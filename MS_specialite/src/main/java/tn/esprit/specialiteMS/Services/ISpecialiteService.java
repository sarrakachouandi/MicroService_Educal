package tn.esprit.specialiteMS.Services;

import tn.esprit.specialiteMS.entities.Specialite;

import java.util.List;

public interface ISpecialiteService {
    Specialite add(Specialite specialite);
    Specialite getById(Long id);
    List<Specialite> getAll();
    void delete(long id);
    Specialite update(Specialite specialite);
}
