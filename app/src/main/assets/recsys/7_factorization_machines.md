# Factorization Machines

we turn the recommending problem into a regression problem.

columns corresponding to users.

columns corresponding to items.

columns corresponding to content.
$$
\tilde{r}_{k}=\omega_0+\bar{x}_k^T\bar{\omega} +\bar{x}_k^TW\bar{x}_k
$$
$\bar{\omega}$ is a vector of weights of dimension $U+I$.

$W$ is a matrix of weights of dimensionality $(U+I)\times (U+I)$. It models how much user $u$ likes item $j$.

$W$ is very big though, so read the following:

The way it is written in the paper it is the following, stick with it if you want to be sure:
$$
\hat{y}(\bar{x}):=w_0+\sum_i^nw_ix_i+\sum_{i=1}^n\sum_{j=i+1}^n<\bar{v}_i,\bar{v}_j>x_ix_j
$$

$$
n= \text{num cols}, \ \ w_0 \in \R, \ \ \bar{w} \in \R^n, \ \ V \in \R^{n\times k}
$$

$<\cdot,\cdot > $ is the dot product of two vectors of size $k$:
$$
<\bar{v}_i,\bar{v}_j>:=\sum_{f=1}^kv_{if}v_{jf} 
$$
a row $\bar{v}_i$ within $V$ describes the $i$-th variable with $k$ factors. $k \in \N^+_0$ is a hyperparameter that defines the dimensionality of the factorization.

- $w_0$ is the global bias
- $w_i$ models the strength of the $i$-th variable
- $w_{i,j}=<\bar{v}_i,\bar{v}_j>$ models the interaction between the $i$-th and $j$-th variable. Instead of using an own model parameter $w_{i,j} \in \R$ for each interaction, the $FM$ models the interaction by factorizing it.

$FM$ can be applied to a variety of prediction tasks, among them there are:

- Regression (use MSE or similar loss)
- Binary classification  
- Ranking 

BPR is a good optimization metric to use in this case, but MSE is the one implemented in libFM.

[source](https://www.csie.ntu.edu.tw/~b97053/paper/Rendle2010FM.pdf)

Important:  it is possible to prove that, if you use this formula and you strictly follow the rule of putting one only for the user and the item that did the rating, the formula is identical to collaborative filtering matrix factorization, but more complex to write. 