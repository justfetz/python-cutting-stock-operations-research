from pathlib import Path
import csv

from .models import OrderLine


def read_orders(path: Path) -> list[OrderLine]:
    with path.open("r", newline="", encoding="utf-8") as handle:
        reader = csv.DictReader(handle)
        return [
            OrderLine(int(row["stock_width"]), row["order_id"], int(row["piece_width"]), int(row["quantity"]))
            for row in reader
        ]
