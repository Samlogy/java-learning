package com.example.MeetingPlanner.meeting;

import com.example.MeetingPlanner.room.ResourceType;
import com.example.MeetingPlanner.room.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }
    public List<Meeting> getMeetings() {
        return meetingRepository.findAll();
    }

    public List<Meeting> filterMeetings(Integer capacity, MeetingType meetingType, ResourceType availableResources, String planning) {
        return meetingRepository.filterMeetings(capacity, meetingType, availableResources, planning);
    }

    public Meeting createMeeting(Meeting meeting) {
        meeting.setCreatedAt(LocalDate.now());
        return meetingRepository.save(meeting);
    }

}
