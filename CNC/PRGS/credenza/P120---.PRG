1 CREDENZA ROLLDOOR DIV SHELVES
2 65800 47500 1600
3 1
4 0
5 1
6 0
%
(FOR DIV ON LHS OF CUPBOARD USE D MIRROR IMAGE
(FOR STEEL CHANGE P2=DY-83
(FOR WOODEN CHANGE P2=DY-62
@P1=DX-32
@P2=DY-83
@P3=DY-12
@P4=117
@P5=DX
@T81=7,8,9
@X81=9,8,7
G72 X70 Y=P4 Z13 T81
G86 X=P1 F2 T81
G72 X70 Y=P2 T81
G86 X=P1 Y=P2 T81
@P7=DY-30
@P8=DX-34
@P10=DY-10
G00 X34 Y28 Z13.5 F.5 T2
G82 X29 Y28 Z8 F2 T19
G00 X34 Y=P7 Z13.5 F.5 T2
G82 X29 Z8 F2 T17
G00 X=P8 Y28 Z13 F.5 T2
G83 X29 Z8 F2 T18
G00 X=P8 Y=P7 Z13 F.5 T2
G83 X29 Z8 F2 T20
G72 X=P5 Y=P10 Z8 E1 T23
G80 X0 F3 T23
.
