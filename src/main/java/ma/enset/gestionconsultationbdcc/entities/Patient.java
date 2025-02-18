package ma.enset.gestionconsultationbdcc.entities;

import java.util.List;

//entité ou classe persistante
public class Patient {
    private long id;
    private String nom;
    private String prenom;
    private String tel;
    private List<Consultation> consultations;

    public Patient() {
    }

    public Patient(String nom, String prenom, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    @Override
    public String toString() {
        return  nom+" " + prenom;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
