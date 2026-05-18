package cs.tests;

import cs.CuttingStockSolver;
import cs.OrderLine;
import cs.RollPlan;

import java.util.List;

public class TestCuttingStock {
    public static void main(String[] args) {
        List<OrderLine> orders = List.of(
            new OrderLine(100, "O1", 45, 2),
            new OrderLine(100, "O2", 36, 3),
            new OrderLine(100, "O3", 31, 2),
            new OrderLine(100, "O4", 14, 4)
        );

        List<RollPlan> rolls = new CuttingStockSolver().solve(orders);
        assertEquals(4, rolls.size(), "roll count");
        for (RollPlan roll : rolls) {
            if (roll.getUsedWidth() > roll.getStockWidth()) {
                throw new AssertionError("roll exceeds stock width");
            }
        }
        System.out.println("Java tests passed.");
    }

    private static void assertEquals(int expected, int actual, String label) {
        if (expected != actual) {
            throw new AssertionError("Expected " + label + " to be " + expected + " but was " + actual);
        }
    }
}
