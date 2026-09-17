package smartenergy;

import smartenergy.model.Appliance;
import smartenergy.service.*;
import smartenergy.utility.*;
import smartenergy.report.ReportGenerator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Main entry point for the Smart Energy Consumption Advisor application.
 *
 * This application helps household users track, analyze, and optimize
 * their electricity consumption across multiple appliances. It features
 * a menu-driven interface with the following capabilities:
 * - Add household appliances with wattage and usage details
 * - Calculate monthly energy consumption and estimated bills
 * - Rank appliances by consumption
 * - Analyze spending against a monthly budget
 * - Provide personalized energy-saving recommendations
 * - Perform detailed consumption analysis with projections
 * - Save/load data to/from CSV files
 * - Export reports to text files
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class Main {

    // Logger for application-level logging
    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Data store
        ArrayList<Appliance> appliances = new ArrayList<>();

        // Service instances
        InputValidator input = new InputValidator(sc);
        EnergyCalculator energy = new EnergyCalculator();
        BillCalculator bill = new BillCalculator();
        ApplianceRanking ranking = new ApplianceRanking();
        BudgetAnalyzer budgetAnalyzer = new BudgetAnalyzer();
        RecommendationEngine recommendation = new RecommendationEngine();
        ConsumptionAnalyzer analyzer = new ConsumptionAnalyzer();
        FileManager fileManager = new FileManager();
        ReportGenerator report = new ReportGenerator();

        LOGGER.info("Application started.");

        // ======================================
        // Welcome Banner
        // ======================================

        System.out.println("======================================================");
        System.out.println("         SMART ENERGY CONSUMPTION ADVISOR");
        System.out.println("======================================================");
        System.out.println("  Helps you track, analyze, and reduce your");
        System.out.println("  household electricity consumption.");
        System.out.println("======================================================");

        // ======================================
        // Initial Setup: Budget & Rate
        // ======================================

        double budget =
                input.getPositiveDouble(
                        "\nEnter your monthly electricity budget (Rs): ");

        double rate =
                input.getPositiveDouble(
                        "Enter electricity rate per unit/kWh (Rs): ");

        LOGGER.info("Budget set: Rs " + budget
                + ", Rate: Rs " + rate + "/kWh");

        // ======================================
        // Menu-Driven Loop
        // ======================================

        boolean running = true;

        while (running) {

            System.out.println("\n==================== MENU ====================");
            System.out.println("  1. Add Appliances");
            System.out.println("  2. View Energy Report");
            System.out.println("  3. View Appliance Ranking");
            System.out.println("  4. View Budget Analysis");
            System.out.println("  5. View Energy Saving Recommendations");
            System.out.println("  6. View Detailed Consumption Analysis");
            System.out.println("  7. Load Previously Saved Data");
            System.out.println("  8. Save Data & Export Report");
            System.out.println("  9. Exit");
            System.out.println("================================================");

            int choice = input.getIntInRange(
                    "Select option (1-9): ", 1, 9);

            switch (choice) {

                case 1:
                    // ---- Add Appliances ----
                    addAppliances(sc, input, appliances);
                    recalculate(appliances, energy, bill, rate);
                    LOGGER.info(appliances.size()
                            + " appliances registered.");
                    break;

                case 2:
                    // ---- View Report ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances added yet. "
                                + "Please add appliances first (Option 1).");
                    } else {
                        double totalUnits =
                                energy.getTotalUnits(appliances);
                        double totalBill =
                                bill.calculateTotalBill(appliances);
                        report.showReport(
                                appliances, totalUnits, totalBill);
                    }
                    break;

                case 3:
                    // ---- View Ranking ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances added yet.");
                    } else {
                        showRanking(ranking, appliances);
                    }
                    break;

                case 4:
                    // ---- Budget Analysis ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances added yet.");
                    } else {
                        double totalBill =
                                bill.calculateTotalBill(appliances);
                        budgetAnalyzer.checkBudget(
                                totalBill, budget);
                    }
                    break;

                case 5:
                    // ---- Recommendations ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances added yet.");
                    } else {
                        double totalUnits =
                                energy.getTotalUnits(appliances);
                        recommendation.giveRecommendations(
                                appliances, totalUnits);
                    }
                    break;

                case 6:
                    // ---- Detailed Analysis ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances added yet.");
                    } else {
                        double totalUnits =
                                energy.getTotalUnits(appliances);
                        analyzer.showDetailedAnalysis(
                                appliances, totalUnits);
                    }
                    break;

                case 7:
                    // ---- Load Saved Data ----
                    ArrayList<Appliance> loaded =
                            fileManager.loadAppliances();
                    if (!loaded.isEmpty()) {
                        appliances = loaded;
                        recalculate(appliances, energy, bill, rate);
                        LOGGER.info("Loaded "
                                + appliances.size()
                                + " appliances from file.");
                    }
                    break;

                case 8:
                    // ---- Save & Export ----
                    if (appliances.isEmpty()) {
                        System.out.println(
                                "\nNo appliances to save.");
                    } else {
                        fileManager.saveAppliances(appliances);

                        double totalUnits =
                                energy.getTotalUnits(appliances);
                        double totalBill =
                                bill.calculateTotalBill(appliances);

                        String reportStr =
                                report.generateSummaryReport(
                                        appliances,
                                        totalUnits,
                                        totalBill,
                                        budget);

                        fileManager.saveReport(reportStr);

                        LOGGER.info("Data and report exported.");
                    }
                    break;

                case 9:
                    // ---- Exit ----
                    running = false;
                    break;
            }
        }

        // ======================================
        // Final Summary
        // ======================================

        if (!appliances.isEmpty()) {

            double totalUnits =
                    energy.getTotalUnits(appliances);
            double totalBill =
                    bill.calculateTotalBill(appliances);

            System.out.println(
                    "\n=============== FINAL SUMMARY ===============");

            System.out.println(
                    "Total Appliances   : " + appliances.size());

            System.out.printf(
                    "Monthly Consumption: %.2f kWh%n", totalUnits);

            System.out.printf(
                    "Estimated Bill     : Rs %.2f%n", totalBill);

            System.out.printf(
                    "Monthly Budget     : Rs %.2f%n", budget);

            if (totalBill > budget) {
                System.out.println(
                        "Status             : OVER BUDGET");
            } else {
                System.out.println(
                        "Status             : WITHIN BUDGET");
            }

            System.out.println(
                    "=============================================");
        }

        System.out.println(
                "\nThank you for using Smart Energy "
                + "Consumption Advisor!");

        LOGGER.info("Application terminated normally.");

        sc.close();
    }

    // ======================================
    // Helper Methods
    // ======================================

    /**
     * Prompts the user to add one or more appliances.
     */
    private static void addAppliances(
            Scanner sc,
            InputValidator input,
            ArrayList<Appliance> appliances) {

        int n = input.getPositiveInt(
                "\nHow many appliances to add? ");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "\n--- Appliance " + (i + 1)
                    + " of " + n + " ---");

            System.out.print("Appliance name: ");
            String name = sc.nextLine();

            double wattage =
                    input.getPositiveDouble(
                            "Power rating (Watts): ");

            double hours =
                    input.getHours(
                            "Daily usage (Hours, 0-24): ");

            Appliance appliance =
                    new Appliance(name, wattage, hours);

            appliances.add(appliance);

            System.out.println(
                    "Added: " + appliance);
        }
    }

    /**
     * Recalculates energy and bill for all appliances.
     */
    private static void recalculate(
            ArrayList<Appliance> appliances,
            EnergyCalculator energy,
            BillCalculator bill,
            double rate) {

        energy.calculateAll(appliances);
        bill.calculateApplianceCost(appliances, rate);
    }

    /**
     * Displays the appliance ranking by consumption.
     */
    private static void showRanking(
            ApplianceRanking ranking,
            ArrayList<Appliance> appliances) {

        ArrayList<Appliance> ranked =
                ranking.rank(appliances);

        System.out.println(
                "\n====== APPLIANCE RANKING (by consumption) ======");

        int position = 1;

        for (Appliance a : ranked) {

            System.out.printf(
                    "%d. %s -> %.2f kWh/month%n",
                    position,
                    a.getName(),
                    a.getMonthlyUnits());

            position++;
        }

        System.out.println(
                "================================================");
    }
}
