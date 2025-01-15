import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Date;
public class MembreDAO {

    // Méthode pour inscrire un membre en utilisant la connexion de DBConnection
    public boolean inscrireMembre(Membre membre) {
        String query = "INSERT INTO membre (nom, prenom, email,  adhesion_date) VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Paramétrer la requête
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getEmail());
            preparedStatement.setDate(4, Date.valueOf(membre.getAdhesionDate()));

            // Exécuter la requête
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Retourne true si l'insertion a réussi

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur
        } finally {
        }
    }
}

