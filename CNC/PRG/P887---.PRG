1 ROUND
2 20000 20000 1600
3 1
4 0
5 1
6 0
%
(P7= RADIOUS
@P7=100
@P1=DX-30
@P2=DX/2
@P3=-30
@P4=DY/2
@P5=DY
@P6=DX
G00 X=P7 Y=P7 Z9 F1 T4
G72 X=P2 Y=P3 Z20 S18 E1 T25
G41 Y0
G02 X=P6 Y=P4 R=P7
G02 X=P2 Y=P5 R=P7
G02 X0 Y=P4 R=P7
G02 X=P2 Y0 R=P7
G40 Y=P3
.
