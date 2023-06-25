package com.example.MeetingPlanner.room;

import com.example.MeetingPlanner.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RoomService {
    private final RoomRepository roomRepositoryRepository;
    @Autowired
    public RoomService(RoomRepository roomRepositoryRepository) {
        this.roomRepositoryRepository = roomRepositoryRepository;
    }
    public List<Room> getRooms() {
        return roomRepositoryRepository.findAll();
    }

    public Room addRoom(Room room) {
        room.setCreatedAt(LocalDate.now());
        return roomRepositoryRepository.save(room);
    }

    public Room patchRoom(UUID id, ResourceType resources) {
        Room roomExist = roomRepositoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with ID: " + id));
        if (resources != null) {
            roomExist.setResources(resources);
            return roomRepositoryRepository.save(roomExist);
        }
        throw new IllegalArgumentException("Room requires resources !");
    }
}
