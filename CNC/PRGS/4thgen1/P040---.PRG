1 ROL DOOR PEDENZA BOT OOP INSI
2 120000 55000 3200
3 1
4 0
5 1
6 0
%
(FOR PLASTIC DRAWERS LINE 43 X= 388
(FOR WOODEN DRAWERS LINE 43 X=418
@P1=DX-76
@P2=DX-26
@P3=DY-76
@P4=DY-26
@P5=DX-27
@P6=DX-77
@P7=DY-77
@P8=DY-27
@P9=DY-55
@P10=DX-8
@P11=DY-70
@P12=DX-107
@P13=DY-90
@P14=DY-34
@P15=DX-266
@P16=DY-12
G72 X30 Y23 Z0 S18 E1 T25
G42 X13 Y15 Z11.5
G01 X=P1 Z11.5
G05 X=P2 Y65
G01 Y=P3
G05 X=P1 Y=P4
G01 X13
G40 X40 Y=P14
G72 X30 Y23 Z0 S18 E1 T25
G42 X13 Y17 Z11.5
G01 X=P61 2DR 1F OR 4DR SIDE 32 KICK
2 70000 52000 1600
3 1
4 0
5 1
6 0
%
@P1=DX-74
@P2=DX-104
@P3=DY-12
@P4=DX-100
@P5=DY-80
@P6=DX-360
@P7=DY-110
@P9=DX/2
G00 X=P6 Y=P5 Z12 F2 T1
G00 X=P6 Y=P7 Z8 F2 T6
G00 X=P6 Y90 T6
G00 X=P6 Y60 Z12 F2 T1
.
      