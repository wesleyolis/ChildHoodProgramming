1 CORE TOP ROUND ATHLONE COLLEGE
2 120000 120000 3200
3 1
4 0
5 1
6 0
%
@P1=DX
@P2=DY-325
@P3=DX-625
@P4=DY-625
@P5=DX-220
@P6=P1+32
G72 X325 Y-32 Z34 S18 E1 T25
G41 Y0 F4
G02 X=P3 Y220 R350
G03 X=P5 Y=P4 R650
G02 X=P1 Y=P2 R350
G01 X=P6
G40 X=P6 Z0
@P5=DY/2
@P6=250
@P7=P2-250
@P8=544
@P9=P8-45
@P10=P8-141
@P11=P8/2
@P12=P11-48
@P13=P11+48
G00 X=P12 Y100 Z23 F2 T1
G00 X=P13
G00 X=P13 Y196
G00 X=P12
G00 X46 Y=P7
G00 Y=P6
.
