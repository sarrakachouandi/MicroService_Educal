package tn.esprit.News.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.News.Entities.Actualite;

public interface ActualiteRepository extends JpaRepository<Actualite, Long> {
}
