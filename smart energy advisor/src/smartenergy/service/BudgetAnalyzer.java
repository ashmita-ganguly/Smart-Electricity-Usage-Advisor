package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Analyzes whether the estimated electricity bill fits within
 * the user's monthly budget and provides actionable feedback.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class BudgetAnalyzer {

    /**
     * Compares the estimated bill against the user's budget and
     * prints a detailed analysis with suggestions.
     *
     * @param bill   the estimated monthly electricity bill (in Rs)
     * @param budget the user's monthly electricity budget (in Rs)
     */
    public void checkBudget(double bill, double budget) {

        System.out.println("\n========== BUDGET ANALYSIS ==========");

        System.out.printf("Monthly Budget : Rs %.2f%n", budget);
        System.out.printf("Estimated Bill : Rs %.2f%n", bill);

        if (bill > budget) {

            double extra = bill - budget;
            double overPercent = (extra / budget) * 100;

            System.out.printf(
                    "Budget exceeded by : Rs %.2f (%.1f%% over)%n",
                    extra, overPercent);

            System.out.println(
                    "Suggestion: Reduce usage of high consuming appliances.");

        } else {

            double remaining = budget - bill;
            double savedPercent = (remaining / budget) * 100;

            System.out.printf(
                    "Amount remaining : Rs %.2f (%.1f%% saved)%n",
                    remaining, savedPercent);

            System.out.println(
                    "Your estimated bill is within the budget.");
        }
    }

    /**
     * Checks if the bill exceeds the budget.
     *
     * @param bill   the estimated bill
     * @param budget the user's budget
     * @return true if the bill exceeds the budget
     */
    public boolean isOverBudget(double bill, double budget) {
        return bill > budget;
    }

    /**
     * Returns the budget surplus or deficit amount.
     *
     * @param bill   the estimated bill
     * @param budget the user's budget
     * @return positive value = savings, negative value = over budget
     */
    public double getBudgetDifference(double bill, double budget) {
        return budget - bill;
    }
}
