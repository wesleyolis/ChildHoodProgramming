1 TOP ROLLER CREDENZA OOP 4TH
2 120000 65000 3200
3 1
4 0
5 1
6 0
%
(IT IS IMPORTANT TO REMEMBER IF THE TOP IS ENLARGED ALL THE Y
(PARAMETERS MUST BE ENLARGE BY THE DIFFENCE BETWEEN THE NORM
(OF 600 AND THE NEW SIZE IE 700-600=100 THEREFOR Y PARAMETER
(INCREASE BY 100
(ACTUAL DY PRAMETERS P3=DY-121 P4=DY-71 P7=DY-122 P8=DY-72
(P9=DY-100 P11=DY-115 P13=DY-135 P14=DY-79 P16=DY-57
@P1=DX-76
@P2=DX-26
@P3=DY-171
@P4=DY-121
@P5=DX-44
@P6=DX-77
@P7=DY-177
@P8=DY-139
@P9=DY-150
@P10=DX-8
@P11=DY-165
@P12=DX-107
@P13=DY-185
@P14=DY-129
@P15=DX-266
@P16=DY-107
G72 X30 Y23 Z0 S18 E1 T27
G42 X13 Y15 Z11.5
G01 X=P1 Z11.5
G05 X=P2 Y65
G01 Y=P3
G05 X=P1 Y=P4
G01 X13
G40 X40 Y=P14
G72 X30 Y23 Z0 S18 E1 T27
G41 X13 Y33 Z11.5 F3
G01 X=P6
G05 X=P5 Y69
G01 Y=P7
G05 X=P6 Y=P8
G01 X13
G40 X40 Y=P14
G72 X10 Y=P9 Z12 E1 T23
G80 X=P6 F3 T23
G00 X8 Y75 Z13 F2 T6
G00 Y=P11
G00 X266 Y=P16 Z12 F2 T6
G00 X=P15
G00 X=P10 Y=P11 Z13 F2 T6
G00 Y75
G00 X=P12
G00 Y=P13
.
