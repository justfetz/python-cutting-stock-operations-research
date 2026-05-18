from dataclasses import dataclass


@dataclass(frozen=True)
class OrderLine:
    stock_width: int
    order_id: str
    piece_width: int
    quantity: int


@dataclass(frozen=True)
class Piece:
    piece_id: str
    order_id: str
    width: int
