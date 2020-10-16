#!/usr/bin/env python3

import matplotlib.pyplot as plt

import statistics as sts

import numpy as np


def plotDistances(filename):

  aux = []
  f = open(filename, "r")
  lines = f.readlines()
  x = -1
  for l in lines:
    array = l.split()
    x = float(array[0])
    y = float(array[1])
    x = x/(60*60)
    # x = x + 1
    # y = float(array[0])
    # y = y/(60*60*24)
    # x = x/(60*60*24)

    plt.errorbar(x, y,
       yerr=0,
       # marker='o',
       color="blue",
       ecolor='blue',
       # markerfacecolor='g',
       capsize=1,
       linestyle='None',
       fmt=' ')


    # plt.plot(x, y)


  fig = plt.gcf()
  axes = fig.gca()
  # axes.set_ylim([0, 0.16])
  # axes.set_xlim([0, 0.035])

  plt.xlabel('Minuto de despegue', fontsize=15)
  plt.ylabel('Distancia minima de viaje hasta llegar a marte (km)', fontsize=15)
  axes.tick_params(axis='x', labelsize=13)
  axes.tick_params(axis='y', labelsize=13)
  plt.show()
  return


plotDistances("excactDistance2.txt")