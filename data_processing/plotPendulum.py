#!/usr/bin/env python3

import matplotlib.pyplot as plt

import numpy as np


def toarray(file):
  f = open(file, "r")
  lines = f.readlines()
  pos = []
  times = []
  for l in lines:
    array = l.split()
    pos.append(float(array[1]))
    times.append(float(array[0]))
  return times, pos


xV=[]
yV=[]
xO=[]
yO=[]
xB=[]
yB=[]
xG=[]
yG=[]

xV, yV = toarray("outputV6.txt")
xO, yO = toarray("outputO6.txt")
xB, yB = toarray("outputB6.txt")
xG, yG = toarray("outputG6.txt")

plt.plot(xV, yV, color='red', label="Velocity Verlet")
plt.plot(xB, yB, color='orange', label="Beeman", linestyle = "dashdot")
plt.plot(xO, yO, color='blue', label="Solucion Analitica", linestyle = "dotted")
plt.plot(xG, yG, color='green', label="Gear Corrector-Predictor", linestyle = "dashed")

fig = plt.gcf()
axes = fig.gca()
axes.set_ylim([-1, 1])

plt.xlabel('Tiempo (s)', fontsize=20)
plt.ylabel('Poscion de pendulo (m)', fontsize=20)
plt.rc('legend', fontsize=16)  
axes.tick_params(axis='x', labelsize=16)
axes.tick_params(axis='y', labelsize=16)
plt.legend()
plt.show()
