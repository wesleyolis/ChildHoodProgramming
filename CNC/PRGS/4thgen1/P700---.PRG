1 S TOP END
2 60000 80000 3200
3 1
4 0
5 1
6 0
%
@P1=-30
@P2=75
@P3=150
@P4=DX/2
@P5=DX/4
@P6=DX/4*3
@P7=-30
@P8=DX
@P9=DX+30
@P10=P9+30
(IF OFFSET CHANGES CHANGE DISTANCE OF OFFSET TO BE = TO P11
(AND P12 TO 1/2 OF OFFSET IE 600 TO 800 P11=200 AND P12 =100
@P11=150
@P12=75
G72 X=P1 Y=P11 Z35 S18 E1 T26
G41 X0 Y=P11 Z35
G03 X=P4 Y=P12 R800
G02 X=P8 Y0 R800 Q16
G02 X=P9 Y0 R800
G40 X=P10 Y=P1
.
