package smartenergy.report;

import smartenergy.model.Appliance;

import java.util.ArrayList;

/**
 * Generates formatted energy consumption reports for display and file export.
 * Provides both console output and string-based report generation.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class ReportGenerator {

    /**
     * Displays a formatted energy consumption report to the console.
     *
     * @param appliances the list of appliances
     * @param totalUnits total monthly energy consumption (kWh)
     * @param totalBill  total estimated monthly bill (Rs)
     */
    public void showReport(
            ArrayList<Appliance> appliances,
            double totalUnits,
            double totalBill) {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("       SMART ENERGY CONSUMPTION REPORT");
        System.out.println("==============================================");

        System.out.printf(
                "%-20s %-12s %-12s %-10s%n",
                "Appliance",
                "Units(kWh)",
                "Cost(Rs)",
                "Share(%)");

        System.out.println("----------------------------------------------");

        for (Appliance a : appliances) {

            double percent = 0;
            if (totalUnits != 0) {
                percent = (a.getMonthlyUnits() / totalUnits) * 100;
            }

            System.out.printf(
                    "%-20s %-12.2f Rs %-9.2f %-10.1f%n",
                    a.getName(),
                    a.getMonthlyUnits(),
                    a.getMonthlyCost(),
                    percent);
        }

        System.out.println("----------------------------------------------");

        System.out.printf(
                "Total Consumption : %.2f kWh%n",
                totalUnits);

        System.out.printf(
                "Estimated Bill    : Rs %.2f%n",
                totalBill);

        System.out.println(
                "==============================================");
    }

    /**
     * Generates a summary report as a String (for file export).
     *
     * @param appliances the list of appliances
     * @param totalUnits total monthly consumption
     * @param totalBill  total estimated bill
     * @param budget     user's monthly budget
     * @return the complete report as a formatted string
     */
    public String generateSummaryReport(
            ArrayList<Appliance> appliances,
            double totalUnits,
            double totalBill,
            double budget) {

        StringBuilder sb = new StringBuilder();

        sb.append("==============================================\n");
        sb.append("    SMART ENERGY CONSUMPTION ADVISOR REPORT\n");
        sb.append("==============================================\n\n");

        sb.append(String.format(
                "%-20s %-12s %-12s %-10s%n",
                "Appliance",
                "Units(kWh)",
                "Cost(Rs)",
                "Share(%)"));

        sb.append("----------------------------------------------\n");

        for (Appliance a : appliances) {

            double percent = 0;
            if (totalUnits != 0) {
                percent = (a.getMonthlyUnits() / totalUnits) * 100;
            }

            sb.append(String.format(
                    "%-20s %-12.2f Rs %-9.2f %-10.1f%n",
                    a.getName(),
                    a.getMonthlyUnits(),
                    a.getMonthlyCost(),
                    percent));
        }

        sb.append("----------------------------------------------\n");
        sb.append(String.format("Total Consumption : %.2f kWh%n", totalUnits));
        sb.append(String.format("Estimated Bill    : Rs %.2f%n", totalBill));
        sb.append(String.format("Monthly Budget    : Rs %.2f%n", budget));

        if (totalBill > budget) {
            sb.append(String.format(
                    "Status            : OVER BUDGET by Rs %.2f%n",
                    totalBill - budget));
        } else {
            sb.append(String.format(
                    "Status            : WITHIN BUDGET (Rs %.2f remaining)%n",
                    budget - totalBill));
        }

        sb.append("==============================================\n");

        return sb.toString();
    }
}
