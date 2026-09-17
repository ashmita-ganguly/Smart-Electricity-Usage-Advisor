package smartenergy.service;

/**
 * Unit tests for BudgetAnalyzer.
 * Tests budget comparison logic (over/under budget scenarios).
 *
 * @author Adhishree Singh
 */
public class BudgetAnalyzerTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("========== BudgetAnalyzer Tests ==========");

        testOverBudget();
        testWithinBudget();
        testExactBudget();
        testBudgetDifference();

        System.out.println("\n--- Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("==========================================");
    }

    /**
     * Test: Bill > Budget should return true for isOverBudget.
     */
    private static void testOverBudget() {

        BudgetAnalyzer analyzer = new BudgetAnalyzer();

        boolean result = analyzer.isOverBudget(1500.0, 1000.0);

        if (result) {
            System.out.println("  PASS: Over budget detection");
            passed++;
        } else {
            System.out.println("  FAIL: Over budget detection");
            failed++;
        }
    }

    /**
     * Test: Bill < Budget should return false for isOverBudget.
     */
    private static void testWithinBudget() {

        BudgetAnalyzer analyzer = new BudgetAnalyzer();

        boolean result = analyzer.isOverBudget(800.0, 1000.0);

        if (!result) {
            System.out.println("  PASS: Within budget detection");
            passed++;
        } else {
            System.out.println("  FAIL: Within budget detection");
            failed++;
        }
    }

    /**
     * Test: Bill == Budget should return false for isOverBudget.
     */
    private static void testExactBudget() {

        BudgetAnalyzer analyzer = new BudgetAnalyzer();

        boolean result = analyzer.isOverBudget(1000.0, 1000.0);

        if (!result) {
            System.out.println("  PASS: Exact budget (not over)");
            passed++;
        } else {
            System.out.println("  FAIL: Exact budget (not over)");
            failed++;
        }
    }

    /**
     * Test: Budget difference calculation.
     * Budget 1000, Bill 750 -> difference = 250 (positive = savings)
     * Budget 1000, Bill 1200 -> difference = -200 (negative = over)
     */
    private static void testBudgetDifference() {

        BudgetAnalyzer analyzer = new BudgetAnalyzer();

        double diff1 = analyzer.getBudgetDifference(750.0, 1000.0);
        assertEqual("Savings", 250.0, diff1);

        double diff2 = analyzer.getBudgetDifference(1200.0, 1000.0);
        assertEqual("Over budget diff", -200.0, diff2);
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
