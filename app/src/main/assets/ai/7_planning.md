# Planning

### Introduction

**Closed World Assumption**

The Closed World Assumption amounts to consider as false all the sentences that are not known to be true. 
In STRIPS, this means that all the predicates not listed in the representation of a state are considered false. 

**Terminology**

- *<u>Predicate</u>*

  - something that can be true or false. stuff with parameters or without.   
    As parameters, predicates take constants.
    A predicate with more than one parameter is called *relation*.
  - Predicates can be divided in two classes:
    - *Fluent*
      Predicates that can change with time (true in some instances of time, false in others)
      (e.g. onTable(A))
      They are the only thing that change!
    - *Atemporal predicate*
      (e.g. Ball(A))

- *<u>Constants</u>*
  A, B : denotes object

- *<u>Primitives & Derivables</u>*

  Clear(A) is derivable because a block is clear if there is not a block upon it.



**State**

A state is represented by a set of literals that are:

- *positive*  
- *grounded*  
  they don't have any variable
- *function free*  
  there are no functions





**Goal**

- Goals are a set of states   
  [ C over A over B ]  or [ (A over B) and  C ]     --> both satisfy on(A,B)
- A state S satisfies a goal G when the state S contains all the positive literals of G and does not contain any of the negative literals of G
- ***PDLL***
  - *Goals are represented by a set of literals that are function-free ${\to}$   
    variables and negative predicates are allowed!*
    e.g. 
    not On(A,B) 
    On(x,A)
  - PDLL extends STRIPS
  - if you have a variable in a goal then this variable has an existence quantifier
    On(x,A) means Exists x |on(x,A) is true?
- ***STRIPS*** 
  - *Goals are represented by a set of positive literals*
  - STRIPS doesn't allow negative predicates and variables in goals



**Action Schemas & Actions**

Valid for both STRIPS and PDLL:

- Action schema:
  a set of possible actions, an action is derived by an action schema according to how I instantiate the variables.
  divided in

  - *<u>Name</u>*

  - *<u>Preconditions</u>*
    list of literals that are function free that state what should be true in the current state in order for the action to be applicable.
    A precondition allows to decide if an action is applicable in a state.
    An action is *applicable* in a state when 

    - All the positive preconditions of the action are present in the description of the state
    - None of the negative preconditions of the action is present in the description of the state.
      I*n fact the precondition can also contain some negative literals* (something that Amigoni doesn't like). 

  - *<u>Effects</u>*

    1. copy the representation of the previous state
    2. delete the negative effects.
    3. add the positive effects.

    Do not mention atemporal predicates in the actions please.

- ***frame problem***
  transitioning from a state to the other most of the things don't change. (I wrote it just because he could ask it at the exam).
  Example? Disney Cartoons LOL. fixed background, mickey mouse just moves his legs and arms e.e

- Action types (concept needed for backward planning)

  - *relevant actions:*  
    An action is relevant to a goal if it achieves at least one of the conjuncts of the goal.
  - *consistent actions:* 
    An action that does not undo any conjunct of the goal.



### Forward Planning / Progressive Planning

*definition:* Forward planning formulates a search problem that starts from the initial state of the planning problem and applies all the applicable actions, in order to reach a state that satisfies the goal of the planning problem.

- Forward Planning searches in the space of states because the states of the search problem formulated by forward planning are states of the planning problem



### Backward Planning / Regression Planning

*definition*: Backward planning, instead, formulates a search problem that starts from the goal of the planning problem and applies all the regressions of the goal through relevant and consistent actions, in order to reach a goal state that is satisfied by the initial state of the planning problem. 

- given an action A and a goal G, such that A is relevant and consistent for the goal G, the regression of the goal G through the action A is the goal G'
  R[G,A] = G'

- Backward Planning searches in the space of goals because the states of the search problem formulated by backward planning are goals of the planning problem

- In Practice:  

You can derive an action if and only if *at least* one of the predicates of the considered state is present in the effect of the considered action.  
  g' is found by 

  - copying g

  - deleting positive effects of the action that are present in the starting state

  - adding all the preconditions of A

- Some goals g' will not be consistent , I would need a consistency check but usually it's not done. 
  Depth first search would suck! limited depth search would be ok, other searches as well.

  

### Hierarchical Task Network

- Search in the space of plans, which means: let's start from an empty plan (just initial state + goal state) as the root.  
  Its children will be all the plans with only one action going from the initial state to the goal state.  
  Their children will have two actions and so on until we find a plan that actually is feasible for reaching the goal state.
- An optimization consists in the Partial Ordering Planning which constraints the actions of the plan to respect a certain order.



### Situation Calculus

- Start from a planning problem and transform it into a satisfiability problem, which means in a very big propositional logic formula.
- a situation is a picture of the world
- situations are objects
- reification: give names to objects
- at(robot1,room6,s_3)   it's fluent --> it is true for situation $x$ but it can be false for situation $y$
- by convention the situation is the last argument of the thing.
- so, you have logical formulas that are changing their truth values in time.
- *<u>Result Action</u>*
  we define very special elements, one of this elements is a function that is called "Result"
  Result takes an action and a situation and returns a new situation:
  Result( Movenorth(Product), S_1) = S_2
  A situation can be thought of as a state.
- it's boring to express everything all the time. 
  we don't have the closed world assumption! 

$$
\forall x \forall s  \space Present(x,s) \space \and \space Portable(x) \rightarrow \space Holding(x,Result(Grab(x),s))
$$



- some predicates are fluent and some are not. 
  Portable(x) is not fluent, it's always portable or not.
  the fact that I'm holding an object is fluent. Present as well. 

- The preconditions are on the left side!
  The effects are on the right side!
  the name of the action is on the right side as well.

- <u>effect axiom</u>   
  what is on the left side is called the *<u>effect axiom</u>* : what is the effect of the action I'm performing?

- every time that I have an $x$ and a $s$ such that the precondition is true and I'm performing the action of grabbing x then it is true. 

- another example of effect axiom
  $$
  \forall x \forall s \space \neg Holding(x,Result(Drop(x),s))
  $$
  In this case no precondition.
  it says that if I'm dropping x in a situation s then i will reach a situation in which im not holding x.

- <u>frame axiom</u>

  Unfortunately we have to specify even what is not changing
  it consists in describing what is not changing:

$$
\forall x \forall c\forall s \space Color(x,c,s)\rightarrow Color(x,c,Result(Grab(x),s))
$$

​	If I grab an object $x$, its color $c$ doesn't change.

​	Another way of writing it is with functions (this means that we can use functions in situation calculus)
$$
\forall x\forall s \space Color(x,s)=Color(x,Result(Grab(x),s))
$$


- *In synthesis for each action that you have you should define an effect axiom + a number of frame axioms. moreover you need to specify an initial state (a set of conjuncted predicates) and goal state. how is the goal specified? it is something like this: it's usually an existentially quantified formula*

$$
\exists x \exists s \space Holding(x,s)
$$

​	(don't know if I copied it completely correctly, maybe the exists s has not been written by Amigoni)

​	can I derive this theorem above from the KB? Planning to proving a theorem
​	kb |- alpha where alpha is the thing above.



- STRIPS was created to simplify all this mess, but:
  Situation Calculus ${\to}$ Too Complex ${\to}$ Move to STRIPS ${\to}$ Sometimes difficult to handle ${\to}$ Move to Situation Calculus but use Propositional Logic instead of First Order Logic.