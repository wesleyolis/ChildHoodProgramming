1 CREDENZA DOOR FLUSH PULL
2 60000 45000 1600
3 1
4 0
5 1
6 0
%
@P1=96
@P2=P1+46
@P3=P1-46
G72 X=P1 Y40 Z13 S18 E1 T26
G42 X=P1 Y23
G01 X=P2 Y23
G02 Y57 R17
G01 X=P3
G02 Y23 R17
G01 X=P2
G01 Y48
G01 X=P3
G40 Y40
.
