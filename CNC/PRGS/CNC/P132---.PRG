1 SIDEFILLER VANITY 855 X 700
2 85500 70000 1600
3 1
4 0
5 1
6 0
%
(TRIMMING RHS
@P1=DX-34
@P2=DY/2
@P3=DY-100
@P10=DX+30
@P11=DX
@P12=DY
@P13=DY+30
G72 X=P10 Y=P13 Z18 S18 E1 T25
G42 X=P11 Y=P12 Z18
G01 Y0 Z30
G40 X=P10 Y-30 Z0
G82 X28 Y100 Z8 F2 T19
G82 Y=P2 T19
G82 X28 Y=P3 Z8 F2 T19
G83 X28 Y=P3 Z8 F2 T20
G83 X28 Y=P2 T20
G83 Y100 T18
G00 X=P1 Y100 Z13 F1 T2
G00 X=P1 Y=P2 T2
G00 X=P1 Y=P3 Z13 F1 T2
G00 X34 Y=P3 Z13 F1 T2
G00 X34 Y=P2 T2
G00 X34 Y100 Z13 F1 T2
.
