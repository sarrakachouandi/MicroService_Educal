package com.esprit.microservices.foyer;






import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Foyer implements Serializable {
    private static final long serialVersionUID = 6;

    @Id
    @GeneratedValue
    private long idFoyer;
    private String nomFoyer;
    private long capacityFoyer;
    private String imageFoyer;


    public Foyer() {
    }

    public Foyer(long idFoyer, String nomFoyer, long capacityFoyer,String imageFoyer) {
        this.idFoyer = idFoyer;
        this.nomFoyer = nomFoyer;
        this.capacityFoyer = capacityFoyer;
        this.imageFoyer = imageFoyer;
    }

    public long getIdFoyer() {
        return idFoyer;
    }


    public String getNomFoyer() {
        return nomFoyer;
    }

    public void setNomFoyer(String nomFoyer) {
        this.nomFoyer = nomFoyer;
    }

    public long getCapacityFoyer() {
        return capacityFoyer;
    }

    public void setCapacityFoyer(long capacityFoyer) {
        this.capacityFoyer = capacityFoyer;
    }

    public String getImageFoyer() {
        return imageFoyer;
    }

    public void setImageFoyer(String imageFoyer) {
        this.imageFoyer = imageFoyer;
    }
}