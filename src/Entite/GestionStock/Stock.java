package Entite.GestionStock;


public class Stock {
    int id;
    String nom;
    String marque;
    String categorie;
    double prix;
    int note;

    public Stock(int id, String nom, String marque, String categorie, double prix, int note) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.categorie = categorie;
        this.prix = prix;
        this.note = 0;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                ", note=" + note +
                '}';
    }
}

