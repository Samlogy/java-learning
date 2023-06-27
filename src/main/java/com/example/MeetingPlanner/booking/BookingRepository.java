package com.example.MeetingPlanner.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;



@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
//    @Query("SELECT a FROM Meeting a " +
//            "WHERE (:title IS NULL OR a.title LIKE %:title%) " +
//            "AND (:type IS NULL OR a.type = :type) " +
//            "AND (:priceMin IS NULL OR a.price >= :priceMin) " +
//            "AND (:priceMax IS NULL OR a.price <= :priceMax)")
//    List<Booking> filterMeetings(@Param("capacity") Integer capacity,
//                                 @Param("meetingType") MeetingType meetingType,
//                                 @Param("availableResources") ResourceType availableResources,
//                                 @Param("planning") String planning);
}