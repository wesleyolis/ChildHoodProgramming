1 SYSTEM CAB DOOR 5HOLES
2 252500 50600 1600
3 1
4 0
5 1
6 0
%
(THROUGH BORING
@P1=DX-600                      NB! THIS DOOR MUST BE EDGED
@P2=DX-100
@P7=DX/2
@P8=DX/2+24
@P9=DX/2-24
G00 X100 Y21.5 Z13 F0.7 T5
G00 X550 T5
G00 X=P7 T5
G00 X=P1 T5
G00 X=P2 T5
@P3=P1-24
@P4=P1+24
@P5=P2-24
@P6=P2+24
G00 X=P6 Y30.5 Z13 F6 T6
G00 X=P5 T6
G00 X=P4 Y30.5 Z13 F6 T6
G00 X=P3 T6
G00 X=P8 T6
G00 X=P9 T6
G00 X575 T6
G00 X525 T6
G00 X124 T6
G00 X76 T6
.
