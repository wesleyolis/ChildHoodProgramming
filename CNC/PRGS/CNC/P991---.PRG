1 PEN TECH DRILLING SECOND RUN
2 160000 160000 3200
3 1
4 0
5 1
6 0
%
@P9=DX/2
@P2=P9+200
@P3=P9-200
@P4=DX-45
@P5=DX-141
(CHANGE P6 FOR DEPTH OF CORE)
@P6=700
@P7=P6-72
@P8=P6-168
G00 X=P3 Y72 Z25 T1
G00 X=P2
G00 X=P4
G00 Y168
G00 Y264
G00 Y360
G00 Y=P8
G00 Y=P7
G00 X=P5 Y=P7
G00 Y=P8
G00 Y360
G00 Y264
G00 Y168
G00 Y72
(NB! WATCH OUT FOR THE CORNER PAD
(RUN AT 20% OF AXIS SPEED FOR ROUTERING
@P20=DX-552
@P21=DX-592
@P22=DX-572
G72 X=P20 Y178 Z36 S18 E1 T25
G42 X=P21 Y178 F1
G02 I=P20 J178
G40 X=P22 Y178
@P1=DX+40
(P2 IS FIRST RADIOUS
@P2=411
(P3 IS SECOND RADIOUS
@P3=372
(P4 STANDARD LENGHT FOR THIS PROGRAM IS 1600MM
(P4 IS DISTANCE IN ON X IF PANEL INCREACES INCREACE EQUALLY
@P4=DX-300
(P5 IS SECOND DISTANCE IN ON X
@P5=DX-450
(P6 IS DEPTH OF WORK AREA
@P6=700
@P7=P6-50
(P8 IS 838 PLUS INCREACED LENGHT STANDARD IS (1600MM
(AS THE INCREASE HIPOTONUES
@P8=DX-838
@P9=P8+30
@P10=DX
G72 X=P1 Y=P6 Z36 S18 E1 T25
G41 X=P10 Z36
G01 X=P4 Q150
G02 X=P5 Y=P7 R=P2 Q140
G03 X=P8 Y=P8 R=P3
G40 X=P9 Z0
@P23=DX-200
G84 X=P23 Y25 Z16 F1 T22
G84 X300 Y25 Z16 F1 T22
.
