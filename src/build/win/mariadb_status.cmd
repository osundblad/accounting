@echo off
CLS

for /F "tokens=3 delims=: " %%H in ('sc query "MariaDB" ^| findstr "        STATE"') do (
  echo "MariaDB is %%H"
)

timeout 2 >nul
