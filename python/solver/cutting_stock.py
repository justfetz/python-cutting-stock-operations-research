from .models import OrderLine, Piece


def _expand_pieces(orders: list[OrderLine]) -> list[Piece]:
    pieces: list[Piece] = []
    for order in orders:
        for index in range(1, order.quantity + 1):
            pieces.append(Piece(f"{order.order_id}_P{index}", order.order_id, order.piece_width))
    return pieces


def solve_first_fit_decreasing(orders: list[OrderLine]) -> list[dict]:
    if not orders:
        return []

    stock_width = orders[0].stock_width
    pieces = sorted(_expand_pieces(orders), key=lambda item: (-item.width, item.piece_id))
    rolls: list[dict] = []

    for piece in pieces:
        assigned = False
        for roll in rolls:
            if roll["used_width"] + piece.width <= stock_width:
                roll["pieces"].append(piece)
                roll["used_width"] += piece.width
                roll["waste"] = stock_width - roll["used_width"]
                assigned = True
                break
        if not assigned:
            rolls.append({
                "roll_id": f"R{len(rolls) + 1}",
                "pieces": [piece],
                "used_width": piece.width,
                "waste": stock_width - piece.width,
            })

    return rolls
