Write-Host "Running Java heuristic..."
Push-Location ".\java"
powershell -ExecutionPolicy Bypass -File .\run-java.ps1
Pop-Location

Write-Host "Running Python heuristic..."
Push-Location ".\python"
powershell -ExecutionPolicy Bypass -File .\run-python.ps1
Pop-Location

Write-Host "Running OR-Tools exact model..."
Push-Location ".\ortools-python"
powershell -ExecutionPolicy Bypass -File .\run-ortools-python.ps1
Pop-Location

Write-Host "Completed all runs."
