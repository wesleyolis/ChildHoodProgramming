1 DRILL CORE 45MM OFF 4HOLE
2 160000 160000 3200
3 1
4 0
5 1
6 0
%
@P2=DX-250
@P3=250
@P4=DX-45
@P5=DX-141
(CHANGE P6 FOR DEPTH OF CORE)
@P6=650
@P7=P6-45
@P8=P6-141
G00 X45 Y112.88 Z23 F2 T1
G00 X112.88 Y45 Z23 F2 T1
G00 X180.76 Y112.88
G00 X112.88 Y180.76
G00 X=P3 Y45
G00 X=P2
G00 X=P4
G00 Y141
G00 Y=P8
G00 Y=P7
G00 X=P5 Y=P7
G00 Y=P8
G00 Y141
G00 Y45
(RUN AT 20% OF AXIS SPEED FOR ROUTERING
G72 X184 Y184 Z35 S18 E1 T26
G42 X144 Y184
G02 I184 J184
G40 X163 Y184
.
