1 SYSTEM CAB DOOR NO HANDLE 1510
2 151000 45700 1600
3 1
4 0
5 1
6 0
%
(THROUGH BORING
@P1=DX-100                      NB! THIS DOOR MUST BE EDGED
@P2=DX/2              THIS IS RHS DOOR
G00 X100 Y21.5 Z12 F0.9 T5
G00 X=P1 T5
@P3=P1-24
@P4=P1+24
@P5=P2-24
@P6=P2+24
G00 X=P4 Y30.5 Z12 F1 T6
G00 X=P3 T6
G00 X124 T6
G00 X76 T6
.
