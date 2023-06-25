package com.example.MeetingPlanner.meeting;

import com.example.MeetingPlanner.room.ResourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public ResponseEntity<List<Meeting>> getMeetings() {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getMeetings());
    }

    @GetMapping
    public ResponseEntity<List<Meeting>> filterMeetings(@RequestParam(name = "capacity", required = false) Integer capacity,
                                                        @RequestParam(name = "meetingType", required = false) MeetingType meetingType,
                                                        @RequestParam(name = "availableResources", required = false) ResourceType availableResources,
                                                        @RequestParam(name = "planning", required = false) String planning) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.filterMeetings(capacity, meetingType, availableResources, planning));
    }

    @PostMapping
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.createMeeting(meeting));
    }
}

