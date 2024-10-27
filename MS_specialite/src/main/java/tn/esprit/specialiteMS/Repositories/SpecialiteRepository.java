package tn.esprit.specialiteMS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.specialiteMS.entities.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite,Long> {
}
