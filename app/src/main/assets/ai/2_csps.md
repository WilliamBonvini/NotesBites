# Constraint Satisfaction Problems

### Definition

A (discrete) CSP is defined by:

- A finite set ${}X={x_1,...,x_n}$ of *variables*
- For every variable ${x_i}$, a set ${D_i}$ of possible values, called the *domain* of ${x_i}$ (such domain can be a tuple of domains if required)
- A finite set ${C=\{c_1,...,c_m\}}$ of *constraints* on possible assignments of values to variables.

CSP constraints are represented by logical expressions involving the problem's variables. Such expressions may take a definite truth value when they are applied to an assignment.

### Methods

<u>*Basic Solving Tecnhiques*</u>

- **Backtracking**  
  Choose variables with the specified method (in lexical graphical order if not specified), don't update domains
- **Backtracking with forward checking**  
  Choose variables with the specified method (in lexical graphical order if not specified), update domains

<u>*Variable Selection Heuristics*</u>

- **Degree Heuristic**  
  Choose the variable that is involved in the largest number of constraints on other unassigned variables
- **Minimum Remaining Values (MRV) **  
  Choose the variable with the fewest possible values in the domain

<u>*Value Selection Heuristics*</u>

- **Least-constraining value heuristic**  
  Choose the value of the selected variable that rules out the smallest number of values in variables connected to the current variable by constraints

### Arc Consistency

1. build the constraint directed graph
2. Take a walk in the queue knowing that the arc "X -> Y"  is consistent if for every value of X there is some possible legal value of Y
3. 1. if it is not consistent 
      1. I will change the domain of the left member node (X in the example) in order to make it consistent
      2. 1. if after removing the element of the domain that makes the arc consistent I obtain an empty domain there is no solution and I stop
         2. otherwise I'll push in the queue some other arcs: 
            since I modified the domain of X, I will have to push in the queue all the arcs in the form of Z -> X, where Z is any node of the graph, excluding Y and excluding the arcs I already evaluated consistent.
         3. if the queue is empty I stop
   2. if it is consistent 
      1. I throw it away and I forget it forever, it will never be back in the queue
      2. if the queue is empty I stop
4. go back to step 2.