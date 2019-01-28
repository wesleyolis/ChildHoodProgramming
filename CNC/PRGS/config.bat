@echo off
IF NOT "%2" == "" GOTO second
upconfig.COM C:\CNC\PRGS\%1 A: INT:f\%1 DSK:A:
GOTO contine


:second
IF NOT EXIST %2 GOTO ERROR
upconfig.COM C:\CNC\PRGS\%1 C:\CNC\PRGS\%2 INT:f\%1 DSK:f\%2
GOTO contine


:ERROR
echo Error Folder '%2' not found
echo '
GOTO end


:contine
echo '
echo Press any key to launch CNC
echo '
pause 
cd ../
AUTOEXEC2.bat

:end

