1 S TOPS 1500 X 800
2 160000 90000 3200
3 1
4 0
5 1
6 0
%
@P1=-30
@P2=75
@P3=149
@P4=DX/2
@P5=DX/4
@P6=DX/4*3
@P7=-30
@P8=DX
@P9=DX+30
@P10=P9+30
G72 X=P1 Y50 Z35 S18 E1 T26
G41 X0 Y=P2 Z38
G03 X=P4 Y=P2 R1150
G02 X=P8 Y=P2 R1150 Q16
G02 X=P9 Y=P2 R1150
G40 X=P10 Y=P1
.
