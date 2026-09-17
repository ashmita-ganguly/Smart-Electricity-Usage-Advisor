package smartenergy.service;

import smartenergy.model.Appliance;
import java.util.ArrayList;

/**
 * Unit tests for EnergyCalculator.
 * Validates energy consumption calculations using known input/output values.
 *
 * @author Adhishree Singh
 */
public class EnergyCalculatorTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("========== EnergyCalculator Tests ==========");

        testSingleApplianceCalculation();
        testMultipleAppliances();
        testZeroWattage();
        testZeroHours();
        testTotalUnits();

        System.out.println("\n--- Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("============================================");
    }

    /**
     * Test: 100W appliance used 10 hours/day
     * Expected: (100 * 10 * 30) / 1000 = 30 kWh
     */
    private static void testSingleApplianceCalculation() {

        EnergyCalculator calc = new EnergyCalculator();
        Appliance a = new Appliance("TestFan", 100, 10);

        calc.calculateApplianceEnergy(a);

        double expected = 30.0;

        assertEqual("Single appliance (100W, 10h)",
                expected, a.getMonthlyUnits());
    }

    /**
     * Test: Calculate energy for multiple appliances.
     */
    private static void testMultipleAppliances() {

        EnergyCalculator calc = new EnergyCalculator();

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(new Appliance("Fan", 75, 8));
        list.add(new Appliance("AC", 1500, 6));

        calc.calculateAll(list);

        // Fan: (75*8*30)/1000 = 18
        assertEqual("Fan energy", 18.0,
                list.get(0).getMonthlyUnits());

        // AC: (1500*6*30)/1000 = 270
        assertEqual("AC energy", 270.0,
                list.get(1).getMonthlyUnits());
    }

    /**
     * Test: 0W appliance should produce 0 kWh.
     */
    private static void testZeroWattage() {

        EnergyCalculator calc = new EnergyCalculator();
        Appliance a = new Appliance("ZeroWatt", 0, 5);

        calc.calculateApplianceEnergy(a);

        assertEqual("Zero wattage", 0.0,
                a.getMonthlyUnits());
    }

    /**
     * Test: 0 hours usage should produce 0 kWh.
     */
    private static void testZeroHours() {

        EnergyCalculator calc = new EnergyCalculator();
        Appliance a = new Appliance("NoUse", 500, 0);

        calc.calculateApplianceEnergy(a);

        assertEqual("Zero hours", 0.0,
                a.getMonthlyUnits());
    }

    /**
     * Test: Total units across all appliances.
     */
    private static void testTotalUnits() {

        EnergyCalculator calc = new EnergyCalculator();

        ArrayList<Appliance> list = new ArrayList<>();
        list.add(new Appliance("A", 100, 10));  // 30
        list.add(new Appliance("B", 200, 5));   // 30

        calc.calculateAll(list);

        double total = calc.getTotalUnits(list);

        assertEqual("Total units", 60.0, total);
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
