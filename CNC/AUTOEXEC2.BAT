cls
echo off

IDRVAX02 4 19200 19200 1 1

MOUSE

MET_SHEL /I

echo 旼컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
echo �                                             �
echo � **** CARICAMENTO  PROGRAMMA  FORATRICI **** 쳐�
echo �                                             � �
echo 읕컫컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴� �
echo    �   Versione da UFFICIO        (C)Tecnos     �
echo    읕컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴켸

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




