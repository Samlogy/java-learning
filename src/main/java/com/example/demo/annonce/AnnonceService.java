package com.example.demo.annonce;

import com.example.demo.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Slf4j
@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }

    public Map<String, Object> getAnnonces(int page, int size) {
        Map<String, Object> response = new HashMap<>();

        Pageable paging = PageRequest.of(page, size);
        Page<Annonce> pageAnnonces = annonceRepository.findAll(paging);

        response.put("annonces", pageAnnonces.getContent());
        response.put("currentPage", pageAnnonces.getNumber());
        response.put("totalItems", pageAnnonces.getTotalElements());
        response.put("totalPages", pageAnnonces.getTotalPages());
        return response;
    }

    public Annonce getAnnonceById(UUID id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
    }

    public Map<String, Object> filterAnnonces(String title, Double priceMin, Double priceMax, Type type, int page, int size) {
        // Solution 1: create fillter(repo) + custom query
//        return annonceRepository.filterAnnonces(title, type, priceMin, priceMax, page, size);

        Map<String, Object> response = new HashMap<>();

        Pageable paging = PageRequest.of(page, size);
        Page<Annonce> pageAnnonces = annonceRepository.filterAnnonces(title, type, priceMin, priceMax, paging);

        response.put("annonces", pageAnnonces.getContent());
        response.put("currentPage", pageAnnonces.getNumber());
        response.put("totalItems", pageAnnonces.getTotalElements());
        response.put("totalPages", pageAnnonces.getTotalPages());
        return response;
    }

    public Map<String, Object> getAnnoncesPagingSorting(int page, int size, String[] sort) {
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


        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<Annonce> pageAnnonces = annonceRepository.findAll(pagingSort);
        Map<String, Object> response = new HashMap<>();

        response.put("annonces", pageAnnonces.getContent());
        response.put("currentPage", pageAnnonces.getNumber());
        response.put("totalItems", pageAnnonces.getTotalElements());
        response.put("totalPages", pageAnnonces.getTotalPages());

        return response;
    }

    public List<Annonce> getAnnoncesSorting(String[] sort) {
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
        Sort sorting = Sort.by(orders);
        return annonceRepository.findAll(sorting);
    }

    public Annonce createAnnonce(Annonce annonce) {
//        if (annonce.getTitle() == null || annonce.getType() == null || annonce.getDescription() == null) {
//            throw new MethodArgumentNotValidException("Invalid Annonce Data !");
//        }
//        else {
//            annonce.setCreatedAt(LocalDate.now());
//            return annonceRepository.save(annonce);
//        }
        annonce.setCreatedAt(LocalDate.now());
        return annonceRepository.save(annonce);
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
        if (annonce.getType() != null) annonceExist.setType(annonce.getType());

        return annonceRepository.save(annonceExist);
    }

    public boolean deleteAnnonce(UUID id) {
       annonceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Annonce not found with ID: " + id));
        annonceRepository.deleteById(id);
        return true;
    }
}
