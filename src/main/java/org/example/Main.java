package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connecté à : " + conn.getMetaData().getURL());

            // Test de la Question 1
            DataRetriever retriever = new DataRetriever();
            System.out.println("--- Liste des factures ---");
            retriever.findInvoiceTotals().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }


        DataRetriever dataRetriever = new DataRetriever();
        dataRetriever.findInvoiceTotals();



    }
}