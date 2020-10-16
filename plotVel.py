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
    a.append(float(array[0]))
    b.append(float(array[1]))
  return a, b


x=[]
y=[]


x, y = toarray("velTime.txt")


plt.scatter(x, y, color='blue')

fig = plt.gcf()
axes = fig.gca()

plt.xlabel('Tiempo de viaje (s)', fontsize=20)
plt.ylabel('Modulo de velocidad de la nave (km/s)', fontsize=20)
axes.tick_params(axis='x', labelsize=16)
axes.tick_params(axis='y', labelsize=16)
# axes.set_yscale('log')
plt.show()
