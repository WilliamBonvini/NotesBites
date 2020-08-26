# Logic - Part 2

### First Order Logic (FOL)

As any other type of logic, FOL is composed of three elements:

- a formal language that models a fragment of natural language.
- a semantics that defines under which conditions a sentence of the language is true or false.
- a calculus that mechanically determines the validity of a sentence.

Before diving into it let's see an example of FOL sentence:

${\forall x \forall y(Mother(x,y))\iff Female(x) \and Parent(x,y))}$

**Formal language**

Defined by means of

- **A dictionary of symbols**

  - *Logical Symbols*
    $$
    \neg,\and,\forall,=,x,y,z,...
    $$

  - *Descriptive Symbols*
    constituted by 

    - a set C of individual constants
      $$
      a,b,c,...
      $$

    - a set P of predicate symbols (${P,Q,R,..}$) or predicates with a function.
      $$
      P\to N \space that \space assigns \space to \space every \space symbol \space an \space arity \space (n°\space of \space arguments)
      $$

  - *Structural Symbols*
    brackets and commas.

  *Terms* are the symbols utilized to refer to individuals.
  *Functional Terms* are complex terms ${f(t_1,...,t_n)}$ where *f* is the functional symbol with arity *n* and ${t_k}$ are terms.

- **A grammar**
  Made out of *formulas* which are made out of *literals* . I will not go into details since it's not the scope of the course. 
  I will just give a few definitions:

  - *Open and Closed Formulas*
    A formula is called *closed* if every occurrence of variable in the formula is bound.
    It is called *open* in the other cases.
  - *Sentences*
    A closed formula is called sentence.
  - *Predicates*
    A predicate is a property that  a given individual can possess or not, or a relation that can subsist or not subsist among couples, terns, etc. of individuals.

**Semantics**

To assign semantics to a logical language means to define a criterion to determine whether an argument is valid. The validity of an argument is reduced to the relation of logical entailment among formulas. This notion of entailment is based on the concept of truth of a formula. But in general a formula is not true or false per se, but only with respect to a precise interpretation of the descriptive symbols of its language (predicates and constants). It follows that to specify the semantics of a language is to define when formula ϕ is true in a model M, which is expressed by the notation ${M \models \varphi}$.
A few definition:

- *Model*
  A model consist of an interpretation of the predicates and the constants of the language.
- *Value assignment*
  The assignment of a value to a variable of the language.
- *True and False Formulas*
  There exists formula that are true in all models, formulas that are false in every model, formulas that are false depending on the model. For instance, every formula with the form ${\varphi \or \neg \varphi}$ is true in each model, ${\varphi \and \neg \varphi}$ is false in each model, ${\exists xP(x)}$ is true in some models and false in others
- *Validity*
  a formula ${\varphi}$ is valid or logically true if for any model M and for any assignment val we have ${M,val \models \varphi}$
- *Satisfiability*
  a formula ${\varphi}$ is satisfiable if for some model M and for some assignment val we have ${M,val \models \varphi}$.

**Calculus**

Similar to the calculus procedures of Propositional Logic, it's though considered a prerequisite for such course so I will not go deep into it.

### DPLL Algorithm

solve 
$$
\phi_1 |= \phi_2
$$

1. Lets draws 3 vertical lines and put in the middle one all the clauses in AND ($\phi_1$ AND not $ \phi_2$)

2. giving the precedence to one-literals and then the pure literals (literals that appear just in one form (or all negated or all not negated)), insert them in the knowledge base in order of appearance (left section) specifying in the right section what you've done / observations (If you really need to...).
   Every time that I insert a literal in the knowledge base I exploit such information simplifying the clauses in the mid section (A=1 -> notA or B becomes B)

3. The goal is to end up with the empty clause in the mid section

   P.S.: 

   - if we find ourselves in having no pure literals and no one-literals, we consider one of the remaining literals (for example A) and split the solution in two (s' and s''). s' will consider A in the knowledge base, while s'' will consider notA. 
     phi_1 implies phi_2 only if both parallel solutions return an empty clause (not verified, but seems pretty obvious to me).
   - If we find ourselves with only one logical proposition left, for example 
     (A or B), let's choose just the first one in order of appearance and put it in the knowledge base. this means that B can assume whatever value it wants, it won't influence the result.



### Backward Chaining

Backward chaining is the logical process of inferring unknown truths from known conclusions by moving backward from a solution to determine the initial conditions and rules.   
In AI, backward chaining is used to find the conditions and rules by which a logical result or conclusion was reached. An AI might utilize backward chaining to find information related to conclusions or solutions in reverse engineering or game theory applications.  
As a goal-driven and top-down form of reasoning, backward chaining usually employs a <u>depth-first search strategy</u> by starting from a conclusion, result or goal and going backward to infer the conditions from which it resulted.

**Algorithm**

Initially the KB is composed only from the single literals and the Goal List is composed only from the literal we want to infer. In our example we have ${KB=\{E;F;P\}}$ and ${Goal \space List = Z}$.  
The children to be derived can be a conjunct of literals (which translates into two children whose branches are connected via an arc) or a single literal (simply one only-child).

1. Draw the root, which is the end literal to be derived.
2. Analyze the children using depth first search:
   1. if the node is part of our KB  we are happy with it and we mark it with a check mark.
   2. elif the node is derivable and is not in the Goal List we add it to the Goal List and derive it.
   3. elif the node is already in the goal list we end the search for that node
   4. elif the node is not in the Goal List, is not part of the KB, and is not derivable we mark it with an X.
   5. go back to 2

The algorithm ends as soon as you find one of the following:

1. a conjunct of true (check marked) children of the goal literal
2. a true only child of the goal literal
3. end of the search (in this case the goal is not satisfied)

 

**Exam's Example**

![1557584378119](C:\Users\Willi\AppData\Roaming\Typora\typora-user-images\1557584378119.png)





### Forward Chaining

Forward Chaining is a *sound and complete* inference procedure

- **Algorithm**  
  consider rules in the "implication form", do not put them in CNF .  
  1. Start from your knowledge base which is composed only by the one literals that have been provided to you
  2. Apply Modus Ponens.  
     Write the modus ponens derivations in the form ${MP(Preconditions, Effects)}$.
  3. If all the predicates on the right of the clauses (the effects) have been derived stop. You obtained all the sentences entailed by the KB.  
     else go back to step 2  

- **Example**   
  1. ${A \to B}$
  2. ${A}$
  3. ${B \space \space \space  MP(2,1)}$



