package com.example.MeetingPlanner.meeting;

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
@Table(name="meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String creneauHoraire;
    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;
    private UUID roomId;
    private LocalDate createdAt;
}