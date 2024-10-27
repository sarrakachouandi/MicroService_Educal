package tn.esprit.News.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actualite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idActualite;
    String titreActualite;

    @Column(columnDefinition = "TEXT")
    String descriptionActualite;

    @Temporal(TemporalType.TIMESTAMP)
    Date dateActualite;
    String imageActualite;

}
