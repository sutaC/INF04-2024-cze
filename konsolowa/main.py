#!/bin/python
from random import random

# ************************************************
#  nazwa: getDiceRoll
#  opis: Zwraca losową wartość rzutu kością
#  parametry: brak
#  zwracany typ i opis: (int) - całkowita losowa wartość rzutu kością z przedziału <1,6>
#  autor: XYZ
# ************************************************
def getDiceRoll() -> int:
    return int(1 + random() * 5)

def sumUpPoints(points: list[int]) -> int:
    result = 0
    for i in range(6):
        if points[i] > 1:
            result += (i + 1) * points[i]
    return result

# Start
amount = None
while True:
    amount = input("Ile kostek chcesz rzucić?(3 - 10)\n")
    try:
        amount = int(amount)
        if amount < 3 or amount > 10:
            raise Exception()
    except:
        continue
    break
# Game loop
while True:
    points = [0, 0, 0, 0, 0, 0]
    for i in range(amount):
        dice = getDiceRoll()
        points[dice-1] += 1
        print(f"Kostka {i+1}: {dice}")
    print(f"Liczba uzyskanych punktów: {sumUpPoints(points)}")
    # Resume?
    resume = None
    while True:
        resume = input("Jeszcze raz? (t/n)\n")
        if resume == "t" or "n":
            break
    if resume == "n":
        break


