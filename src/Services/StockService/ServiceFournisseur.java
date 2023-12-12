package Services.StockService;



import Entite.GestionStock.Fournisseur;
import Services.IService;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceFournisseur implements IService<Fournisseur> {
    private Connection connection= DataSource.getInstance().getCon();
    private Statement statement;
    public ServiceFournisseur(){
        try {
            statement= connection.createStatement();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter(Fournisseur fournisseur) throws SQLException {
        String query = "INSERT INTO fournisseur (nom, prenom, email, adresse, phonenumber) VALUES ('"
                + fournisseur.getNom() + "', '" + fournisseur.getPrenom() + "', '" + fournisseur.getEmail()
                + "', '" + fournisseur.getAdresse() + "', '" + fournisseur.getPhonenumber() + "')";
        int res = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                fournisseur.setId(generatedKeys.getInt(1));
                System.out.println("Fournisseur ajouté avec succès. Nouvel ID : " + fournisseur.getId());
            } else {
                System.out.println("Erreur lors de la récupération de l'ID généré automatiquement.");
            }
        }
    }
    @Override
    public void update(Fournisseur fournisseur) throws SQLException {
        String req = "UPDATE fournisseur SET nom = '" + fournisseur.getNom() + "', prenom = '"
                + fournisseur.getPrenom() + "', email = '" + fournisseur.getEmail() + "', adresse = '" + fournisseur.getAdresse() +"', phonenumber = '" + fournisseur.getPhonenumber() + "' WHERE id = "
                + fournisseur.getId();
        int res = statement.executeUpdate(req);
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM fournisseur WHERE id = " + id;
        int res = statement.executeUpdate(req);
    }

    @Override
    public ArrayList<Fournisseur> readAll() throws SQLException {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from fournisseur");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String prenom = resultSet.getString(3);
                String email = resultSet.getString(4);
                String adresse = resultSet.getString(5);
                int phonenumber = resultSet.getInt(6);
                fournisseurs.add(new Fournisseur(id, nom, prenom, email, adresse,phonenumber));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return fournisseurs;
    }




    @Override
    public Fournisseur get(int id) throws SQLException {
        return null;
    }
    public Fournisseur consulter(int id) throws SQLException {
        String req = "SELECT * FROM fournisseur WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(req);

        if (resultSet.next()) {
            id = resultSet.getInt(1);
            String nom = resultSet.getString(2);
            String prenom = resultSet.getString(3);
            String email = resultSet.getString(4);
            String adresse = resultSet.getString(5);
            int phonenumber = resultSet.getInt(6);

            Fournisseur fournisseur = new Fournisseur(id, nom, prenom, email, adresse,phonenumber);
            return fournisseur;
        } else {
            return null;
        }
    }


    public Fournisseur contacter(int fournisseurId, String message) throws SQLException {
        try {
            Fournisseur fournisseur = consulter(fournisseurId);
            if (fournisseur != null) {
                System.out.println("Envoi du message au fournisseur " + fournisseur.getNom() + " " + fournisseur.getPrenom() + " :");
                System.out.println(message);
            } else {
                System.out.println("Fournisseur introuvable avec l'ID : " + fournisseurId);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des informations du fournisseur : " + ex.getMessage());
        }
        return null;
    }
}

