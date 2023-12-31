import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Boolean connection = false;
        String choix = "";
        String nom="";
        String prenom="";
        String metier="";
        String email="";
        String mdp="";
        System.out.println("Veuillez vous connecté:");
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        while (!(choix.equals("quitter"))) {

            while (connection != true) {
                System.out.println("Votre email:");
                email = sc.next();
                System.out.println("Votre Mot De Passe:");
                mdp = sc.next();
                PreparedStatement requetePrepare = crud.prepareStatement("SELECT * FROM user WHERE mail = ? and mdp = ?");
                requetePrepare.setString(1, email);
                requetePrepare.setString(2, mdp);

                ResultSet resultatsRequetes = requetePrepare.executeQuery();
                if (resultatsRequetes.next()) {
                    System.out.println("Authentification réussie.");
                    System.out.println("Vous étes connecter");
                    connection = true;
                } else {
                    System.out.println("Echec de l'authentification. L'utilisateur n'existe pas ou les informations sont incorrectes.");
                    connection = false;
                }
            }
            while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toLowerCase();
            }
            if (choix.equals("ajouter")) {
              ajouter(choix,nom,prenom,metier, email, mdp);
              menu(choix);
            }
            else if (choix.equals("lister")) {
                lister();
                menu(choix);
            }
            else if (choix.equals("selectionner")) {
                selectionner(choix,nom,prenom,metier, email, mdp);
                menu(choix);
            }
            else if (choix.equals("rechercher")) {
                rechercher(choix);
                menu(choix);
            }
        }
    }
    private static void ajouter(String choix, String nom, String prenom, String metier, String email, String mdp) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        PreparedStatement requeteinsertion = crud.prepareStatement("INSERT INTO user (id_user, nom, prenom, metier, mail, mdp) VALUES (?, ?, ?, ?, ?, ?)");
        System.out.println("ID:");
        System.out.println("Saisissez votre nom:");
        nom = sc.next();
        System.out.println("Saisissez votre prenom:");
        prenom = sc.next();
        System.out.println("Saisissez votre metier:");
        metier = sc.next();
        System.out.println("Saisissez votre email:");
        email = sc.next();
        System.out.println("Saisissez votre le Mot De Passe:");
        mdp = sc.next();
        requeteinsertion.setString(1, nom);
        requeteinsertion.setString(2, prenom);
        requeteinsertion.setString(3, metier);
        requeteinsertion.setString(4, email);
        requeteinsertion.setString(5, mdp);
        requeteinsertion.executeUpdate();
    }
    private static void lister() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        PreparedStatement requetePrepareAfficher = crud.prepareStatement(
                "SELECT*FROM user"
        );
        ResultSet resultatsRequetes = requetePrepareAfficher.executeQuery();
        while (resultatsRequetes.next()) {
            System.out.println("ID : Nom-Prénom-Métier-Mail");
            System.out.print(resultatsRequetes.getInt(1));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(2));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(3));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(4));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(5));
            System.out.println("");
        }
    }
    private static void selectionner(String choix,String nom, String prenom, String metier, String email, String mdp) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        PreparedStatement requetePrepareselectionner = crud.prepareStatement(
                "SELECT*FROM user");
        ResultSet resultatsRequetes1 = requetePrepareselectionner.executeQuery();
        System.out.println("ID : Nom-Prénom-Métier-Mail");
        while (resultatsRequetes1.next()) {
            System.out.print(resultatsRequetes1.getInt(1));
            System.out.print("-");
            System.out.print(resultatsRequetes1.getString(2));
            System.out.print("-");
            System.out.print(resultatsRequetes1.getString(3));
            System.out.print("-");
            System.out.print(resultatsRequetes1.getString(4));
            System.out.print("-");
            System.out.print(resultatsRequetes1.getString(5));
            System.out.println("");
        }
        System.out.println("Sélectionner l'id:");
        int ID = sc.nextInt();
        System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
        System.out.println("(lister)- Lister tous les utilisateurs");
        System.out.println("(rechercher)- Rechercher des utilisateurs");
        System.out.println("(ajouter)-Ajouter des utilisateurs");
        System.out.println("(selectionner)-Séléctioner un utilisateur");
        System.out.println("Pour l'utilisateur séléctionné,");
        System.out.println("(afficher) Affiche l'utilisateur d'id "+ ID);
        System.out.println("(modifier) Affiche l'utilisateur d'id "+ ID);
        System.out.println("(suprimer) Affiche l'utilisateur d'id "+ ID);
        System.out.println("(deconnecter)-Se déconecter");
        System.out.println("(quitter)-Quitter");
        choix = sc.next().toUpperCase();
        while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
            System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
            System.out.println("(lister)- Lister tous les utilisateurs");
            System.out.println("(rechercher)- Rechercher des utilisateurs");
            System.out.println("(ajouter)-Ajouter des utilisateurs");
            System.out.println("(selectionner)-Séléctioner un utilisateur");
            System.out.println("Pour l'utilisateur séléctionné,");
            System.out.println("(afficher) Affiche l'utilisateur d'id "+ ID);
            System.out.println("(modifier) Affiche l'utilisateur d'id "+ ID);
            System.out.println("(suprimer) Affiche l'utilisateur d'id "+ ID);
            System.out.println("(deconnecter)-Se déconecter");
            System.out.println("(quitter)-Quitter");
            choix = sc.next().toUpperCase();
        }
        while (!(choix.equals("QUITTER"))&&!(choix.equals("DECONNECTER"))&&!(choix.equals("SUPRIMER"))){
            if (choix.equals("LISTER")) {
                lister();
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("Pour l'utilisateur séléctionné,");
                System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toUpperCase();
                while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                    System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                    System.out.println("(lister)- Lister tous les utilisateurs");
                    System.out.println("(rechercher)- Rechercher des utilisateurs");
                    System.out.println("(ajouter)-Ajouter des utilisateurs");
                    System.out.println("(selectionner)-Séléctioner un utilisateur");
                    System.out.println("Pour l'utilisateur séléctionné,");
                    System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(deconnecter)-Se déconecter");
                    System.out.println("(quitter)-Quitter");
                    choix = sc.next().toUpperCase();
                }
            }
            else if (choix.equals("RECHERCHER")){
                rechercher(choix);
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("Pour l'utilisateur séléctionné,");
                System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toUpperCase();
                while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                    System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                    System.out.println("(lister)- Lister tous les utilisateurs");
                    System.out.println("(rechercher)- Rechercher des utilisateurs");
                    System.out.println("(ajouter)-Ajouter des utilisateurs");
                    System.out.println("(selectionner)-Séléctioner un utilisateur");
                    System.out.println("Pour l'utilisateur séléctionné,");
                    System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(deconnecter)-Se déconecter");
                    System.out.println("(quitter)-Quitter");
                    choix = sc.next().toUpperCase();
                }
            } else if (choix.equals("AJOUTER")) {
                ajouter(choix,nom,prenom,metier, email, mdp);
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("Pour l'utilisateur séléctionné,");
                System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toUpperCase();
                while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                    System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                    System.out.println("(lister)- Lister tous les utilisateurs");
                    System.out.println("(rechercher)- Rechercher des utilisateurs");
                    System.out.println("(ajouter)-Ajouter des utilisateurs");
                    System.out.println("(selectionner)-Séléctioner un utilisateur");
                    System.out.println("Pour l'utilisateur séléctionné,");
                    System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(deconnecter)-Se déconecter");
                    System.out.println("(quitter)-Quitter");
                    choix = sc.next().toUpperCase();
                }
            } else if (choix.equals("SELECTIONNER")) {
                selectionner(choix,nom,prenom,metier, email, mdp);
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("Pour l'utilisateur séléctionné,");
                System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toUpperCase();
                while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                    System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                    System.out.println("(lister)- Lister tous les utilisateurs");
                    System.out.println("(rechercher)- Rechercher des utilisateurs");
                    System.out.println("(ajouter)-Ajouter des utilisateurs");
                    System.out.println("(selectionner)-Séléctioner un utilisateur");
                    System.out.println("Pour l'utilisateur séléctionné,");
                    System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(deconnecter)-Se déconecter");
                    System.out.println("(quitter)-Quitter");
                    choix = sc.next().toUpperCase();
                }
            }
            else if (choix.equals("AFFICHER")) {
                afficher(ID);
                System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                System.out.println("(lister)- Lister tous les utilisateurs");
                System.out.println("(rechercher)- Rechercher des utilisateurs");
                System.out.println("(ajouter)-Ajouter des utilisateurs");
                System.out.println("(selectionner)-Séléctioner un utilisateur");
                System.out.println("Pour l'utilisateur séléctionné,");
                System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                System.out.println("(deconnecter)-Se déconecter");
                System.out.println("(quitter)-Quitter");
                choix = sc.next().toUpperCase();
                while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
                    System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
                    System.out.println("(lister)- Lister tous les utilisateurs");
                    System.out.println("(rechercher)- Rechercher des utilisateurs");
                    System.out.println("(ajouter)-Ajouter des utilisateurs");
                    System.out.println("(selectionner)-Séléctioner un utilisateur");
                    System.out.println("Pour l'utilisateur séléctionné,");
                    System.out.println("(afficher) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(modifier) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(suprimer) Affiche l'utilisateur d'id " + ID);
                    System.out.println("(deconnecter)-Se déconecter");
                    System.out.println("(quitter)-Quitter");
                    choix = sc.next().toUpperCase();
                }
            }
        }
    }
    private static void afficher(int ID) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");
        PreparedStatement requetePrepareAfficher = crud.prepareStatement(
                "SELECT*FROM user WHERE id_user=?"
        );
        requetePrepareAfficher.setInt(1,ID);
        ResultSet resultatsRequetes = requetePrepareAfficher.executeQuery();

        System.out.println("ID : Nom-Prénom-Métier-Mail");
        System.out.print(resultatsRequetes.getInt(1));
        System.out.print("-");
        System.out.print(resultatsRequetes.getString(2));
        System.out.print("-");
        System.out.print(resultatsRequetes.getString(3));
        System.out.print("-");
        System.out.print(resultatsRequetes.getString(4));
        System.out.print("-");
        System.out.print(resultatsRequetes.getString(5));
        System.out.println("");
    }
    private static void rechercher(String choix) throws SQLException {
        Connection crud = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dki_crud", "root", "");

        Scanner sc = new Scanner(System.in);
        System.out.println("Choit de la collone");
        System.out.println("- id_user");
        System.out.println("- nom");
        System.out.println("- prenom");
        System.out.println("- mail");
        System.out.println("- metier");
        String colone = sc.next();
        while (!(colone.equals("id_user")) && !(colone.equals("nom")) && !(colone.equals("prenom")) && !(colone.equals("mail")) && !(colone.equals("metier"))) {
            System.out.println("Choit de la collone");
            System.out.println("- id_user");
            System.out.println("- nom");
            System.out.println("- prenom");
            System.out.println("- mail");
            System.out.println("- metier");
            colone = sc.next();
        }
        System.out.println("Faite votre recherche :");
        String recherche = sc.next().toLowerCase();
        PreparedStatement rechercher = crud.prepareStatement("SELECT * FROM user WHERE " + colone + " LIKE  ?");
        rechercher.setString(1, "%" + recherche + "%");

        ResultSet resultatsRequetes = rechercher.executeQuery();

        while (resultatsRequetes.next()) {

            System.out.println("ID : Nom-Prénom-Métier-Mail");
            System.out.print(resultatsRequetes.getInt(1));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(2));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(3));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(4));
            System.out.print("-");
            System.out.print(resultatsRequetes.getString(5));
            System.out.println("");
        }

    }
    private static String menu(String choix){
        Scanner sc = new Scanner(System.in);
        System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
        System.out.println("(lister)- Lister tous les utilisateurs");
        System.out.println("(rechercher)- Rechercher des utilisateurs");
        System.out.println("(ajouter)-Ajouter des utilisateurs");
        System.out.println("(selectionner)-Séléctioner un utilisateur");
        System.out.println("Aucun utilisateur n'a été choisit.");
        System.out.println("(deconnecter)-Se déconecter");
        System.out.println("(quitter)-Quitter");
        choix = sc.next().toLowerCase();
        while (!(choix.equals("lister")) && !(choix.equals("rechercher")) && !(choix.equals("ajouter")) && !(choix.equals("selectionner")) && !(choix.equals("deconnecter")) && !(choix.equals("quitter"))) {
            System.out.println("Séléctionner le mot clés de l'option que vous voulez utiliser:");
            System.out.println("(lister)- Lister tous les utilisateurs");
            System.out.println("(rechercher)- Rechercher des utilisateurs");
            System.out.println("(ajouter)-Ajouter des utilisateurs");
            System.out.println("(selectionner)-Séléctioner un utilisateur");
            System.out.println("Aucun utilisateur n'a été choisit.");
            System.out.println("(deconnecter)-Se déconecter");
            System.out.println("(quitter)-Quitter");
            choix = sc.next().toLowerCase();
        }
        return choix;
    }
}