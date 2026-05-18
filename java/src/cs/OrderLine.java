package cs;

public class OrderLine {
    private final int stockWidth;
    private final String orderId;
    private final int pieceWidth;
    private final int quantity;

    public OrderLine(int stockWidth, String orderId, int pieceWidth, int quantity) {
        this.stockWidth = stockWidth;
        this.orderId = orderId;
        this.pieceWidth = pieceWidth;
        this.quantity = quantity;
    }

    public int getStockWidth() { return stockWidth; }
    public String getOrderId() { return orderId; }
    public int getPieceWidth() { return pieceWidth; }
    public int getQuantity() { return quantity; }
}
