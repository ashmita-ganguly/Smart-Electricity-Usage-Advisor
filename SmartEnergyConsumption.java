import java.util.*;

public class SmartEnergyConsumption {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] appliance = new String[20];
        double[] watt = new double[20];
        double[] hours = new double[20];
        double[] monthlyUnits = new double[20];

        int n = 0;

        System.out.println("WELCOME TO OUR SMART ENERGY CONSUMPTION ADVISOR");

        System.out.println("Enter the household appliance type:");
        System.out.println("(Enter 'done' when you don't want to add more)");

        while (true) {

            System.out.println("Enter appliance name: ");
            String name = sc.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            appliance[n] = name;

            System.out.print("Enter wattage of " + name + " (in Watts): ");
            watt[n] = sc.nextDouble();

            System.out.print("Enter average usage per day in hours): ");
            hours[n] = sc.nextDouble();

            // Monthly consumption calculation
            monthlyUnits[n] = (watt[n] * hours[n] * 30) / 1000;

            n++;

            sc.nextLine();

            if (n == 20) {
                System.out.println("Maximum appliances.");
                break;
            }
        }

        // If no appliances were entered
        if (n == 0) {
            System.out.println("No appliances entered.");
            sc.close();
            return;
        }

        // ------------------------------------------------
        // TOTAL ENERGY CONSUMPTION
        // ------------------------------------------------

        double totalUnits = 0;

        for (int i = 0; i < n; i++) {
            totalUnits = totalUnits + monthlyUnits[i];
        }

        System.out.println("MONTHLY ENERGY USAGE");

        for (int i = 0; i < n; i++) {

            System.out.println(appliance[i] + " : "
                    + String.format("%.2f", monthlyUnits[i])
                    + " kWh/month");
        }

        System.out.println("Total Consumption = "
                + String.format("%.2f", totalUnits) + " kWh");

        // ------------------------------------------------
        // ELECTRICITY BILL CALCULATION
        // ------------------------------------------------

        System.out.print("\nEnter electricity rate per unit (₹): ");
        double rate = sc.nextDouble();

        double estimatedBill = totalUnits * rate;

        System.out.println("\nEstimated Electricity Bill = ₹"
                + String.format("%.2f", estimatedBill));

        // ------------------------------------------------
        // APPLIANCE RANKING
        // ------------------------------------------------

        System.out.println("       APPLIANCES RANKED BY CONSUMPTION");

        // Simple sorting
        for (int i = 0; i < n - 1; i++) {

            for (int j = i + 1; j < n; j++) {

                if (monthlyUnits[j] > monthlyUnits[i]) {

                    double temp = monthlyUnits[i];
                    monthlyUnits[i] = monthlyUnits[j];
                    monthlyUnits[j] = temp;

                    String tempName = appliance[i];
                    appliance[i] = appliance[j];
                    appliance[j] = tempName;

                    double tempWatt = watt[i];
                    watt[i] = watt[j];
                    watt[j] = tempWatt;

                    double tempHour = hours[i];
                    hours[i] = hours[j];
                    hours[j] = tempHour;
                }
            }
        }

        for (int i = 0; i < n; i++) {

            System.out.println((i + 1) + ". "
                    + appliance[i] + " -> "
                    + String.format("%.2f", monthlyUnits[i])
                    + " kWh");
        }

        // ------------------------------------------------
        // BUDGET COMPARISON
        // ------------------------------------------------

        System.out.print("\nEnter your monthly electricity budget (₹): ");
        double budget = sc.nextDouble();

        System.out.println("              BUDGET ANALYSIS");
        if (estimatedBill > budget) {

            double extra = estimatedBill - budget;

            System.out.println("WARNING: Your estimated bill is above the budget.");
            System.out.println("Budget = ₹" + budget);
            System.out.println("Estimated Bill = ₹"
                    + String.format("%.2f", estimatedBill));

            System.out.println("Extra amount = ₹"
                    + String.format("%.2f", extra));

        } else {

            double remaining = budget - estimatedBill;

            System.out.println("Your estimated bill is within the budget.");
            System.out.println("Remaining budget = ₹"
                    + String.format("%.2f", remaining));
        }

        // ------------------------------------------------
        // MAJOR CONSUMPTION SOURCES
        // ------------------------------------------------

        System.out.println("         MAJOR CONSUMPTION SOURCES");

        for (int i = 0; i < n; i++) {

            double percentage =
                    (monthlyUnits[i] / totalUnits) * 100;

            if (percentage >= 20) {

                System.out.println(appliance[i]
                        + " is a major consumption source.");

                System.out.println("It uses "
                        + String.format("%.2f", percentage)
                        + "% of total electricity.");
            }
        }

        // ------------------------------------------------
        // PERSONALIZED RECOMMENDATIONS
        // ------------------------------------------------

        System.out.println("       PERSONALIZED SAVING SUGGESTIONS");

        boolean suggestionFound = false;

        for (int i = 0; i < n; i++) {

            double percentage =
                    (monthlyUnits[i] / totalUnits) * 100;

            if (percentage >= 20) {

                suggestionFound = true;

                System.out.println("\nFor " + appliance[i] + ":");

                if (hours[i] > 8) {

                    System.out.println("- Usage is quite high.");
                    System.out.println("- Try reducing daily usage.");
                    System.out.println("- Avoid keeping it ON when not required.");

                } else if (watt[i] > 1000) {

                    System.out.println("- This appliance has high power consumption.");
                    System.out.println("- Use an energy-efficient model if possible.");
                    System.out.println("- Try to reduce unnecessary usage.");

                } else {

                    System.out.println("- Monitor its daily usage.");
                    System.out.println("- Switch it OFF when not required.");
                }
            }
        }

        if (!suggestionFound) {

            System.out.println("No appliance is consuming more than 20% individually.");
            System.out.println("Your electricity usage is relatively distributed.");
            System.out.println("Continue monitoring high-usage appliances.");
        }

        // ------------------------------------------------
        // FINAL SUMMARY
        // ------------------------------------------------

        System.out.println("                 FINAL SUMMARY");

        System.out.println("Total Appliances : " + n);

        System.out.println("Monthly Consumption : "
                + String.format("%.2f", totalUnits) + " kWh");

        System.out.println("Estimated Bill : ₹"
                + String.format("%.2f", estimatedBill));

        System.out.println("Monthly Budget : ₹"
                + String.format("%.2f", budget));

        if (estimatedBill > budget) {
            System.out.println("Status : OVER BUDGET");
        } else {
            System.out.println("Status : WITHIN BUDGET");
        }

        System.out.println("\nThank you for using Smart Energy Consumption Advisor!");

        sc.close();
    }
}
