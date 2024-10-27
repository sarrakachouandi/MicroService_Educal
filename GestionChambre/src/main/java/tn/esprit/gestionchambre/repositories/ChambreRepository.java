package tn.esprit.gestionchambre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.gestionchambre.entities.Chambre;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre,Long> {


}