1 BOUNDARY REC ALTERNATING XY
2 50000 70000 1600
3 1
4 0
5 1
6 0
%
(X-AXIS START,END,X-INCREMENT)
@P1=100
@P2=DX-150
@P5=95
(Y-AXIS START,END,Y-INCREMENT)
@P3=25
@P4=DY-75
@P6=100
(
(PROGRAM)

@P9=INT((P2-P1)/P5)+1)
@P10=INT((P4-P3)/P6)+1)

(AXIS SPEEDS P16-X P17-Y)

@P16=35
@P17=40

(AXIS DIRECTIONS)
@P14=1
@P15=0

@P18=(((((P9-1)*P5)/P16)*P10)+(((P10-1)*P6)/P17))

@P19=((((((P10-1)*P6)/P17)+(P5/P16))*P9)-(P5/P16))

@IF P19<P18
@P7=P10
@P10=P9
@P9=P7
@P14=0
@P15=1
@ENDIF

@P8=1
@P11=1
@P13=0

@WHILE P13<(P9*P10)
(G-CODES TO BE EXCUTED AT POINT, WITH X=P1 Y=P3)
(
G00 X=P1 Y=P3 Z12 F1 T7
(
(END OF C-CODES)
@P7=P8

@P3=P3+(P6*(INT(P11/P9))*P14)
@P1=P1+(P8*(P5*((INT(P11/P9))))*P14)

@P1=P1+(P5*(INT(P11/P9))*P15)
@P3=P3+(P8*(P6*((INT(P11/P9))))*P15)

@P8=P8-(2*((INT(P11/P9))*((P7+1)/2)))
@P8=P8+(2*((INT(P11/P9))*((P7-1)/(-2))))

@P1=P1+(P5*P8*P14)
@P3=P3+(P6*P8*P15)

@P11=P11-((P11)*INT(P11/P9))
@P11=P11+1
@P13=P13+1
@WEND
.
