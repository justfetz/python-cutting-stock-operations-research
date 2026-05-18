package cs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CuttingStockSolver {
    public List<RollPlan> solve(List<OrderLine> orders) {
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }
        int stockWidth = orders.get(0).getStockWidth();
        List<Piece> pieces = expandPieces(orders);
        pieces.sort(Comparator.comparingInt(Piece::getWidth).reversed().thenComparing(Piece::getPieceId));

        List<RollPlan> rolls = new ArrayList<>();
        int rollCounter = 1;
        for (Piece piece : pieces) {
            boolean assigned = false;
            for (RollPlan roll : rolls) {
                if (roll.canFit(piece)) {
                    roll.add(piece);
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                RollPlan roll = new RollPlan("R" + rollCounter++, stockWidth);
                roll.add(piece);
                rolls.add(roll);
            }
        }
        return rolls;
    }

    private List<Piece> expandPieces(List<OrderLine> orders) {
        List<Piece> pieces = new ArrayList<>();
        for (OrderLine order : orders) {
            for (int index = 1; index <= order.getQuantity(); index++) {
                pieces.add(new Piece(order.getOrderId() + "_P" + index, order.getOrderId(), order.getPieceWidth()));
            }
        }
        return pieces;
    }
}
