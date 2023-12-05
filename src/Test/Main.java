package Test;
import java.sql.SQLException;
import java.util.ArrayList;

import Entite.Utilisateur;
import Services.ServiceUtilisateur;

public class Main {
    public static void main(String[] args) {

        ServiceUtilisateur ser=new ServiceUtilisateur();
        Utilisateur u1=new Utilisateur("test1","trst","test@gmail.com","aabbcc");

       /* try {
            ser.ajouter(u1);
            System.out.println("utilisateur ajouté");
        }catch (SQLException e)
        {
            System.out.println(e);
        }*/
        try {
            ser.delete(1);
            System.out.println("utilisateur supprimer");
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        try {
            ser.update(u1);
            System.out.println("utilisateur mise a jour");
        }catch (SQLException e)
        {
            System.out.println(e);
        }

        ArrayList<Utilisateur> l=null;
        try {
            l = ser.readAll();
        }catch (SQLException e)
        {
            System.out.println(e);
        }


        l.forEach(e-> System.out.println(e));

        try {
            System.out.println(ser.login("test@gmail.com","aabbcc"));

        }catch (SQLException e)
        {
            System.out.println(e);
        }
    }

}
