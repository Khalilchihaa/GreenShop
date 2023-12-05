package Entite.Produit;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private Categorie categorie;
    private Marque marque;

    public Produit(int id, String nom, double prix, Categorie categorie, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.marque = marque;
    }
    public Produit(String nom, double prix, Categorie categorie, Marque marque) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.marque = marque;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", categorie=" + categorie +
                ", marque=" + marque +
                '}';
    }
}
