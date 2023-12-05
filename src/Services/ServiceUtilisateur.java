package Services;

import Entite.Utilisateur.Utilisateur;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceUtilisateur implements IService<Utilisateur>{

   private Connection con= DataSource.getInstance().getCon();

   private Statement ste;


   public ServiceUtilisateur()
   {
       try {
               ste = con.createStatement();
       }catch (SQLException e)
       {
           System.out.println(e);
       }
   }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        String req="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`,`password`) VALUES (NULL, '"+utilisateur.getNom()+"', '"+utilisateur.getPrenom()+"', '"+utilisateur.getEmail()+"', '"+utilisateur.getPassword()+"');";
        int res=   ste.executeUpdate(req);
    }

    @Override
    public void update(Utilisateur utilisateur) throws SQLException {
        String req = "UPDATE utilisateur SET nom = '" + utilisateur.getNom()+
                "', prenom = '" + utilisateur.getPrenom()+
                "', email = '" + utilisateur.getEmail() +
                "', password = '" + utilisateur.getPassword() +
                "' WHERE id = " + utilisateur.getId();

        int res = ste.executeUpdate(req);

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id = " + id;
        int res = ste.executeUpdate(req);
    }

        public boolean login(String email, String motDePasse) throws SQLException {
            String req = "SELECT * FROM utilisateur WHERE email = '" + email + "' AND password = '" + motDePasse + "'";
            ResultSet resultSet = ste.executeQuery(req);

            return resultSet.next();
        }



    @Override
    public ArrayList<Utilisateur> readAll() throws SQLException {
       ArrayList<Utilisateur> list=new ArrayList<>();
        try {
            ResultSet resultSet = ste.executeQuery("select * from utilisateur");
            while (resultSet.next())
            {
                int id=resultSet.getInt("id");
                String nom=resultSet.getString(2);

                String prenom=resultSet.getString(3);
                String email=resultSet.getString(4);
                String password=resultSet.getString(5);
               Utilisateur u=new Utilisateur(id,nom,prenom,email,password);
               list.add(u);
            }

        }catch (SQLException e)
        {
            System.out.println(e);
        }




       return list;
    }

    @Override
    public Utilisateur get(int id) throws SQLException {
        return null;
    }
}
