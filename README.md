# Cutting Stock Operations Research

Runnable Java, Python, and OR-Tools implementations of a simple 1D cutting stock problem.

This repo is meant to present the same manufacturing-flavored optimization problem through:

- Java first-fit decreasing heuristic
- Python first-fit decreasing heuristic
- Python OR-Tools CP-SAT exact model

## Problem

Given a stock width and a set of order widths with quantities, assign pieces to stock rolls while:

- respecting the stock width capacity of each roll
- satisfying all demand pieces
- minimizing the number of rolls used

## Repository layout

```text
python-cutting-stock-operations-research/
  input/
  output/
  docs/
  java/
  python/
  ortools-python/
```

## Reproducible commands

Java:

```powershell
cd java
.\run-java.ps1
.\run-java-tests.ps1
```

Python:

```powershell
cd python
.\run-python.ps1
python -m pytest tests\test_cutting_stock.py
```

OR-Tools Python:

```powershell
cd ortools-python
.\run-ortools-python.ps1
python -m pytest tests\test_cp_sat_cutting_stock.py
```

Run all:

```powershell
.\run-all.ps1
```

## Sample input

- [`input/sample_orders.csv`](./input/sample_orders.csv)

## Verified outputs

- [`output/cutting_plan_java.csv`](./output/cutting_plan_java.csv)
- [`output/cutting_plan_python.csv`](./output/cutting_plan_python.csv)
- [`output/cutting_plan_ortools_python.csv`](./output/cutting_plan_ortools_python.csv)

## Next steps

1. Add trim-loss reporting and waste percentage summaries.
2. Add column generation for larger instances.
3. Add multi-machine / multiple stock-width variants.
