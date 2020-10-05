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


x=[]
y=[]

x, y = toarray("outputB6.txt")

plt.plot(x, y, color='red')

fig = plt.gcf()
axes = fig.gca()
axes.set_ylim([-1, 1])

plt.xlabel('Tiempo (s)', fontsize=18)
plt.ylabel('Poscion de pendulo (m)', fontsize=18)
plt.show()
