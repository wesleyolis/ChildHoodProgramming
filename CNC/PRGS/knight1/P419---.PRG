1 KC2 PEDENZA DOOR
2 62200 52000 1600
3 1
4 0
5 1
6 0
%
(THROUGH BORING
@P1=DX-100                      NB! THIS DOOR MUST BE EDGED
G00 X100 Y21.5 Z12 F.5 T5
G00 X=P1 T5
@P3=P1-24
@P4=P1+24
G00 X=P4 Y30.5 Z12 F2 T6
G00 X=P3 T6
G00 X124 T6
G00 X76 T6
@P7=DY-32
G00 X50 Y=P7 Z18.5 F.5 T9
G00 X146 T9
.
