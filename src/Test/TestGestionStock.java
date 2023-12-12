package Test;

import Entite.GestionStock.Fournisseur;
import Entite.GestionStock.Stock;
import Entite.Utilisateur.Utilisateur;
import Services.ServiceUtilisateur;
import Services.StockService.ServiceFournisseur;
import Services.StockService.ServiceStock;

import java.sql.*;
import java.util.ArrayList;

public class TestGestionStock {
    private static Connection con;
    static String url = "jdbc:mysql://localhost:3306/esprit";
    static String user = "root";
    static String pwd = "";

    private static Statement ste;

    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println(con);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            ste = con.createStatement();
            String req = "INSERT INTO `fournisseur` (`id`, `nom`,`prenom`, `email`,`adresse`,`phonenumber`) VALUES (1, 'wassef', 'chargui', 'medwassef@esprit.tn','la goulette',90026584);";
            int res = ste.executeUpdate(req);

            System.out.println("fournisseur ajouté");
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            ResultSet resultSet = ste.executeQuery("select * from fournisseur");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString(2);
                String prenom = resultSet.getString(3);
                String email = resultSet.getString(4);
                String adresse = resultSet.getString(5);
                int phonenumber = resultSet.getInt(6);

                System.out.println("id :" + id);
                System.out.println("nom :" + nom);
                System.out.println("prenom :" + prenom);
                System.out.println("email :" + email);
                System.out.println("adresse :" + adresse);
                System.out.println("phonenumber :" + phonenumber);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        // Exemple d'utilisation dans la méthode main
        ServiceFournisseur serviceFournisseur = new ServiceFournisseur();

        // Ajouter un fournisseur
        Fournisseur fournisseur = new Fournisseur(2, "Attia", "Imeed", "imed@esprit.com", "hayelkhadhra", 96325874);
        try {
            serviceFournisseur.ajouter(fournisseur);
            System.out.println("Fournisseur ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Lire tous les fournisseurs
        try {
            ArrayList<Fournisseur> fournisseurs = serviceFournisseur.readAll();
            for (Fournisseur f : fournisseurs) {
                System.out.println(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ajouter un fournisseur
        Fournisseur fournisseur1 = new Fournisseur(3, "khalil", "chiha", "khalil@esprit.com", "Aaouina", 32659887);
        try {
            serviceFournisseur.ajouter(fournisseur);
            System.out.println("Fournisseur ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Lire tous les fournisseurs
        try {
            ArrayList<Fournisseur> fournisseurs = serviceFournisseur.readAll();
            for (Fournisseur f : fournisseurs) {
                System.out.println(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mettre à jour un fournisseur
        Fournisseur fournisseurToUpdate = new Fournisseur(4, "Ahmed", "soula", "ahmed@esprit.com", "charguia", 56328745);
        try {
            serviceFournisseur.update(fournisseurToUpdate);
            System.out.println("Fournisseur mis à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lire à nouveau tous les fournisseurs après la mise à jour
        try {
            ArrayList<Fournisseur> fournisseursAfterUpdate = serviceFournisseur.readAll();
            for (Fournisseur f : fournisseursAfterUpdate) {
                System.out.println(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Supprimer un fournisseur
        try {
            serviceFournisseur.delete(3);
            System.out.println("Fournisseur supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lire à nouveau tous les fournisseurs après la suppression
        try {
            ArrayList<Fournisseur> fournisseursAfterDelete = serviceFournisseur.readAll();
            for (Fournisseur f : fournisseursAfterDelete) {
                System.out.println(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Consulter un fournisseur par son ID
        int fournisseurIdToConsult = 1;
        try {
            Fournisseur fournisseurConsulte = serviceFournisseur.consulter(fournisseurIdToConsult);
            if (fournisseurConsulte != null) {
                System.out.println("Fournisseur consulté : " + fournisseurConsulte);
            } else {
                System.out.println("Aucun fournisseur trouvé avec l'ID : " + fournisseurIdToConsult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Fournisseur fournisseurTest = new Fournisseur(1, "wassef", "chargui", "wassef@esprit.com", "Lagoulette", 90026584);
        try {
            serviceFournisseur.ajouter(fournisseurTest);

            // Test de la méthode contacterFournisseur
            int fournisseurIdTest = fournisseurTest.getId();
            String messageTest = "Bonjour, nous avons besoin de réapprovisionnement.";

            serviceFournisseur.contacter(fournisseurIdTest, messageTest);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Créer une instance de ServiceStock
        ServiceStock serviceStock = new ServiceStock();

        // Test de la méthode ajouter
        Stock nouveauStock = new Stock(1, "NomProduit", "MarqueProduit", "CategorieProduit", 99.99, 0);
        try {
            serviceStock.ajouter(nouveauStock);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de la méthode readAll
        try {
            ArrayList<Stock> tousLesProduits = serviceStock.readAll();
            for (Stock produit : tousLesProduits) {
                System.out.println(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de la méthode chercherProduitParMarque
        try {
            ArrayList<Stock> produitsParMarque = serviceStock.chercherProduitParMarque("MarqueProduit");
            for (Stock produit : produitsParMarque) {
                System.out.println(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de la méthode chercherProduitParCategorie
        try {
            ArrayList<Stock> produitsParCategorie = serviceStock.chercherProduitParCategorie("CategorieProduit");
            for (Stock produit : produitsParCategorie) {
                System.out.println(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de la méthode chercherProduitParPrix
        try {
            ArrayList<Stock> produitsParPrix = serviceStock.chercherProduitParPrix(50.0, 100.0);
            for (Stock produit : produitsParPrix) {
                System.out.println(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //noterProduit
        try {
            serviceStock.noterProduit(nouveauStock.getId(), 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //update
        try {
            nouveauStock.setNom("ProduitTREE");
            serviceStock.update(nouveauStock);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // delete
        try {
            serviceStock.delete(nouveauStock.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Consulter
        try {
            int stockId = 1;
            String message = "Bonjour, nous sommes intéressés par vos produits.";

            serviceStock.contacter(stockId, message);

        } catch (SQLException ex) {
            System.out.println("Erreur générale : " + ex.getMessage());
        }
    }
}
