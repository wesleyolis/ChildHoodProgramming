1 BOTTOM WITH 3 GROMMOT HOLES
2 86800 58000 1600
3 1
4 0
5 1
6 0
%
(TRIMMING RHS
@P1=DX-34
@P2=DY/2
@P3=DY-50
@P4=DX/2
@P10=DX+30
@P11=DX
@P12=DY
@P13=DY+30
G82 X28 Y50 Z8 F6 T19
G82 Y=P2 F6 T19
G82 X28 Y=P3 Z8 F6 T19
G83 X28 Y=P3 Z8 T20
G83 X28 Y=P2 T20
G83 Y50 T20
G00 X=P1 Y50 Z13 F1 T2
G00 X=P1 Y=P2 T2
G00 X=P1 Y=P3 Z13 F1 T2
G00 X34 Y=P3 Z13 F1 T2
G00 X34 Y=P2 T2
G00 X34 Y50 Z13 F1 T2
.
