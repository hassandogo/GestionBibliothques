import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivreDAO {
    private static void ajouterLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le titre du livre : ");
        String titre = scanner.nextLine();
        System.out.print("Entrez l'auteur du livre : ");
        String auteur = scanner.nextLine();
        System.out.print("Entrez la catégorie du livre : ");
        String categorie = scanner.nextLine();
        System.out.print("Entrez le nombre d'exemplaires : ");
        int nombre_Exemplaires = scanner.nextInt();

        Livre livre = new Livre(0, titre, auteur, categorie, nombre_Exemplaires);
        if (livreDAO.ajouterLivre(livre)) {
            System.out.println("Livre ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du livre.");
        }
    }


    public Livre rechercherParTitre(String titre) {
        Livre livre = null;
        String query = "SELECT * FROM livre WHERE titre = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, titre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titreLivre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String categorie = resultSet.getString("categorie");
                int nombre_Exemplaires = resultSet.getInt("nombre_Exemplaires");
                livre = new Livre(id, titreLivre, auteur, categorie, nombre_Exemplaires);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }


    public List<Livre> rechercherParCategorie(String categorie) {
        List<Livre> livre = new ArrayList<>();
        String query = "SELECT * FROM livre WHERE categorie = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categorie);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                String cat = resultSet.getString("categorie");
                int nombre_Exemplaires = resultSet.getInt("nombre_Exemplaires");
                livre.add(new Livre(id, titre, auteur, cat, nombre_Exemplaires));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    public boolean ajouterLivre(Livre livre) {
        String query = "INSERT INTO livre (titre, auteur, categorie, nombre_exemplaires) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getCategorie());
            preparedStatement.setInt(4, livre.getNombreExemplaires());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout du livre : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
