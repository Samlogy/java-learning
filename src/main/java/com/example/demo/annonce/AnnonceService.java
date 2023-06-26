package com.example.demo.annonce;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Slf4j
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }
    public List<Annonce> getAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce getAnnonceById(UUID id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
    }

    public List<Annonce> filterAnnonces(String title, Double priceMin, Double priceMax, Type type) {
        // Solution 1: create fillter(repo) + custom query
        return annonceRepository.filterAnnonces(title, type, priceMin, priceMax);

        // Solution 2:
//        Specification<Annonce> spec = annonceSpecification.filterByParams(title, priceMin, priceMax, type);
//        List<Annonce> annonces = annonceRepository.findAll(spec);
//        return annonces.stream()
//                .map(annonceMapper::toDTO)
//                .collect(Collectors.toList());
    }

    public Map<String, Object> getAnnoncesByTitlePagingFilteringSorting(String title, int page, int size, String[] sort) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields --> sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }


        List<Annonce> annonces = new ArrayList<Annonce>();
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<Annonce> pageTuts;
        if (title == null) pageTuts = annonceRepository.findAll(pagingSort);
        else pageTuts = annonceRepository.findByTitle(title, pagingSort);

        annonces = pageTuts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("annonces", annonces);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return response;
    }

    public List<Annonce> getAnnoncesByTitleSorting(String title, String[] sort) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields --> sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }


        List<Annonce> res;
        Sort sorting = Sort.by(orders);

        if (title == null) res = annonceRepository.findAll(sorting);
        else res = annonceRepository.findByTitle(title, sorting);

        return res;
    }


    public Annonce createAnnonce(Annonce annonce) {
        if (annonce.getTitle() == null || annonce.getType() == null || annonce.getDescription() == null) {
            throw new BadRequestException("Invalid Annonce Data !");
        }
        else {
            annonce.setCreatedAt(LocalDate.now());
            return annonceRepository.save(annonce);
        }
    }

    public Annonce updateAnnonce(UUID id, Annonce annonce) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        annonceExist.setTitle(annonce.getTitle());
        annonceExist.setDescription(annonce.getDescription());
        annonceExist.setPrice(annonce.getPrice());
        annonceExist.setType(annonce.getType());

        return annonceRepository.save(annonceExist);
    }

    public Annonce patchAnnonce(UUID id, Annonce annonce) {
        Annonce annonceExist = annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));

        if (!annonce.getTitle().isEmpty()) annonceExist.setTitle(annonce.getTitle());
        if (!annonce.getDescription().isEmpty()) annonceExist.setDescription(annonce.getDescription());

        return annonceRepository.save(annonceExist);
    }

    public boolean deleteAnnonce(UUID id) {
       annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        annonceRepository.deleteById(id);
        return true;
    }
}
