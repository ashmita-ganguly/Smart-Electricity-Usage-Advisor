package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Provides personalized energy saving recommendations based on
 * each appliance's share of total consumption, wattage, and daily usage.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class RecommendationEngine {

    private static final double HIGH_USAGE_THRESHOLD = 15.0;
    private static final double HIGH_HOURS_THRESHOLD = 6.0;
    private static final double HIGH_WATTAGE_THRESHOLD = 1000.0;

    /**
     * Generates and displays personalized energy saving recommendations.
     * Appliances consuming more than 15% of total energy are flagged.
     *
     * @param appliances the list of household appliances
     * @param totalUnits the total monthly energy consumption (kWh)
     */
    public void giveRecommendations(
            ArrayList<Appliance> appliances,
            double totalUnits) {

        System.out.println("\n====== ENERGY SAVING RECOMMENDATIONS ======");

        boolean found = false;

        for (Appliance a : appliances) {

            double percentage = 0;

            if (totalUnits != 0) {
                percentage =
                        (a.getMonthlyUnits() / totalUnits) * 100;
            }

            if (percentage >= HIGH_USAGE_THRESHOLD) {

                found = true;

                System.out.println("\nAppliance: "
                        + a.getName());

                System.out.printf(
                        "Consumption Share: %.2f%%%n",
                        percentage);

                System.out.println(
                        "-> Try reducing its unnecessary usage.");

                System.out.println(
                        "-> Switch it off when not required.");

                if (a.getDailyHours() > HIGH_HOURS_THRESHOLD) {

                    System.out.println(
                            "-> Daily usage is quite high ("
                            + a.getDailyHours()
                            + " hrs). Try reducing the usage time.");
                }

                if (a.getWattage() > HIGH_WATTAGE_THRESHOLD) {

                    System.out.println(
                            "-> This appliance has high power "
                            + "consumption (" + a.getWattage()
                            + "W). An energy efficient "
                            + "model can be considered.");
                }
            }
        }

        if (!found) {

            System.out.println(
                    "No appliance crossed the high usage limit.");

            System.out.println(
                    "Still, avoiding unnecessary usage can "
                    + "help reduce electricity consumption.");
        }
    }
}
