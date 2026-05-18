package cs;

public class Piece {
    private final String pieceId;
    private final String orderId;
    private final int width;

    public Piece(String pieceId, String orderId, int width) {
        this.pieceId = pieceId;
        this.orderId = orderId;
        this.width = width;
    }

    public String getPieceId() { return pieceId; }
    public String getOrderId() { return orderId; }
    public int getWidth() { return width; }
}
