1 PENTECH VANITIES 100 CUT OFF
2 100500 45000 1600
3 1
4 0
5 1
6 0
%
@P1=DX+30
@P2=-30
@P3=DX
@P4=DX-100
G72 X-30 Y100 Z19 S18 E1 T27
G41 X0 Z19 F4
G01 X100 Y0 Z19 F4
G40 Y-30
G72 X=P4 Y-30 Z19 S18 E1 T27
G41 Y0 Z19 F4
G01 X=P3 Y100
G40 X=P1
.
