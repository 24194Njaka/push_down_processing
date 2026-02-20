package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever();

        // Q2
//        System.out.println("--- Liste des factures ---");
//        dataRetriever.findConfirmedAndPaidInvoiceTotals().forEach(System.out::println);

        // Q3
//        System.out.println(dataRetriever.computeStatusTotals());

        // Q4
//        System.out.println(dataRetriever.computeWeightedTurnover());

        // Q5
        dataRetriever.findInvoiceTaxSummaries().forEach(System.out::println);
    }
}