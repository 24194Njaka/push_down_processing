package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL      = dotenv.get("DB_URL");
    private static final String USER     = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    private DBConnection() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static Connection getConnection(Dotenv dotenv) throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Vérification que les variables sont présentes
                if (URL == null || USER == null || PASSWORD == null) {
                    throw new IllegalStateException("Erreur : Les variables d'environnement DB_URL, DB_USER ou DB_PASSWORD ne sont pas définies.");
                }

                // Création de la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à PostgreSQL réussie !");
            } catch (SQLException e) {
                System.err.println("Erreur de connexion : " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}


