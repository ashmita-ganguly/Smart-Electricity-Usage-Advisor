package smartenergy.model;

/**
 * Unit tests for the Appliance model class.
 * Tests constructor initialization, getters, setters, and toString.
 *
 * @author Adhishree Singh
 */
public class ApplianceTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("========== Appliance Model Tests ==========");

        testConstructorAndGetters();
        testSetters();
        testToString();
        testDefaultValues();

        System.out.println("\n--- Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("=============================================");
    }

    /**
     * Test: Constructor sets name, wattage, dailyHours correctly.
     */
    private static void testConstructorAndGetters() {

        Appliance a = new Appliance("Fan", 75.0, 8.0);

        assertStringEqual("Name getter",
                "Fan", a.getName());

        assertEqual("Wattage getter",
                75.0, a.getWattage());

        assertEqual("DailyHours getter",
                8.0, a.getDailyHours());
    }

    /**
     * Test: Setters for monthlyUnits and monthlyCost.
     */
    private static void testSetters() {

        Appliance a = new Appliance("AC", 1500, 6);

        a.setMonthlyUnits(270.0);
        assertEqual("setMonthlyUnits",
                270.0, a.getMonthlyUnits());

        a.setMonthlyCost(1350.0);
        assertEqual("setMonthlyCost",
                1350.0, a.getMonthlyCost());
    }

    /**
     * Test: toString format.
     */
    private static void testToString() {

        Appliance a = new Appliance("Heater", 2000, 3);

        String expected = "Heater - 2000.0W - 3.0 hours/day";
        String actual = a.toString();

        assertStringEqual("toString format",
                expected, actual);
    }

    /**
     * Test: monthlyUnits and monthlyCost default to 0.
     */
    private static void testDefaultValues() {

        Appliance a = new Appliance("Lamp", 40, 5);

        assertEqual("Default monthlyUnits",
                0.0, a.getMonthlyUnits());

        assertEqual("Default monthlyCost",
                0.0, a.getMonthlyCost());
    }

    // ---- Assertion Helpers ----

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

    private static void assertStringEqual(
            String testName,
            String expected,
            String actual) {

        if (expected.equals(actual)) {
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
