\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Maximize
 obj: y(0)#0
Subject To
 c1: 111.75 y(0)#0 - 86.75 x(0,0)#1 - 86.75 x(1,0)#2 - 25 x(2,0)#3  = 0
 c3: x(0,0)#1 <= 1
 c5: x(1,0)#2 <= 1
 c7: x(2,0)#3 <= 1
Bounds
 0 <= y(0)#0 <= 1
 0 <= x(0,0)#1 <= 1
 0 <= x(1,0)#2 <= 1
 0 <= x(2,0)#3 <= 1
Generals
 y(0)#0  x(0,0)#1  x(1,0)#2  x(2,0)#3 
End
