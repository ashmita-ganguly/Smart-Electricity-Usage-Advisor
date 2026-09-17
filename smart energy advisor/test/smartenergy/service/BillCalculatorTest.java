package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Unit tests for BillCalculator.
 * Validates bill computation with known rate and consumption values.
 *
 * @author Adhishree Singh
 */
public class BillCalculatorTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("========== BillCalculator Tests ==========");

        testSingleApplianceCost();
        testMultipleApplianceCost();
        testTotalBill();
        testZeroRate();

        System.out.println("\n--- Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("==========================================");
    }

    /**
     * Test: 30 kWh at Rs 8/unit = Rs 240
     */
    private static void testSingleApplianceCost() {

        BillCalculator calc = new BillCalculator();

        Appliance a = new Appliance("Fan", 100, 10);
        a.setMonthlyUnits(30.0);

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(a);

        calc.calculateApplianceCost(list, 8.0);

        assertEqual("Single cost (30 kWh @ Rs 8)",
                240.0, a.getMonthlyCost());
    }

    /**
     * Test: Multiple appliances with different consumption values.
     */
    private static void testMultipleApplianceCost() {

        BillCalculator calc = new BillCalculator();

        Appliance fan = new Appliance("Fan", 75, 8);
        fan.setMonthlyUnits(18.0);

        Appliance ac = new Appliance("AC", 1500, 6);
        ac.setMonthlyUnits(270.0);

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(fan);
        list.add(ac);

        calc.calculateApplianceCost(list, 5.0);

        // Fan: 18 * 5 = 90
        assertEqual("Fan cost", 90.0, fan.getMonthlyCost());

        // AC: 270 * 5 = 1350
        assertEqual("AC cost", 1350.0, ac.getMonthlyCost());
    }

    /**
     * Test: Total bill is sum of all appliance costs.
     */
    private static void testTotalBill() {

        BillCalculator calc = new BillCalculator();

        Appliance a = new Appliance("A", 100, 5);
        a.setMonthlyUnits(15.0);
        a.setMonthlyCost(120.0);

        Appliance b = new Appliance("B", 200, 3);
        b.setMonthlyUnits(18.0);
        b.setMonthlyCost(144.0);

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        double total = calc.calculateTotalBill(list);

        assertEqual("Total bill", 264.0, total);
    }

    /**
     * Test: Rate of Rs 0 should give Rs 0 cost.
     */
    private static void testZeroRate() {

        BillCalculator calc = new BillCalculator();

        Appliance a = new Appliance("Test", 500, 10);
        a.setMonthlyUnits(150.0);

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(a);

        calc.calculateApplianceCost(list, 0.0);

        assertEqual("Zero rate", 0.0, a.getMonthlyCost());
    }

    // ---- Assertion Helper ----

    private static void assertEqual(
            String testName,
            double expected,
            double actual) {

        if (Math.abs(expected - actual) < 0.01) {
            System.out.println("  PASS: " + testName);
            passed++;
        } else {
            System.out.println("  FAIL: " + testName
                    + " (expected=" + expected
                    + ", actual=" + actual + ")");
            failed++;
        }
    }
}
