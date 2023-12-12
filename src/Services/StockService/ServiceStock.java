package Services.StockService;


import Entite.GestionStock.Stock;
import Services.IService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;

public class ServiceStock implements IService<Stock> {
    private Connection connection= DataSource.getInstance().getCon();
    private Statement statement;
    public ServiceStock(){
        try {
            statement= connection.createStatement();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void ajouter(Stock stock) throws SQLException {
        String query = "INSERT INTO stock (id, nom, marque, categorie, prix, note) VALUES ('" +
                stock.getId() + "','" + stock.getNom() + "', '" + stock.getMarque() + "', '" +
                stock.getCategorie() + "', '" + stock.getPrix() + "', '" + stock.getNote() + "')";

        int res = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                stock.setId(generatedKeys.getInt(1));
                System.out.println("stock ajouté avec succès. Nouvel ID : " + stock.getId());
            } else {
                System.out.println("Erreur lors de la récupération de l'ID généré automatiquement.");
            }
        }
    }


    @Override
    public void update(Stock stock) throws SQLException {
        String req = "UPDATE stock SET nom = '" + stock.getNom() + "', marque = '" + stock.getMarque()
                + "', categorie = '" + stock.getCategorie() + "', prix = '" + stock.getPrix() + "', note = "
                + stock.getNote() + " WHERE id = " + stock.getId();
        int res = statement.executeUpdate(req);
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM stock WHERE id = " + id;
        int res = statement.executeUpdate(req);

    }

    @Override
    public ArrayList<Stock> readAll() throws SQLException {
        ArrayList<Stock> stock = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from stock");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String marque = resultSet.getString(3);
                String categorie = resultSet.getString(4);
                double prix = resultSet.getDouble(5);
                int note = resultSet.getInt(6);
                stock.add(new Stock(id, nom, marque,categorie,prix,note));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return stock; // Retournez la liste des fournisseurs ici
    }


    public Stock consulter(int id) throws SQLException {
        Stock stock = null;
        String query = "SELECT * FROM stock WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Récupérer les informations de l'enregistrement
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                double prix = resultSet.getDouble("prix");
                int note = resultSet.getInt("note");

                // Créer une instance de Stock avec les informations récupérées
                stock = new Stock(id, nom, marque, categorie, prix, note);
            }
        }

        return stock;
    }

    @Override
    public Stock get(int id) throws SQLException {
        return null;
    }


    public void contacter(int stockid, String message) throws SQLException {
        try {
            Stock stock = consulter(stockid);
            if (stock != null) {
                System.out.println("contacter fournisseur " + stock.getNom() + " " + " :");
                System.out.println(message);
            } else {
                System.out.println("stock introuvable avec l'ID : " + stockid);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des informations du stock : " + ex.getMessage());
        }
    }

    public ArrayList<Stock> chercherProduitParMarque(String marque) throws SQLException {
        ArrayList<Stock> produitsTrouves = new ArrayList<>();
        try {
            String query = "SELECT * FROM stock WHERE marque = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, marque);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String nom = resultSet.getString(2);
                    String categorie = resultSet.getString(3);
                    String prixString = resultSet.getString(5);
                    System.out.println("Contenu de la colonne 'prix': " + prixString);

                    double prix = 0.0;  // Défaut si la conversion échoue
                    try {
                        prix = Double.parseDouble(prixString);
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur de conversion de la colonne 'prix' en double: " + e.getMessage());
                    }

                    int note = resultSet.getInt(6);
                    produitsTrouves.add(new Stock(id, nom, marque, categorie, prix, note));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return produitsTrouves;
    }


    public ArrayList<Stock> chercherProduitParCategorie(String categorie) throws SQLException {
        ArrayList<Stock> produitsTrouves = new ArrayList<>();
        try {
            String query = "SELECT * FROM stock WHERE categorie = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categorie);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String nom = resultSet.getString(2);
                    String marque = resultSet.getString(3);
                    Object prixObject = resultSet.getObject(5);
                    double prix = 0.0;  // Défaut si la conversion échoue

                    if (prixObject instanceof Double) {
                        prix = (Double) prixObject;
                    } else if (prixObject instanceof Number) {
                        prix = ((Number) prixObject).doubleValue();
                    } else if (prixObject != null) {
                        System.out.println("Type de données inattendu pour la colonne 'prix'");
                    }

                    int note = resultSet.getInt(6);
                    produitsTrouves.add(new Stock(id, nom, marque, categorie, prix, note));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return produitsTrouves;
    }

    public ArrayList<Stock> chercherProduitParPrix(double prixMin, double prixMax) throws SQLException {
        ArrayList<Stock> produitsTrouves = new ArrayList<>();
        try {
            String query = "SELECT * FROM stock WHERE prix BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, prixMin);
                preparedStatement.setDouble(2, prixMax);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String nom = resultSet.getString(2);
                    String marque = resultSet.getString(3);
                    String categorie = resultSet.getString(4);
                    int note = resultSet.getInt(6);
                    produitsTrouves.add(new Stock(id, nom, marque, categorie, prixMin, note));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return produitsTrouves;
    }

    public void noterProduit(int idProduit, int nouvelleNote) throws SQLException {
        try {
            String query = "UPDATE stock SET note = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, nouvelleNote);
                preparedStatement.setInt(2, idProduit);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    System.out.println("Note mise à jour avec succès pour le produit avec l'ID : " + idProduit);
                } else {
                    System.out.println("Erreur lors de la mise à jour de la note pour le produit avec l'ID : " + idProduit);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}


