package tn.esprit.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.service.IProjetService;

import java.util.List;

@RestController
@RequestMapping("/projet")
@AllArgsConstructor
@CrossOrigin(origins = {"http://192.168.33.10:4200", "http://localhost:4200"})
public class ProjetController {

    IProjetService projetService;

    @PostMapping("/add")
    public Projet addProjet(@RequestBody Projet projet) {
        return projetService.addProjet(projet);
    }

    @PutMapping("/update")
    public Projet updateProjet(@RequestBody Projet projet) {
        return projetService.updateProjet(projet);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
    }

    @GetMapping("/get/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id);
    }

    @GetMapping("/all")
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }
}
