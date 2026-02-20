package org.example;

import java.sql.Connection;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connecté à : " + conn.getMetaData().getURL());
        DataRetriever dataRetriever = new DataRetriever();

            // Test de la Question 2
//        System.out.println("--- Liste des factures ---");
//        List<InvoiceTotal> totals = dataRetriever.findConfirmedAndPaidInvoiceTotals();
//        totals.forEach(System.out::println);
            InvoiceStatusTotal totals = dataRetriever.computeStatusTotals();
            System.out.println(totals);



        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }







    }
}