1 S TOP END 1200 X 800 X600
2 130000 45000 3200
3 1
4 0
5 1
6 0
%
@P1=-30
@P2=75
@P3=200
@P4=DX/2
@P5=DX/4
@P6=DX/4*3
@P7=-30
@P8=DX
@P9=DX+30
@P10=P9+30
@P11=200
@P12=125
G72 X=P1 Y200 Z35 S18 E1 T26
G41 X0 Y=P11 Z36
G03 X=P4 Y=P12 R2500
G02 X=P8 Y0 R2500 Q16
G02 X=P9 Y0 R2500
G40 X=P10 Y=P1
.
