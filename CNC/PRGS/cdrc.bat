@echo off
upconfig.COM C:\CNC\PRGS\%1 C:\CNC\PRGS\%2 INT:f\%1 DSK:f\%2

echo "Press any key to launch CNC"
pause 
cd ../
AUTOEXEC2.bat


