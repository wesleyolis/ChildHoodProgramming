1 OOP NUCLEA VISON END S
2 70000 60000 3200
3 1
4 0
5 1
6 0
%
@P1=DX
@P2=DY
@P3=DX-10
@P4=DX-40
@P5=DX-50
@P6=DY+30
G72 X-30 Y-30 Z35 S18 E1 T25
G41 X0 Y0 Z36
G02 X=P4 Y250 Z36 R1030 Q79 F4
G02 X=P3 Y350 Z36 R100 Q79
G03 X=P5 Y=P2 R691
G40 X=P1 Y=P6
.
