package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Calculates the electricity bill for each appliance and the total bill.
 * Bill = Monthly Units × Rate per Unit.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class BillCalculator {

    /**
     * Calculates and sets the monthly cost for each appliance.
     *
     * @param appliances the list of appliances
     * @param rate       electricity rate per kWh (in Rs)
     */
    public void calculateApplianceCost(
            ArrayList<Appliance> appliances,
            double rate) {

        for (Appliance a : appliances) {

            double cost = a.getMonthlyUnits() * rate;

            a.setMonthlyCost(cost);
        }
    }

    /**
     * Returns the total monthly electricity bill across all appliances.
     *
     * @param appliances the list of appliances
     * @return total monthly bill in Rs
     */
    public double calculateTotalBill(
            ArrayList<Appliance> appliances) {

        double total = 0;

        for (Appliance a : appliances) {
            total = total + a.getMonthlyCost();
        }

        return total;
    }
}
