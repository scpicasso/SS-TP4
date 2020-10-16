#!/usr/bin/env python3

import matplotlib.pyplot as plt

import numpy as np


def toarray(file):
  f = open(file, "r")
  lines = f.readlines()
  a = []
  b = []
  for l in lines:
    array = l.split()
    a.append(10**float(array[0]))
    b.append(float(array[1]))
  return a, b


x=[]
y=[]


x, y = toarray("errorDt2.txt")


plt.scatter(x, y, color='blue')

fig = plt.gcf()
axes = fig.gca()

plt.xlabel('Diferencial de tiempo (s)', fontsize=20)
plt.ylabel('Cambio relativo en la energia total al cabo de un mes', fontsize=20)
axes.tick_params(axis='x', labelsize=16)
axes.tick_params(axis='y', labelsize=16)
axes.set_xscale('log')
plt.show()
