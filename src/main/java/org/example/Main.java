package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        try (Connection conn = DBConnection.getConnection(dotenv)) {
            if (conn != null) {
                System.out.println("La base de données est prête pour le Push-down processing !");
            }
        } catch (Exception e) {
            throw  new RuntimeException("Erreur lors de la connexion", e);
        }



    }
}