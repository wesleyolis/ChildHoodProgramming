1 WORKSTATION VAR 600MM R GROOVE
2 140000 140000 3200
3 1
4 0
5 1
6 0
%
@P1=598      (A LENGTH ON X-AXIS START POINT)
@P2=DX
@P3=DY-598          (B LENGTH)
@P4=P3-40
G72 X=P1 Y-30 Z15.8 S18 E1 T28
G41 X=P1 Y0 Q16 F2
G01 Y=P3 Q600 F6
G01 X=P2
G40 Y=P4
.
