# Ranking Metrics

**Average Precision at $N$**
$$
AP@N=\frac{1}{\min(m,N)}\sum_{k=1}^NP(k)rel(k)
$$

- $m$ is the number of relevant items in the entire system.
- $N$ is the number of items recommended.
- $P(k)$ is the precision at cutoff $k$.
- $rel(k)$ is $1$ if item $k$ is relevant, $0$ otherwise.

**MAP at $N$**
$$
MAP@N=\frac{1}{|U|}\sum_{u=1}^{|U|}(AP@N)_u
$$

<div style="page-break-after:always"> </div>

# Taxonomy

![image-20200216104451475](C:\Users\Willi\AppData\Roaming\Typora\typora-user-images\image-20200216104451475.png)

# Non Personalized Recommenders

##### 1 - Top Popular

*Implicit & Explicit*

recommend items with the most number of interactions.  
$s$ stands for score, $b$ is a binary variable telling us whether the interaction happened or not.
$$
s_i=\sum_{u \in U}b_{ui}
$$
sort $\bar{s}$ in descending order and take the first $k$ items.  
Most Popular items are those who have most ratings whatever is their value. 
It is good to remove items the user have already seen.

##### 2 - Top Rated

$$
\hat{r}_i=\frac{\sum_{u \in U}r_{ui}}{N_{i}+ C}
$$

$N_{i}$ is the number of users that have given a score to item $i$.

$C$ is the shrinkage.



# Personalized Recommenders

we will talk about them deeply in the following modules