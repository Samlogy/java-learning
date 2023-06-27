package com.example.MeetingPlanner.booking;

import com.example.MeetingPlanner.room.ResourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public List<Booking> getMeetings() {
        return bookingRepository.findAll();
    }

//    public List<Booking> filterBookings(Integer capacity, MeetingType meetingType, ResourceType availableResources, String planning) {
//        return bookingRepository.filterMeetings(capacity, meetingType, availableResources, planning);
//    }

    public Booking createBooking(Booking meeting) {
        meeting.setCreatedAt(LocalDate.now());
        return bookingRepository.save(meeting);
    }

}
