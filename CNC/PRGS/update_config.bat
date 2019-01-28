@echo off
echo D %2\
echo P %1\
echo M C:\CNC\
echo E C:\CNC\PRG\
echo A C:\CNC\GRF\
echo G 1
echo S 5 38 38 500
echo F 640 320 0 16
echo C SYSTEM16.fnt
echo X 50
echo B 5 4
echo T A
echo O 1
echo H 3
echo N "%3   %4"
echo K 1
exit
