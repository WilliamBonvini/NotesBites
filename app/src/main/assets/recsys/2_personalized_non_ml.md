# Personalized Recommender Systems

### Global Effects

- compute the average rating of all items by all users  
  $$
  r_{avg}=\frac{\sum_{u,i}r_{ui}}{N^+}
  $$
  where $N^+$ is the number of interactions.

- subtract if from each rating in the URM  
  $$
  r'_{ui}=r_{ui}-r_{avg} \ \ \ \ \ \ \forall (u,i) \in \text{Interactions}
  $$

- compute for each item the item bias  
  $$
  b_i=\frac{\sum_ur'_{ui}}{N_i+C}  \ \ \ \ \ \forall i \in I
  $$
  where $N_i$ is the number of users that interacted with item $i$

- remove it from each rating in the URM  
  $$
  r''_{ui}=r'_{ui}- b_i \ \ \ \ \ \ \forall (u,i) \in \text{Interactions}
  $$

- compute for each user the user bias   
  $$
  b_u=\frac{\sum_{i}r''_{ui}}{N_u+C} \ \ \ \ \ \ \forall u \in U
  $$
  where $N_u$ is the number of items rated by $u$.

- remove it from each rating the URM  
  $$
  r'''_{ui}=r_{ui}''-b_u \ \ \ \ \ \ \forall (u,i) \in \text{Interactions}
  $$

$\hat{r}_{ui}=r_{avg}+b_i+b_u$

### Content Based

- Content Based Filtering:   
  use the ICM

- Knowledge Based Filtering:    
  when the user only buys occasionally so we can’t create user profiles (e.g. a consumer electronics shop). We use additional, often manually provided, information about the users and the available items. content not only of items but even of users.

##### 1 - IBCBF

Item Based Content Based Filtering.

*Cosine Similarity*
$$
s_{ij}=\frac{\sum_{a}i_{a}j_{a}}{\sqrt{\sum_{a}i_{a}^2\sum_aj_{a}^2}+C}
$$
where $x_{if}$ tells you if feature $f$ is present in item $i$.
$$
\tilde{r}_{ui}=\frac{\sum_{j\in KNN}r_{uj}s_{ij}}{\sum_{j \in KNN} s_{ij}}
$$

##### 2 - IBCBF with TF-IDF

it is useful to use TF-IDF as well:

*TF*
$$
TF=\frac{N_{ai}}{N_i}
$$
$N_{ai}$ is the number of times attribute $a$ appears in item $i$ (usually $1$).  
$N_i$ is the number of attributes of item $i$.
$$
IDF=\log{\frac{N_I}{N_{a}}}
$$
$N_I$ is the number of items.

$N_{a}$ is the number of items in which attribute $a$ appears.
$$
\text{TF-IDF}(a,i)=TF(a,i)\cdot IDF(a)
$$
In the TF-IDF model, the document is represented not as a  vector of boolean values for each keyword but a s a vector of the computed TF-IDF weights.

### Collaborative Filtering

##### 1 - UBCF

User Based Collaborative Filtering

***Implicit***

*Cosine Similarity*
$$
s_{uv}=\frac{\sum_ir_{ui}r_{vi}}{\sqrt{\sum_{i}r_{ui}^2\sum_ir_{vi}^2}+C}
$$

$$
\tilde{r}_{ui}=\frac{\sum_{v \in KNN}r_{vi}s_{uv}}{\sum_{v \in KNN }s_{uv}}
$$

***Explicit***

*Pearson Similarity*
$$
s_{uv}=	\frac{\sum_i(r_{ui}-r_u)(r_{vi}-r_v)}{\sqrt{\sum_{i}(r_{ui}-r_u)^2\sum_{i}(r_{vi}-r_v)^2}+C}
$$

$$
\tilde{r}_{ui}=\frac{\sum_{v \in KNN}(r_{vi}-r_v)s_{uv}}{\sum_{v \in KNN }s_{uv}}+r_u
$$

##### 2 - IBCF

***Implicit***

*Cosine Similarity*
$$
s_{ij}=\frac{\sum_{u}r_{ui}r_{uj}}{\sqrt{\sum_{u}r_{ui}^2\sum_{u}r_{uj}^2}+C}
$$

$$
\tilde{r}_{ui}=\frac{\sum_{j \in KNN}r_{uj}s_{ij}}{\sum_{j \in KNN }s_{ij}}
$$

***Explicit Ratings***

*Adjusted Cosine Similarity*
$$
s_{ij}=\frac{\sum_u(r_{ui}-r_u)(r_{uj}-r_u)}{\sqrt{\sum_{u}(r_{ui}-r_u)^2\sum_{u}(r_{uj}-r_u)^2}+ C}
$$

$$
\tilde{r}_{ui}=\frac{\sum_{j \in KNN}r_{uj}s_{ij}}{\sum_{j \in KNN }s_{ij}}
$$

probably we can avoid to put the bias into the prediction formula because they rule out each other, being considered always $r_u$.