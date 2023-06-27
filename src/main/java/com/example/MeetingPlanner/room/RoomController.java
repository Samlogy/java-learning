package com.example.MeetingPlanner.room;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> filterMeetings() {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRooms());
    }
    @PostMapping
    public ResponseEntity<Room> addMeeting(@RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(room));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Room> patchRoom(@PathVariable UUID id, @RequestBody ResourceType resources) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.patchRoom(id, resources));
    }
}
