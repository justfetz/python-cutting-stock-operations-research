from pathlib import Path
import csv
import sys

from solver.cp_sat_cutting_stock import solve_cp_sat
from solver.io_utils import read_orders


if __name__ == "__main__":
    if len(sys.argv) < 3:
        raise SystemExit("Usage: python main.py <orders.csv> <plan-output.csv>")

    orders = read_orders(Path(sys.argv[1]))
    rolls = solve_cp_sat(orders)

    with Path(sys.argv[2]).open("w", newline="", encoding="utf-8") as handle:
        writer = csv.writer(handle)
        writer.writerow(["roll_id", "piece_id", "order_id", "piece_width", "used_width", "waste"])
        for roll in rolls:
            for piece in roll["pieces"]:
                writer.writerow([roll["roll_id"], piece.piece_id, piece.order_id, piece.width, roll["used_width"], roll["waste"]])
