package com.example.MeetingPlanner.meeting;


import com.example.MeetingPlanner.room.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;



@Repository
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
//    @Query("SELECT a FROM Meeting a " +
//            "WHERE (:title IS NULL OR a.title LIKE %:title%) " +
//            "AND (:type IS NULL OR a.type = :type) " +
//            "AND (:priceMin IS NULL OR a.price >= :priceMin) " +
//            "AND (:priceMax IS NULL OR a.price <= :priceMax)")
    List<Meeting> filterMeetings(@Param("capacity") Integer capacity,
                                 @Param("meetingType") MeetingType meetingType,
                                 @Param("availableResources") ResourceType availableResources,
                                 @Param("planning") String planning);
}