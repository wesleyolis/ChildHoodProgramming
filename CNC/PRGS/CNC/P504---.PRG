1 1/2RD CORNER CORE 45 OFF HOLE
2 160000 160000 3200
3 1
4 0
5 1
6 0
%
@P1=651
@P2=DX
(Y IS EQUAL TO DY-P3 CHANGE P3 FOR DEPTH ON Y
@P3=651
@P4=DX+30
@P5=DY-P3
(CHANGE RADIOUS = P12  RADIOUS MAY NOT EXSEED DY-P3 1E 1000
(LESS DEPH 750 =250 RADIOUS MUST BE LESS THAN 250 IE 249
@P12=450
@P13=P3+P12
@P14=P1+P12
@P15=DX-P14
@P16=DY-P13
G72 X=P1 Y-30 Z39 S18 E1 T26
G41 Y0
G01 Y=P16
G03 X=P14 Y=P5 R=P12
G01 X=P2
G40 X=P4
@P5=DY/2
@P6=P5+200
@P7=250
@P8=P1
@P9=P8-45
@P10=P8-141
G00 X45 Y45 Z25 F2 T1
G00 X141
G00 X237
G00 X333
G00 X=P10
G00 X=P9
G00 X=P9 Y141
G00 X=P10
G00 X333
G00 X237
G00 X141
G00 X45
G00 X45 Y=P7
G00 Y=P6
.
