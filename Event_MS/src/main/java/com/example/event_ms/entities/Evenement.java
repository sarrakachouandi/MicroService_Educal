package com.example.event_ms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEvent;
    String nomEvent;
    String descriptionEvent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateDebEvent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    Date dateFinEvent;
    String lieuEvent;
    String imageEvent;



}
