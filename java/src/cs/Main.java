package cs;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java cs.Main <orders.csv> <plan-output.csv>");
            return;
        }

        List<OrderLine> orders = new CsvOrdersReader().read(Path.of(args[0]));
        List<RollPlan> rolls = new CuttingStockSolver().solve(orders);
        new CsvPlanWriter().write(Path.of(args[1]), rolls);

        System.out.printf("Created %d roll plans.%n", rolls.size());
    }
}
