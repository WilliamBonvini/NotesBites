# Matrix Factorization Techniques

##### FunkSVD

Matrix Factorization with MSE using ALS and iterating.

we learn $\tilde{R}$ as a product between two matrices $A$ and $B$.

$A: U\times F$

$B: I \times F$

$F$ is a hyperparameter and represents the number of latent factors.
$$
L=||R- A \cdot B||_2 + \alpha||A||_? + \beta ||B||_?
$$
Check this out as well, could consider Frobenius instead if you want.
$$
MSE=\sum_{(u,i)\in N^+}(r_{ui}-\tilde{r}_{ui})^2
$$
matrix factorization advantage: there are way less parameters.

 use ALS to update weights.

- $\text{num of latent factors }=1 \to \text{Top Popular}$
- $\text{ num of latent factors} = \infty \to \text{Overfitting}$

##### SVD ++

Version of FunkSVD that works better for explicit ratings.

It considers global effects.  
$$
\tilde{r}_{ui}=\mu +b_u+b_i +A_u\cdot B_i
$$
same loss function of FunkSVD, simply put $\tilde{r}_{ui}$ as I've described it.

##### Adapted SVD ++

problem with SVD++: it is memory based.

let's make it model based.
$$
\tilde{r}_{ui}=\mu +b_u+b_i +\bigg(R_u\cdot B\bigg)\cdot B_i^T
$$

$$
\tilde{r}_{ui}=\mu + b_u +b_i +\sum_f\bigg(\sum_{j}r_{uj}b_{jf}\bigg)b_{if}
$$

the product of this matrices  with its transpose $(B	\cdot B^T)$ returns as a matrix with the same dimensions of a similarity matrix.   
There is only one difference between the original SLIM and this kind of SLIM. In the first, matrix S was a generic matrix, so you were learning all possible values. Here, matrix S has been decomposed into the product of two identical rectangular matrices.  
The two algorithms are the same if I constrain SLIM to learn a product between two identical rectangular matrices instead of simply $S$.

##### Asymmetric SVD

another way to solve the cold start problem, other than Adapted SVD++.
$$
\tilde{r}_{ui}=\mu +b_u+b_i+\sum_f \bigg(\sum_j r_{uj}Q_{fj}\bigg) Y_{fi}
$$
it is called asymmetric because $Q^TY$ is not symmetric anymore.

Asymmetric SVD is model-based and it has a good quality.   
It is considered one of the best recommendations techniques at least for dataset with explicit ratings. 

##### PureSVD

It is the only actual, covered, technique that uses Singular Value Decomposition.
$$
R=U\cdot \Sigma \cdot V^T
$$

$$
[U]=|U|\times k
$$

$$
[\Sigma] = k \times k 
$$

$$
[V]=|I|\times k
$$

$$
U^TU=I
$$

$$
V^TV=I
$$

$$
\Sigma \text{ is a diagonal matrix}
$$

$$
\tilde{R}=\tilde{U}\tilde{\Sigma}\tilde{V}^T
$$

we aim at finding the 3 matrices on the right member of the equation above by minimizing:
$$
\min_{U,\Sigma,V}||R-\tilde{R}||_2
$$
the problem of this approach is that it tends to overfit too much.

How to create an approximate representation of $R$ starting from pure SVD? just take into consideration not all the singular values in $\Sigma$ (reduce $U$ and $V$ accordingly).

***Relationship among FunkSVD and PureSVD***

$$
R=A\cdot B= \text{FunkSVD}
$$

$$
A=U\sqrt{S}
$$

$$
B=\sqrt{S}V^T
$$

$$
R=USV^T=\text{Pure SVD}
$$

FunkSVD can be seen as pure SVD if we put such values as $A$ and $B$ (kind of, not exactly).

***Pure SVD Trick - Folding in***

Pure SVD can be seen as an item based technique that minimizes the mean squared error (computed on all the rating matrix)
$$
R\color{red}\tilde{V}\color{orange}\tilde{V}^T\color{black}=\tilde{U}\tilde{S}\tilde{V}^T\color{red}\tilde{V}\color{orange}\tilde{V}^T\color{black}=\tilde{U}\tilde{S}\color{orange}\tilde{V}^T
$$
$\tilde{V}^T\tilde{V}$ is equal to $I$, but $\tilde{V}\tilde{V}^T$ is not! this is because we are using approximated matrices.

$\tilde{V}\tilde{V}^T$ is a matrix of dimensions items$\times$ items $\to$ it is a similarity matrix.
$$
RS=\tilde{U}\tilde{S}\color{orange}\tilde{V}^T
$$
so if I have a new user (a new row in $R$) it is easy to compute!