package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Calculates monthly energy consumption (in kWh) for household appliances.
 * Uses the standard formula: (Wattage × Hours × Days) / 1000.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class EnergyCalculator {

    private static final int DAYS_IN_MONTH = 30;

    /**
     * Calculates and sets the monthly energy consumption for a single appliance.
     *
     * @param appliance the appliance to calculate energy for
     */
    public void calculateApplianceEnergy(Appliance appliance) {

        double units = (appliance.getWattage()
                * appliance.getDailyHours()
                * DAYS_IN_MONTH) / 1000;

        appliance.setMonthlyUnits(units);
    }

    /**
     * Calculates monthly energy consumption for all appliances in the list.
     *
     * @param appliances the list of appliances
     */
    public void calculateAll(ArrayList<Appliance> appliances) {

        for (Appliance a : appliances) {
            calculateApplianceEnergy(a);
        }
    }

    /**
     * Returns the total monthly energy consumption across all appliances.
     *
     * @param appliances the list of appliances
     * @return total monthly consumption in kWh
     */
    public double getTotalUnits(ArrayList<Appliance> appliances) {

        double total = 0;

        for (Appliance a : appliances) {
            total = total + a.getMonthlyUnits();
        }

        return total;
    }
}
