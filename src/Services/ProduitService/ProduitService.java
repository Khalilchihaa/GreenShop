package Services.ProduitService;

import Entite.Produit.Produit;
import Services.IService;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProduitService implements IService<Produit> {
    private Connection con= DataSource.getInstance().getCon();

    private Statement ste;
    @Override
    public void ajouter(Produit produit) throws SQLException {

    }

    @Override
    public void update(Produit produit) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public ArrayList<Produit> readAll() throws SQLException {
        return null;
    }

    @Override
    public Produit get(int id) throws SQLException {
        return null;
    }
}
