1 TUDOR AND SIDE TRIMMING
2 60000 45000 1600
3 1
4 0
5 1
6 0
%
@P2=-32
@P3=DX+32
@P7=DX
@P8=DY
G72 X-32 Y-32 Z18 S18 E1 T25
G41 X0 Y0 Z18 F5
G01 X=P7
G01 Y=P8
G01 X0
G01 X0 Y0
G40 X=P2 Y=P2
(PI=TOP OF ARCH
@P1=50
(P2=DIST FROM EDGE
@P2=70
(P3=START OF STRAIGHT
@P3=70
@P4=P2*2
@P5=(DY-P4)/2
@P6=P3/2
@P7=P5/2
@P8=P7*P7
@P19=P6*P6
@P21=P8+P19
@P22=P6*2
@P9=P21/P22
@P10=P2
@P11=P1+P3
@P12=P6+P1
@P13=P7+P2
@P14=DY-P13
@P15=DY-P2
@P16=DX-P12
@P17=DX-P11
@P18=DX-P1
(P20=DEPTH
@P20=3
@P25=P20-P20
@P23=DY/2+30
@P24=DY/2-30
G72 X=P10 Y=P23 Z=P25 E1 T25
G01 Y=P24 Z=P20 F2
G01 Y=P2 F8
G01 X=P17
G03 X=P16 Y=P13 R=P9
G02 X=P16 Y=P14 R=P9
G03 X=P17 Y=P15 R=P9
G01 X=P10
G01 Y=P24
.
