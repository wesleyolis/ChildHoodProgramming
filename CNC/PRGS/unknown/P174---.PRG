1 FRIDGE UNIT/SIDE/SHELF 968X590
2 96800 58000 1600
3 1
4 0
5 1
6 0
%
@P1=DX-216
@P2=DY-70
@T81=7,8,9
@X81=9,8,7
G72 X200 Y70 Z13 T81
G86 X=P1 F2 T81
G72 X200 Y=P2 T81
G86 X=P1 Y=P2 T81
@P3=DX/2
@P4=DX-100
@P5=DY-50
@P6=DX-58
@P7=DX-32
@P9=DY-8
@P8=DY-50
@P10=DY-30
@P11=DY-25
@P12=DY-50
G00 X100 Y=P9 Z13 F2 T1
G00 X=P3 F2 T1
G00 X=P4 F2 T1
G00 X=P6 Y=P8 F2 T1
G00 X=P6 Y50 F2 T1
G00 X=P7 Y30 Z12 T6
G00 X=P7 Y=P10 T6
G00 X8 Y25 Z12 F2 T6
G00 X8 Y50 F2 T1
G00 X8 Y=P11 Z12 F2 T6
G00 X8 Y=P12 F2 T1
.
