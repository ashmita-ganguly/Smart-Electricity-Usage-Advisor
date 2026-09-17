package smartenergy.model;

/**
 * Represents a household appliance with its power rating and usage details.
 * This is the core data model used across all modules of the application.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class Appliance {

    private String name;
    private double wattage;
    private double dailyHours;
    private double monthlyUnits;
    private double monthlyCost;

    /**
     * Constructs an Appliance with the given name, wattage, and daily usage hours.
     *
     * @param name       the name of the appliance
     * @param wattage    power rating in Watts
     * @param dailyHours average daily usage in hours (0-24)
     */
    public Appliance(String name, double wattage, double dailyHours) {
        this.name = name;
        this.wattage = wattage;
        this.dailyHours = dailyHours;
    }

    // ==================== Getters ====================

    public String getName() {
        return name;
    }

    public double getWattage() {
        return wattage;
    }

    public double getDailyHours() {
        return dailyHours;
    }

    public double getMonthlyUnits() {
        return monthlyUnits;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    // ==================== Setters ====================

    public void setMonthlyUnits(double monthlyUnits) {
        this.monthlyUnits = monthlyUnits;
    }

    public void setMonthlyCost(double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    /**
     * Returns a human-readable representation of the appliance.
     *
     * @return formatted string with name, wattage, and daily hours
     */
    @Override
    public String toString() {
        return name + " - " + wattage + "W - "
                + dailyHours + " hours/day";
    }
}
