package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Ranks appliances by their monthly energy consumption in descending order.
 * Uses Java Collections Framework for efficient sorting (O(n log n)).
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class ApplianceRanking {

    /**
     * Returns a new list of appliances sorted by monthly consumption (highest first).
     * The original list is not modified.
     *
     * @param appliances the list of appliances to rank
     * @return a new sorted ArrayList of appliances
     */
    public ArrayList<Appliance> rank(
            ArrayList<Appliance> appliances) {

        ArrayList<Appliance> sortedList =
                new ArrayList<>(appliances);

        Collections.sort(sortedList,
                new Comparator<Appliance>() {

            @Override
            public int compare(
                    Appliance a1,
                    Appliance a2) {

                return Double.compare(
                        a2.getMonthlyUnits(),
                        a1.getMonthlyUnits());
            }
        });

        return sortedList;
    }
}
