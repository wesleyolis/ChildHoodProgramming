1 KF3 3 DRAW/FIL SIDE 1050 X 590
2 105000 59000 1600
3 1
4 0
5 1
6 0
%
@P1=DX-P3
@P3=(DX-200)/3+100
@P4=DX-100
@P5=DY-50
@P6=DX-78
@P7=DX-32
@P9=DY-12
@P8=DY-70
@P11=DX-120
@P10=DX-106
@P12=DY/2-10
@P13=250
@P14=DX-P3
G00 X100 Y=P9 Z12 F2 T1
G00 X=P3 Y=P9 Z12 F2 T1
G00 X=P14 Y=P9 Z12 F2 T1
G00 X=P4 Y=P9 Z12 F2 T1
G00 X=P6 Y=P12 Z12 F2 T1
G00 X=P6 Y=P8 Z12 F2 T1
G00 X=P6 Y50 Z12 F2 T1
G00 X=P7 Y30 T6
G00 X8 Y=P13 Z13 F2 T1
G00 X8 Y50 Z13 F2 T1
G00 X330 Y=P13 Z13 F2 T1
G00 X330 Y50 Z13 F2 T1
G00 X653 Y=P13 Z13 F2 T1
G00 X653 Y50 Z13 F2 T1
G00 X286 Y15 Z12 F2 T7
G00 X300 Y391 T8
G00 Y263 T8
G00 Y39 T8
G00 X611 Y12 Z12 F2 T7
G00 X625 Y35 T9
G00 Y259 T9
G00 Y387 T9
G00 X=P11 Y14 Z12 F2 T7
G00 X=P10 Y35 T7
G00 Y259 T7
G00 Y387 T7
.
