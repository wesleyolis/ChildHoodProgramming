1 FLYCUT IN X TO X ON BACK
2 60000 60000 3200
3 1
4 0
5 1
6 0
%
@P10=DX+30
@P11=DX
@P12=DY
@P13=-30
G72 X-30 Y=P13 Z20.5 S18 E1 T28
G41 X0 Y0 Q14
G01 X=P11 Y0
G40 X=P10 Y=P13
.
