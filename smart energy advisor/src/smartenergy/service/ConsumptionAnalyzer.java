package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Performs advanced consumption analysis including:
 * - Consumption category classification (Low/Medium/High/Very High)
 * - Daily, Monthly, and Yearly energy projections
 * - Peak consumption source identification
 * - Percentage breakdown of each appliance's contribution
 *
 * @author Adhishree Singh
 * @version 1.0
 */
public class ConsumptionAnalyzer {

    // Consumption thresholds (kWh/month per appliance)
    private static final double LOW_THRESHOLD = 30.0;
    private static final double MEDIUM_THRESHOLD = 100.0;
    private static final double HIGH_THRESHOLD = 300.0;

    // Major source threshold (percentage of total)
    private static final double MAJOR_SOURCE_PERCENT = 20.0;

    /**
     * Classifies an appliance's monthly consumption into a category.
     *
     * @param monthlyUnits the monthly energy consumption in kWh
     * @return category string: "Low", "Medium", "High", or "Very High"
     */
    public String classifyConsumption(double monthlyUnits) {

        if (monthlyUnits < LOW_THRESHOLD) {
            return "Low";
        } else if (monthlyUnits < MEDIUM_THRESHOLD) {
            return "Medium";
        } else if (monthlyUnits < HIGH_THRESHOLD) {
            return "High";
        } else {
            return "Very High";
        }
    }

    /**
     * Calculates daily energy consumption from monthly units.
     *
     * @param monthlyUnits monthly consumption in kWh
     * @return daily consumption in kWh
     */
    public double getDailyConsumption(double monthlyUnits) {
        return monthlyUnits / 30.0;
    }

    /**
     * Calculates yearly energy consumption from monthly units.
     *
     * @param monthlyUnits monthly consumption in kWh
     * @return yearly consumption in kWh
     */
    public double getYearlyConsumption(double monthlyUnits) {
        return monthlyUnits * 12.0;
    }

    /**
     * Finds the appliance with the highest monthly consumption.
     *
     * @param appliances the list of appliances
     * @return the appliance with the highest consumption, or null if list is empty
     */
    public Appliance findPeakConsumer(ArrayList<Appliance> appliances) {

        if (appliances == null || appliances.isEmpty()) {
            return null;
        }

        Appliance peak = appliances.get(0);

        for (Appliance a : appliances) {
            if (a.getMonthlyUnits() > peak.getMonthlyUnits()) {
                peak = a;
            }
        }

        return peak;
    }

    /**
     * Calculates the percentage share of each appliance relative to total consumption.
     *
     * @param appliance  the appliance
     * @param totalUnits total monthly consumption
     * @return percentage share (0-100)
     */
    public double getPercentageShare(Appliance appliance, double totalUnits) {

        if (totalUnits == 0) {
            return 0;
        }

        return (appliance.getMonthlyUnits() / totalUnits) * 100;
    }

    /**
     * Displays a detailed consumption analysis report for all appliances.
     *
     * @param appliances the list of appliances
     * @param totalUnits total monthly energy consumption
     */
    public void showDetailedAnalysis(
            ArrayList<Appliance> appliances,
            double totalUnits) {

        System.out.println("\n============ DETAILED CONSUMPTION ANALYSIS ============");

        System.out.printf("%-18s %-10s %-10s %-12s %-10s%n",
                "Appliance", "Daily", "Monthly", "Yearly", "Category");

        System.out.println("-------------------------------------------------------");

        for (Appliance a : appliances) {

            double daily = getDailyConsumption(a.getMonthlyUnits());
            double yearly = getYearlyConsumption(a.getMonthlyUnits());
            String category = classifyConsumption(a.getMonthlyUnits());

            System.out.printf("%-18s %-10.2f %-10.2f %-12.2f %-10s%n",
                    a.getName(),
                    daily,
                    a.getMonthlyUnits(),
                    yearly,
                    category);
        }

        System.out.println("-------------------------------------------------------");

        // Show major sources
        System.out.println("\n--- Major Consumption Sources (>20% share) ---");

        boolean majorFound = false;

        for (Appliance a : appliances) {

            double percent = getPercentageShare(a, totalUnits);

            if (percent >= MAJOR_SOURCE_PERCENT) {

                majorFound = true;

                System.out.printf("  * %s uses %.1f%% of total electricity%n",
                        a.getName(), percent);
            }
        }

        if (!majorFound) {
            System.out.println("  No single appliance dominates consumption.");
            System.out.println("  Your usage is well distributed.");
        }

        // Show peak consumer
        Appliance peak = findPeakConsumer(appliances);

        if (peak != null) {
            System.out.printf("%n  Peak Consumer: %s (%.2f kWh/month)%n",
                    peak.getName(), peak.getMonthlyUnits());
        }

        System.out.println("========================================================");
    }
}
