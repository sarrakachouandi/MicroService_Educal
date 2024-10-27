package tn.esprit.specialiteMS.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.specialiteMS.Repositories.SpecialiteRepository;
import tn.esprit.specialiteMS.entities.Specialite;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecialiteService implements ISpecialiteService{

    SpecialiteRepository specialiteRepository;
    FileStorageService fileStorageService;


    @Override
    public Specialite add(Specialite specialite) {
        return specialiteRepository.save(specialite);
    }

    @Override
    public Specialite getById(Long id) {
        return specialiteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Specialite> getAll() {
        return specialiteRepository.findAll();
    }

    @Override
    public void delete(long id) {
        specialiteRepository.deleteById(id);
    }

    @Override
    public Specialite update(Specialite specialite) {
        Specialite oldSpecialite = specialiteRepository.findById(specialite.getId()).orElse(null);
        specialite.setImageUrl(oldSpecialite.getImageUrl());
        specialite.setPlanEtudePdf(oldSpecialite.getPlanEtudePdf());
        return specialiteRepository.save(specialite);
    }

    public Specialite handlePlanEtudeFileUpload(MultipartFile file, long id) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(file);
        Specialite specialite = specialiteRepository.findById(id).orElse(null);
        specialite.setPlanEtudePdf(fileName);
        return specialiteRepository.save(specialite);
    }

    public Specialite handleImageFileUpload(MultipartFile fileImage, long id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        Specialite specialite = specialiteRepository.findById(id).orElse(null);
        specialite.setImageUrl(fileName);
        return specialiteRepository.save(specialite);
    }
}
