cls
echo off

IDRVAX02 4 19200 19200 1 1

MOUSE

MET_SHEL /I

echo зддддддддддддддддддддддддддддддддддддддддддддд©
echo Ё                                             Ё
echo Ё **** CARICAMENTO  PROGRAMMA  FORATRICI **** цд©
echo Ё                                             Ё Ё
echo юддбдддддддддддддддддддддддддддддддддддддддддды Ё
echo    Ё   Versione da UFFICIO        (C)Tecnos     Ё
echo    юдддддддддддддддддддддддддддддддддддддддддддды

:MENU

MENU UMW302.EXE INT.EXE

if errorlevel 64 goto EXIT
if errorlevel 50 goto INT
if errorlevel 49 goto ISO

:ISO
UMW302.EXE
goto MENU

:INT
INT.EXE
goto MENU

:EXIT

PAUSE

MET_SHEL /K

cd PRGS
dr




