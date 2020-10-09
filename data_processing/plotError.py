#!/usr/bin/env python3

import matplotlib.pyplot as plt
import math

import numpy as np

def toarray(file):
  f = open(file, "r")
  lines = f.readlines()
  pos = []
  times = []
  for l in lines:
    array = l.split()
    pos.append(float(array[1]))
  return pos

def geterror(arr, ans):
	n = len(arr)
	total = 0
	for i in range(len(arr)-1):
		total += math.pow((arr[i] - ans[i+1]),2)
	return total/n

def geterrorG(arr, ans):
	n = len(ans)
	total = 0
	for i in range(len(ans)-1):
		total += math.pow((arr[i] - ans[i+1]),2)

	return total/n


xV1=toarray("outputV1.txt")
xV2=toarray("outputV2.txt")
xV3=toarray("outputV3.txt")
xV4=toarray("outputV4.txt")
xV5=toarray("outputV5.txt")
xV6=toarray("outputV6.txt")
xB1=toarray("outputB1.txt")
xB2=toarray("outputB2.txt")
xB3=toarray("outputB3.txt")
xB4=toarray("outputB4.txt")
xB5=toarray("outputB5.txt")
xB6=toarray("outputB6.txt")
xO1=toarray("outputO1.txt")
xO2=toarray("outputO2.txt")
xO3=toarray("outputO3.txt")
xO4=toarray("outputO4.txt")
xO5=toarray("outputO5.txt")
xO6=toarray("outputO6.txt")
xG1=toarray("outputG1.txt")
xG2=toarray("outputG2.txt")
xG3=toarray("outputG3.txt")
xG4=toarray("outputG4.txt")
xG5=toarray("outputG5.txt")
xG6=toarray("outputG6.txt")

Blist = []
Glist = []
Vlist = []


Glist.append(geterror(xG1, xO1))
Glist.append(geterror(xG2, xO2))
Glist.append(geterror(xG3, xO3))
Glist.append(geterror(xG4, xO4))
Glist.append(geterror(xG5, xO5))
Glist.append(geterrorG(xG6, xO6))
Glist = Glist[::-1]

Blist.append(geterror(xB1, xO1))
Blist.append(geterror(xB2, xO2))
Blist.append(geterror(xB3, xO3))
Blist.append(geterror(xB4, xO4))
Blist.append(geterror(xB5, xO5))
Blist.append(geterror(xB6, xO6))
Blist = Blist[::-1]
Vlist.append(geterror(xV1, xO1))
Vlist.append(geterror(xV2, xO2))
Vlist.append(geterror(xV3, xO3))
Vlist.append(geterror(xV4, xO4))
Vlist.append(geterror(xV5, xO5))
Vlist.append(geterror(xV6, xO6))
Vlist = Vlist[::-1]



print(Glist)

deltas = [1e-6, 1e-5, 1e-4, 1e-3, 1e-2, 1e-1]

plt.plot(deltas, Glist, marker = "o", color='green', label="Gear Corrector-Predictor")
plt.plot(deltas, Blist, marker = "o", color='blue', label="Beeman")
plt.plot(deltas, Vlist, marker = "o", color='red', label="Velocity Verlet")

fig = plt.gcf()
axes = fig.gca()

plt.xlabel(r'$\Delta t (s)$', fontsize=20)
plt.ylabel(r'$ E(\Delta t) (m^2)$', fontsize=20)
axes.set_yscale('log')
axes.set_xscale('log')


plt.rc('legend', fontsize=16)
axes.tick_params(axis='x', labelsize=16)
axes.tick_params(axis='y', labelsize=16)

plt.legend()
plt.show()
