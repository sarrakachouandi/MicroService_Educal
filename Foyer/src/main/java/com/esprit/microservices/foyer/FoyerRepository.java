package com.esprit.microservices.foyer;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    @Query("select f from Foyer f where f.nomFoyer like :name")
    public Page<Foyer> foyerByNomFoyer(@Param("name") String n, Pageable pageable);
    List<Foyer> findByCapacityFoyerLessThan(long capacity);
}
