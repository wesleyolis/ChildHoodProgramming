1 DOM BOOKSHELF DIV
2 61800 28900 1600
3 1
4 0
5 1
6 0
%
@P1=DX-85
@P2=DY-66
@P3=DY-12
@P4=50
@P10=110
@T81=7,8,9
@X81=9,8,7
G72 X85 Y=P4 Z13 T81
G86 X=P1 F2 T81
G72 X85 Y=P2 T81
G86 X=P1 Y=P2 T81
@P6=DX-34
@P9=DY-50
G00 X34 Y50 Z13 F2 T2
G00 Y=P9
G82 X28 Z8 F2 T19
G82 Y50
G00 X=P6 Y=P9 Z13 F2 T2
G00 Y50
G83 X28 Z8 F2 T20
G83 Y=P9
.
