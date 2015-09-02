# CS 101 Repository

## Chapters
* 01: [ADT and Algorithms](../../01-adt-and-algorithms/README.md)
* 02: [Sequential Lists: Lists, Stacks and Queues backed by Arrays](../../02-sequential-lists/README.md)
* 03: [Linked Lists](../../03-linked-lists/README.md)
* 04: [Recursion](../../04-recursion/README.md)

### Projects
* [001-recursion](README.md): **TODO**

#### Programs

**Keywords:**
+ Recursion
+ Factorial, Fibonacci


##### Factorial
Recursive implementation of factorial function.

##### Fibonacci
Recursive implementation of Fibonacci sequence.

##### Ackermann
Recursive implementation of Ackermann function.

##### RecursiveList
Implementation of RecursionList:
List = {}, if list is empty
List = { head: element, rest: List }

##### LabyrinthSolver
Program that solves a 2D labyrinth with a recursive algorithm that leverages backtracking.

##### RecursionPuzzle
Starting from the number 1, and repeatedly either adding 5 or multiplying by 3, write a program that given a number finds the sequence of such additions and multiplications that produce that number.

For example:
```
    13 -> 1 * 3 + 5 + 5
    15 -> cannot be reached
```

##### EightQueensPuzzleRunner
Finds a solution to place 8 queens in a chess board using a recursive algorithm.

##### TowerOfHanoi
Finds a solution for the Tower Of Hanoi Puzzle where three rods and a number of discs of different sizes which can be slide onto any rod.
The puzzle starts with the discs in a net stack in ascending order of size on one rod, the smallest at the top making a conical shape.

The objective is to move the entire stack to another rod following these rules:
1. Only one disc can be moved at a time
2. Each move consists of taking the upper disc from one of the stacks and placing it on top of other stack (i.e. a disc can only be moved if it's the uppermost disc on a stack).
3. No disc can be placed on tip of a smaller disc. 

