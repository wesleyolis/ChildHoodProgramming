1 RET INC/EVENSPACE BOUNDRY
2 50000 60000 1600
3 1
4 0
5 1
6 0
%
(X-AXIS START,END,X-INCREMENT)
@P1=50
@P2=DX-100
@P5=55
(Y-AXIS START,END,X-INCREMENT)
@P3=100
@P4=DY-100
@P6=100
(
(PROGRAM)
@P9=P2-P1
@P9=(P9/INT(P9/P5))
@P10=P4-P3
@P10=(P10/INT(P10/P6))
@P7=P1
@WHILE (P2-P7)*(P4-P3)>=0
(G-CODES TO BE EXCUTED AT POINT, WITH X=P7 Y=P3)
(
G00 X=P7 Y=P3 Z12 F1 T1
(
(END OF C-CODES)
@P7=(P7+P9)
@P3=P3+(P10*INT(P7/(P2+0.0001)))
@P7=P7+((P1-P7)*INT(P7/(P2+0.0001)))
@WEND
.
