1 PART #3 SIMPILFIED IN PARAMETE
2 30000 20000 1905
3 1
4 0
5 1
6 0
%
@P1=-32
@P2=50
@P3=0
@P5=22
G72 X=P1 Y=P2 Z=P3 S18 E1 T25
G41 X=P3 Z=P5 F4
G03 X=P2 Y=P3 R=P2
G40 Y=P1
@P6=DX-25
@P7=-32
@P8=DX
@P9=DX+32
@P10=25
G72 X=P6 Y=P7 Z=P5 S18 E1 T25
G41 Y=P3 F4
G02 X=P8 Y=P10 R=P10
G40 X=P9
@P11=150
@P12=100
@P13=DY
@P14=DY+32
G72 X=P7 Y=P11 Z=P5 S18 E1 T25
G42 X=P3 F4
G01 X=P12
G01 Y=P13
G40 Y=P14
@P15=DX/2-40
@P16=DX/2-50
@P17=DY/2
@P18=DY/2-10
@P19=DY/2+10
@P20=DX/2+50
@P21=0
@P22=12
@P23=DX/2-20
G72 X=P15 Y=P17 Z=P21 S18 E1 T27
G42 X=P16 Y=P18 Z=P22 F4
G01 X=P20
G01 Y=P19
G01 X=P16
G40 X=P23 Y17 Z=P21
.
