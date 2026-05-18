# Problem Notes

## First version

The current version treats cutting stock as a 1D roll-assignment problem. Demand rows are expanded into piece instances, then assigned to rolls.

## Why this matters

- it is a recognizable manufacturing optimization problem
- it connects naturally to paper mill and corrugator waste thinking
- it gives a clean heuristic-vs-exact comparison

## Current tracks

- first-fit decreasing heuristic in Java and Python
- exact CP-SAT roll minimization model in Python
