1 ROUTER CAROLS UCT 1/2 1/2
2 120000 30000 2200
3 1
4 0
5 1
6 0
%
@P1=DX/2
@P2=DY/2
@P3=(DX/2)-11
@P4=(DX/2)+11
@P5=DY-30
@P6=DY
G72 X=P1 Y=P5 Z24 S18 E1 T25
G42 X=P3 Y=P6 F6
G01 Y=P2
G01 X=P4 Z26
G01 Y=P6
G40 X=P1 Y=P5
.
