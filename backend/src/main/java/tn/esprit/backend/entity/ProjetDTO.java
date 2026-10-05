package tn.esprit.backend.entity;

public class ProjetDTO {

    private Long id;
    private String sujet;

    public ProjetDTO() {}

    public ProjetDTO(Long id, String sujet) {
        this.id = id;
        this.sujet = sujet;
    }


    public static ProjetDTO fromEntity(Projet projet) {
        if (projet == null) return null;
        return new ProjetDTO(projet.getId(), projet.getSujet());
    }


    public Projet toEntity() {
        Projet projet = new Projet();
        projet.setId(this.id);
        projet.setSujet(this.sujet);
        return projet;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSujet() { return sujet; }
    public void setSujet(String sujet) { this.sujet = sujet; }
}
