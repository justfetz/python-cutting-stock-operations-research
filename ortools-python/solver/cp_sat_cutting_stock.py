from ortools.sat.python import cp_model

from .models import OrderLine, Piece


def _expand_pieces(orders: list[OrderLine]) -> list[Piece]:
    pieces: list[Piece] = []
    for order in orders:
        for index in range(1, order.quantity + 1):
            pieces.append(Piece(f"{order.order_id}_P{index}", order.order_id, order.piece_width))
    return pieces


def solve_cp_sat(orders: list[OrderLine]) -> list[dict]:
    if not orders:
        return []

    stock_width = orders[0].stock_width
    pieces = _expand_pieces(orders)
    roll_count = len(pieces)

    model = cp_model.CpModel()
    use_roll = [model.NewBoolVar(f"use_roll_{r}") for r in range(roll_count)]
    assign = {}

    for p, piece in enumerate(pieces):
        for r in range(roll_count):
            assign[(p, r)] = model.NewBoolVar(f"assign_{p}_{r}")

    for p, piece in enumerate(pieces):
        model.Add(sum(assign[(p, r)] for r in range(roll_count)) == 1)

    for r in range(roll_count):
        model.Add(sum(pieces[p].width * assign[(p, r)] for p in range(len(pieces))) <= stock_width * use_roll[r])

    for r in range(roll_count - 1):
        model.Add(use_roll[r] >= use_roll[r + 1])

    model.Minimize(sum(use_roll))

    solver = cp_model.CpSolver()
    solver.parameters.max_time_in_seconds = 10
    solver.parameters.num_search_workers = 8
    status = solver.Solve(model)
    if status not in (cp_model.OPTIMAL, cp_model.FEASIBLE):
        raise RuntimeError("No feasible cutting plan returned by OR-Tools.")

    rolls: list[dict] = []
    for r in range(roll_count):
        if solver.Value(use_roll[r]) != 1:
            continue
        assigned_pieces = [pieces[p] for p in range(len(pieces)) if solver.Value(assign[(p, r)]) == 1]
        used_width = sum(piece.width for piece in assigned_pieces)
        rolls.append({
            "roll_id": f"R{len(rolls) + 1}",
            "pieces": sorted(assigned_pieces, key=lambda item: (-item.width, item.piece_id)),
            "used_width": used_width,
            "waste": stock_width - used_width,
        })

    return rolls
