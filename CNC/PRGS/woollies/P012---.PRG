1 TOP CONCAVE WAVE ATHLONE COLLG
2 135000 80000 3200
3 1
4 0
5 1
6 0
%
@P1=DX/2
@P2=P1-150
@P3=P1+150
@P4=DX-300
@P5=DX
@P6=DX+30
G72 X-30 Y0 Z35 S18 E1 T26
G41 X0 Y0 Z36 F4
G02 X300 Y50 R1050 Q99
G03 X=P4 Y50 R1350 Q99
G02 X=P5 Y0 R1050
G40 X=P6 Y0
@P1=DX-45
@P2=DX-141
@P3=DY-141
@P4=DY-45
@P5=DX/2-200
@P6=DX/2+200
@P7=DY-307
@P8=DY-403
G00 X45 Y45 Z24 F2 T1
G00 Y141
G00 Y=P3
G00 Y=P4
G00 X141 Y=P4
G00 Y=P3
G00 Y141
G00 Y45
G00 X=P5 Y=P4
G00 X=P6
G00 X=P2 Y45
G00 X=P1
G00 Y141
G00 Y=P3
G00 Y=P4
G00 X=P2 Y=P4
G00 Y=P3
G00 Y141
.
