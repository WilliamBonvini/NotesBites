# Machine Learning Recommenders

#### SLIM, BPR, IALS

##### SLIM

Sparse Linear Methods.  
It is a family of algorithms whose goal is to minimize Residual Sum of Squares.  
we can see a recommendation task as a machine learning task in which we need to approximate the weights of a similarity matrix.    
the formula that SLIM wants to optimize is 
$$
L=|R-RS|_2+ \alpha|S|_1+\beta |S|_F
$$
The one above is probably just an example of possible optimization. SLIM refers t the fact that I want to minimize the residual, and add some penalization for overfitting.

***Elastic Net***
$$
L=|R-RS|_2+ \alpha|S|_1+\beta |S|_2
$$
***Closed Form Solution***

It is possible if only ridge is present as a regularization term.
$$
S_j=(R^TR+\beta I)^{-1}R^T\bar{r}_j
$$
**R**a**t**to **r**a**b**b-m**Ino** **r**a**t**to **r**o**j**o.

***Early Stopping***

- Speak about early stopping.  
- divide dataset in training, validation, and test set.
- Use MSE on training set, but do early stopping on MAP.

##### BPR

Bayesian Pair-wise Ranking.

BPR is able to maximize AUC.

AUC plots fallout (x) with respect to recall (y) .
$$
\text{Fallout}=\frac{\text{num of non relevant recommended items}}{\text{num of non relevant items}}
$$

$$
\text{Recall}=\frac{\text{num of relevant items suggested}}{\text{num of relevant items}}
$$

BPR works both with implicit and explicit ratings.

Problem: popularity bias

***Explicit***

- positive rating: from 4 to 5
- negative rating: from 0 to 2
- Consider the user bias! no formula provided but you can figure it out.

***Implicit***

- positive rating: 1
- negative rating: 0

pick random triplets of user, positive, and negative ratings and optimize through stochastic gradient descent
$$
\tilde{R}=RS
$$

$$
\tilde{x}_{uij}=\tilde{r}^+_{ui}-\tilde{r}^{-}_{uj}
$$

$$
\max\sum_{uij}\log \frac{1}{1 + e^{-x_{uij}}}- ||S||_2
$$

##### Slim BPR

simply, $S$ is computed through Slim. same as above.
$$
\tilde{r}_{ui}=\bar{r}_u\bar{S}_i
$$

$$
\tilde{r}_{uj}=\bar{r}_u\bar{S}_j
$$

$$
\tilde{x}_{uij}=\tilde{r}_{u}-\tilde{r}_{uj}
$$

##### IALS 

Implicit Alternating Least Squares.  
It is for implicit ratings.  
We have to set up a preference and a confidence value for each user-item pair.
$$
p_{ui}=\begin{cases} 1 \ \ \ r_{ui}>0 	\\ 0 	\ \ \ r_{ui}=0 \end{cases}
$$

$$
c_{ui}=1+\alpha r_{ui}
$$

where $\alpha$ is a hyperparameter (usually set to a value between $15$ and $40$).

the loss function is:
$$
\min_{X,Y} \sum_{u,i}c_{ui}(p_{ui}-X^T_uY_i)^2+\lambda\bigg(||X||_2+||Y||_2\bigg)
$$

<div style="page-break-after:always"> </div>