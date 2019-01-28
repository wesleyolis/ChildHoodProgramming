@echo off
echo %cd%
upconfig.COM %cd% A: INT:%1 DSK:A:

echo "Press any key to launch CNC"
pause 
cd..
AUTOEXEC2.bat


:END