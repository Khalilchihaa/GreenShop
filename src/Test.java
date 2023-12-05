import java.sql.*;
public class Test {
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
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
      /* try {
           ste = con.createStatement();
           String req="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`,'password') VALUES (1, 'ben fade', 'ons', 'benfade@gmail.com','abcde');";
        int res=   ste.executeUpdate(req);

        System.out.println("utilisateur ajouté");
       }catch (SQLException e){
           System.out.println(e);
       }*/
        try {
            ste = con.createStatement();
            String req="DELETE  FROM `utilisateur` WHERE id =1);";
            int res=   ste.executeUpdate(req);

            System.out.println("utilisateur supprimé");
        }catch (SQLException e){
            System.out.println(e);
        }
        try {
            ste = con.createStatement();
            String req="UPDATE `utilisateur` SET `nom` = 'test6tt', `prenom` = 'hhhg', `email` = 'ttt@gmail.comg', `password` = 'hhhhhg' WHERE `utilisateur`.`id` = 6;";
            //String req="UPDATE `utilisateur` SET nom ='test5',prenom='hhh', email='ttt@gmail.com',password='hhhhh' WHERE id =6;";
            System.out.println(req);
            int res=   ste.executeUpdate(req);

            System.out.println("utilisateur a jour");
        }catch (SQLException e){
            System.out.println(e);
        }
       try {
           ResultSet resultSet = ste.executeQuery("select * from utilisateur");
      while (resultSet.next())
      {
          int id=resultSet.getInt("id");
          String nom=resultSet.getString(2);

          String prenom=resultSet.getString(3);
          String email=resultSet.getString(4);
          String password=resultSet.getString(5);
          System.out.println("id :"+id);
          System.out.println("nom :"+nom);
          System.out.println("prenom :"+prenom);
          System.out.println("email :"+email);
          System.out.println("password :"+password);
      }

       }catch (SQLException e)
       {
           System.out.println(e);
       }
    }
}
