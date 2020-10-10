#!/usr/bin/env python3

import matplotlib.pyplot as plt
import math

import numpy as np


d_earth = math.pow(1.493890685719635E+08, 2) + math.pow(1.252824102724684E+07, 2)  
d_earth = math.sqrt(d_earth) - 6371 - 696340

def error(earth):
	error = (d_earth - earth)/d_earth
	return abs(error)


Y = []
Y.append(error(1.491864910938446E+08))
Y.append(error(1.4918877331104904E+08))
Y.append(error(1.4923159468036297E+08))
Y.append(error(1.4943973264580238E+08))
Y.append(error(1.517174528925142E+08))




X = [1e+0, 1e+1, 1e+2, 1e+3, 1e+4]

plt.plot(X, Y, marker = "o", color='red')

fig = plt.gcf()
axes = fig.gca()

plt.xlabel(r'$\Delta t (s)$', fontsize=20)
plt.ylabel(r'$ E(\Delta t)$', fontsize=20)
axes.set_yscale('log')
axes.set_xscale('log')


axes.tick_params(axis='x', labelsize=16)
axes.tick_params(axis='y', labelsize=16)

plt.show()
