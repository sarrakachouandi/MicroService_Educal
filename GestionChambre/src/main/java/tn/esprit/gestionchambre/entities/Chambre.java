package tn.esprit.gestionchambre.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Chambre implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long idChambre;
        long numeroChambre;

        public Chambre(long numeroChambre, TypeChambre typeChambre) {
            this.numeroChambre = numeroChambre;
            this.typeChambre = typeChambre;
        }

        @Enumerated(EnumType.STRING)
        TypeChambre typeChambre;
        String imageUrl;

    }
