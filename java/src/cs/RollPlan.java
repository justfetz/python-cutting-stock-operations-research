package cs;

import java.util.ArrayList;
import java.util.List;

public class RollPlan {
    private final String rollId;
    private final int stockWidth;
    private final List<Piece> pieces;
    private int usedWidth;

    public RollPlan(String rollId, int stockWidth) {
        this.rollId = rollId;
        this.stockWidth = stockWidth;
        this.pieces = new ArrayList<>();
        this.usedWidth = 0;
    }

    public boolean canFit(Piece piece) {
        return usedWidth + piece.getWidth() <= stockWidth;
    }

    public void add(Piece piece) {
        pieces.add(piece);
        usedWidth += piece.getWidth();
    }

    public String getRollId() { return rollId; }
    public int getStockWidth() { return stockWidth; }
    public int getUsedWidth() { return usedWidth; }
    public int getWaste() { return stockWidth - usedWidth; }
    public List<Piece> getPieces() { return pieces; }
}
