1 KB3 BOOKCASE VANITY 1734 X 887
2 173400 88700 1600
3 1
4 0
5 1
6 0
%
(TRIMMING FRONT SIDE
(NB RUN THIS PROGRAM AS NORMAL AS YOU SEE IT
(OTHER HALF SEE PROGRAM 359 WHICH YOU RUN IN MIRROR IMAGE
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
@P10=DX+30
@P11=DX
@P12=DY
@P13=DY+30
G72 X-30 Y=P13 Z18 S18 E1 T25
G42 X0 Y=P12 Z18
G01 X=P11 Y=P12 Z35
G40 X=P10 Y=P13 Z0
.
