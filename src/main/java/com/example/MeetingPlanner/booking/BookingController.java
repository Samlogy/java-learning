package com.example.MeetingPlanner.booking;

import com.example.MeetingPlanner.room.ResourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getMeetings());
    }

//    @GetMapping
//    public ResponseEntity<List<Booking>> filterBookings(@RequestParam(name = "capacity", required = false) Integer capacity,
//                                                        @RequestParam(name = "meetingType", required = false) MeetingType meetingType,
//                                                        @RequestParam(name = "availableResources", required = false) ResourceType availableResources,
//                                                        @RequestParam(name = "planning", required = false) String planning) {
//        return ResponseEntity.status(HttpStatus.OK).body(bookingService.filterBookings(capacity, meetingType, availableResources, planning));
//    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking meeting) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(meeting));
    }
}

