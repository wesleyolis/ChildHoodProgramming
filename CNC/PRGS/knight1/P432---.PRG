1 ROL DOOR DIV FOR SHELF
2 66400 45000 1600
3 1
4 0
5 1
6 0
%
(TO BE RUN IN MIRROR IMAGE IF DIVISION ON LHS
(TRIMMING ON END AND FRONT
@P1=DX-181
@P2=DY-50
@P3=50
@P4=DY-50
@T81=7,8,9
@X81=9,8,7
G72 X48 Y=P3 Z13 T81
G86 X=P1 F2 T81
G72 X48 Y=P2 T81
G86 X=P1 Y=P2 T81
@P10=DX+30
@P11=DX
@P12=DY
@P13=DY+30
G72 X-30 Y=P13 Z18 S18 E1 T25
G42 X0 Y=P12 Z18
G01 X=P11 Y=P12 Z35
G01 Y0 Z18
G40 X=P10 Y-30 Z18
G83 X27 Y50 Z8 F2 T18
G83 Y=P4 T20
G82 T19
G82 Y50 T17
.
