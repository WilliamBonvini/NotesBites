# Side Information Recommenders

##### S-SLIM

the basic loss function of S-SLIM is the following
$$
\min_S \alpha||R-RS||_2+(1-\alpha)||F-FS||
$$
where $F=ICM$.

the variant with a penalization term is the following:
$$
\min_S\bigg\{\alpha ||R-RS||_2+\beta||F-FS||_2+ \gamma||S||\bigg\}
$$
the norm of the regularization term can probably be any kind of norm we want.

***Variant of SSLIM***

I think the following is just Collaborative Boosting not explained properly. 
$$
\min_{S_{CF},S_{CB}}\bigg\{\alpha ||R-RS_{CF}||_2+\beta ||F-FS_{CB}||_2+ \gamma ||S_{CF}-S_{CB}||
$$
the norm of the regularization term can probably be any kind of norm we want.

##### Collaborative Boosted Content Based Filtering

it is an alternative version of SSLIM.

1. solve a SLIM problem  
   $$
   \min_{S^{CF}}||R-RS^{CF}||_2+\lambda ||S^{CF}||
   $$

2. we impose the following:  
   $S^{CB}_{ij}\neq \sum_fa_{if}a_{jf} \text{ but } S^{CB}_{ij}= \sum_f a_{if}a_{jf}w_f$

3. we solve  
   $$
   \min_\bar{w}||S^{CF}-S^{CB}||
   $$

4. we discard $S^{CB}$ and we keep the weights vector $\bar{w}$.

we are assuming that $S^{CF}$ is very good.

I think that the fifth step (not specified by the professor) is to solve S-SLIM taking into account an new weighted item content matrix (with the weights learnt).

##### Tensor Factorization

it is an algorithm belonging to the context-aware recommender systems family.  It can be seen as matrix factorization in 3 dimensions.

Now our URM is $3$ dimensional (it can have even more dimensions but we'll use this dimensionality since it is the wisest to use).

$R$ can be factorized as $R=A\cdot B\cdot D^T$. 

$[A]=U\times F$. It represents how much user $u$ likes latent feature $f$.

$[B]=F \times I$. It represents how much latent feature $f$ is important to describe item $i$.

$[D]=F\times C$. It represents how much feature $f$ is relevant in context $c$.
$$
\tilde{R}=\tilde{A}\cdot \tilde{B} \cdot \tilde{D}
$$

$$
\tilde{r}_{uic}=\sum_fa_{uf}b_{if}d_{cf}
$$

we can learn the predicted values for the $3$ matrices by minimizing $||R-\tilde{R}||_2$ or $||R-\tilde{R}||_F$.

Or we can use $BPR$ to have the best accuracy as possible.

Add bias for user and items if the original $R$ matrix contains explicit ratings.

Sparsity problem, solution: penalization term and reduce dimensionality as long as you can.