package tn.esprit.gestionchambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.esprit.gestionchambre.entities.Chambre;
import tn.esprit.gestionchambre.entities.TypeChambre;
import tn.esprit.gestionchambre.repositories.ChambreRepository;


@SpringBootApplication
public class GestionChambreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionChambreApplication.class, args);
    }

@Autowired
    private ChambreRepository chambreRepository;
    @Bean
    ApplicationRunner init(){

        return(args)->{
            chambreRepository.save(new Chambre(1, TypeChambre.DOUBLE));
            chambreRepository.findAll().forEach(System.out::println);
        };
    }
}
