1 KF4 4DRAW/FILL BOT 440 X 570
2 44000 57000 1600
3 1
4 0
5 1
6 0
%
@P1=DX-34
@P3=DY-50
@P4=DX/2+120
@P5=DX/2-120
@P6=DY/2
G82 X28 Y50 Z8 F2 T19
G82 X28 Y=P6 Z8 F2 T19
G82 X28 Y=P3 Z8 F2 T19
G83 X28 Y=P3 Z8 F2 T20
G83 X28 Y=P6 Z8 F2 T18
G83 X28 Y50 Z8 F2 T18
G00 X=P1 Y50 Z13 F1 T2
G00 X=P1 Y=P6 Z13 F1 T2
G00 X=P1 Y=P3 Z13 F1 T2
G00 X34 Y=P3 Z13 F1 T2
G00 X34 Y=P6 Z13 F1 T2
G00 X34 Y50 Z13 F1 T2
G00 X=P5 Y30 Z10 F2 T6
G00 X=P4 T6
.
