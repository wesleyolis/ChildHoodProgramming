1 KC2 PEDENZA VANITY
2 211200 80600 1600
3 1
4 0
5 1
6 0
%
@P1=DX-34
@P2=DY/2
@P3=DY-100
G82 X28 Y100 Z8 F9 T19
G82 Y=P2 F9 T19
G82 X28 Y=P3 Z8 F9 T19
G83 X28 Y=P3 Z8 F9 T20
G83 X28 Y=P2 F9 T20
G83 Y100 F9 T20
G00 X=P1 Y100 Z13 F2 T2
G00 X=P1 Y=P2 T2
G00 X=P1 Y=P3 Z13 F2 T2
G00 X34 Y=P3 Z13 F2 T2
G00 X34 Y=P2 T2
G00 X34 Y100 Z13 F1 T2
@P4=DX/2
.
