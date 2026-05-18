from pathlib import Path
import sys

sys.path.append(str(Path(__file__).resolve().parents[1]))

from solver.cutting_stock import solve_first_fit_decreasing
from solver.io_utils import read_orders


def test_first_fit_decreasing_sample():
    orders = read_orders(Path(__file__).resolve().parents[2] / "input" / "sample_orders.csv")
    rolls = solve_first_fit_decreasing(orders)
    assert len(rolls) == 4
    for roll in rolls:
        assert roll["used_width"] <= 100
