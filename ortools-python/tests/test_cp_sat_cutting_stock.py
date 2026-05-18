from pathlib import Path
import sys

sys.path.append(str(Path(__file__).resolve().parents[1]))

from solver.cp_sat_cutting_stock import solve_cp_sat
from solver.io_utils import read_orders


def test_cp_sat_sample():
    orders = read_orders(Path(__file__).resolve().parents[2] / "input" / "sample_orders.csv")
    rolls = solve_cp_sat(orders)
    assert len(rolls) == 4
    all_piece_ids = {piece.piece_id for roll in rolls for piece in roll["pieces"]}
    assert len(all_piece_ids) == 11
    for roll in rolls:
        assert roll["used_width"] <= 100
