1 DOUBLE SIDED VAN BOOKCASE
2 230000 86800 1600
3 1
4 0
5 1
6 0
%
(NB THIS PROGRAM MUST RUN IN MIRROR IMAGE AND BOARD BE TURNED
(OTHER HALF SEE PROGRAM 366 WHICH YOU RUN IN NORMAL IMAGE
@P1=DX-100
@P2=DY-34
G00 X100 Y34 Z13 F1 T2
G84 X100 Y28 Z8 F2 T22
@P4=DY/2
@P3=DX/2
G84 X=P3 Y28 Z8 F2 T22
G00 X=P3 Y34 Z13 F1 T2
G00 X=P1 Y34 Z13 F1 T2
G84 X=P1 Y28 Z8 F2 T22
@P5=DX/4+50
G84 X=P5 Y28 Z8 F2 T22
G00 X=P5 Y34 Z13 F1 T2
@P6=DX-567
G84 X=P6 Y28 Z8 F2 T22
G00 X=P6 Y34 Z13 F2 T2
.
