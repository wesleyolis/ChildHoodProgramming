1 4TH GEN CORE 45 DEG ROUTER/FLY
2 100000 100000 3200
3 1
4 0
5 1
6 0
%
@P1=DY-640
@P2=640
@P3=P2+42.5
@P4=90
@P6=P1-42.5
@P7=DX-90
@P14=DX
@P15=DX+30
G72 X=P2 Y-30 Z39 S18 E1 T25
G41 X=P2 Y0
G03 X=P3 Y=P4 R118 Q16
G01 X=P7 Y=P6 Q16
G03 X=P14 Y=P1 R118
G40 X=P15 Y=P1
@P11=DY/2
@P12=P11+200
@P13=P11-200
@P8=P2
@P9=P8-115
@P10=P8-211
G00 X115 Y45 Z23 F2 T1
G00 X211
G00 X307
G00 X403
G00 X=P10
G00 X=P9
G00 X=P9 Y141
G00 X=P10
G00 X403
G00 X307
G00 X211
G00 X115
G00 X115 Y=P13
G00 Y=P12
G72 X=P2 Y-30 Z20.5 S18 E1 T28
G41 X=P2 Y0 Q14
G03 X=P3 Y=P4 R118 Q14
G01 X=P7 Y=P6 Q14
G03 X=P14 Y=P1 R118
G40 X=P15 Y=P1
.
