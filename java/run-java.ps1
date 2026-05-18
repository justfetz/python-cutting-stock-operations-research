$javaHome = "C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot\bin"
$javac = Join-Path $javaHome "javac.exe"
$java = Join-Path $javaHome "java.exe"

New-Item -ItemType Directory -Force out | Out-Null
& $javac -d out src\cs\*.java
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

& $java -cp .\out cs.Main ..\input\sample_orders.csv ..\output\cutting_plan_java.csv
exit $LASTEXITCODE
