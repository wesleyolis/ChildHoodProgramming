1 SYS TOP/BOT NO OVERLAP DIV KAT
2 89200 43000 3200
3 1
4 0
5 1
6 0
%
@P1=DY-50
@P2=12
@P3=DX/3+16
@P4=DX-10.5
(P5 = TO THE DISTANCE ON X FOR THE DIVSION
@P5=446
@P6=DY-70
@P7=DX-((DX/3)+16)
G00 X10.5 Y50 Z13 F2 T6
G00 Y=P1
G00 X=P3 Y=P2 Z12 F2 T6
G00 X=P7
G00 X=P4 Y=P1 Z13 F2 T6
G00 Y50
G00 X=P5 Y70
G00 Y=P6
.
