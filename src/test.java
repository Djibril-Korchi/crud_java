import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws SQLException {
        Scanner sc =new Scanner(System.in);
        Connection maConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        PreparedStatement requeteinsertion = maConnection.prepareStatement("INSERT INTO user (id_user, nom, prenom, metier, mail, mdp) VALUES (?, ?, ?, ?, ?, ?)");        System.out.println("ID:");
        int id = sc.nextInt();
        System.out.println("Saisissez votre nom:");
        String nom = sc.next();
        System.out.println("Saisissez votre prenom:");
        String prenom = sc.next();
        System.out.println("Saisissez votre metier:");
        String metier = sc.next();
        System.out.println("Saisissez votre email:");
        String email = sc.next();
        System.out.println("Saisissez votre le Mot De Passe:");
        String mdp = sc.next();
        requeteinsertion.setInt(1,id);
        requeteinsertion.setString(2, nom);
        requeteinsertion.setString(3, prenom);
        requeteinsertion.setString(4, metier);
        requeteinsertion.setString(5, email);
        requeteinsertion.setString(6, mdp);
        requeteinsertion.executeUpdate();
    }
}
