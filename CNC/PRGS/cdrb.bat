@echo off
upconfig.COM C:\CNC\PRGS\%1 %2 INT:f\%1 DSK:%2
echo '
echo "Press any key to launch CNC"
pause 
cd ../
AUTOEXEC2.bat


