1 SYSTEM CAB DOOR 5 HOLE DRILL
2 204900 45700 1600
3 1
4 0
5 1
6 0
%
(THROUGH BORING
@P1=DX-100                      NB! THIS DOOR MUST BE EDGED
@P2=DX/2              THIS IS RHS DOOR
@P3=DX/4+50
@P4=DX-(DX/4+50)
G00 X100 Y21.5 Z12 F0.9 T5
G00 X=P3 Y21.5 Z12 F0.9 T5
G00 X=P2 T5
G00 X=P4 T5
G00 X=P1 T5
.
