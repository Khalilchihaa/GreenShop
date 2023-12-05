package Services.ProduitService;
import Entite.Produit.Categorie;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieService {
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public CategorieService() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO categorie (nom) VALUES (?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, categorie.getNom());
            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    categorie.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Categorie categorie) throws SQLException {
        String req = "UPDATE categorie SET nom = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setString(1, categorie.getNom());
            preparedStatement.setInt(2, categorie.getId());
            int res = preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String req = "DELETE FROM categorie WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();
        }
    }

    public ArrayList<Categorie> readAll() throws SQLException {
        ArrayList<Categorie> list = new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("SELECT * FROM categorie");
            while (resultSet.next()) {
                int categorieId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Categorie categorie = new Categorie(categorieId, nom);
                list.add(categorie);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Categorie get(int id) throws SQLException {
        String req = "SELECT * FROM categorie WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                return new Categorie(id, nom);
            }
        }
        return null;
    }
}

