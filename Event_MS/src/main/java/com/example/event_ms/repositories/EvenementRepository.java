package com.example.event_ms.repositories;

import com.example.event_ms.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement,Long> {

    List<Evenement> findAllByOrderByDateDebEventAsc();

    List<Evenement> findAllByOrderByDateDebEventDesc();
}
