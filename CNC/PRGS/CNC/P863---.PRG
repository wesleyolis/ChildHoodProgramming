1 INGA SIDE ROUTERING 20MM ROUND
2 55000 50000 1600
3 1
4 0
5 1
6 0
%
(ROUTERING ALL ROUND
@P1=DX
@P2=DX-16
@P3=DY
@P4=DY-16
@P5=DY+30
G72 X30 Y=P5 Z18 S18 E1 T25
G41 X16 Y=P3
G02 X0 Y=P4 R16
G01 Y16
G02 X16 Y0 R16
G40 X30 Y-30
.
