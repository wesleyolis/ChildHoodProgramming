1 SHELVES SETTINGS
2 40000 40000 1600
3 1
4 0
5 1
6 0
%
(DISTANCE FROM BOTTOM)
@P61=50
(DISTANCE FROM TOP)
@P62=50
(DISTANCE FROM FRONT)
@P63=50
(DISTANCE FROM BACK)
@P64=50
(32MM CENTER DISTANCE TOTAL)
@P85=96
(THREASHOLD VALUE)
@P66=250

(DEFAULT VALUES)
@P81=P61
@P82=DX-P62
@P83=P63
@P84=DY-P64

(PASSING PARAMTERS)
@IF P51>0
@P81=P51
@ENDIF

@IF P52>0
@P82=P52
@ENDIF

@IF P53>0
@P83=P53
@ENDIF

@IF P54>0
@P84=P54
@ENDIF

(THREASHOLD CALULATIONS DIS Y)
@P86=(P84-P83)/(INT((P84-P83)/P66)+1)
M99
.
