1 45 DEG CUT STRAIGHT
2 56600 56600 3200
3 1
4 0
5 1
6 0
%
@P1=DY
@P2=DX+30
@P3=DX
@P4=DX-30
G72 X-30 Y=P1 Z38 S18 E1 T25
G41 X0
G01 X=P3 Y0
G40 X=P4 Y-30
.
