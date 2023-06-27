package com.example.MeetingPlanner.room;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roomId;
    private String name;
    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private ResourceType resources;
    private LocalDate createdAt;
}