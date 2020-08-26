# Association Rules

*Support*
$$
\text{Support}(i,j)=\frac{\text{SupportCount}(i,j)}{N_U}
$$
*Confidence*
$$
\text{conf}(i,j)=\frac{\text{SupportCount}(i,j)}{\text{SupportCount}(i)}
$$
*Similarity as Confidence! - not symmetric*
$$
P(i|j)=\frac{\#<i,j>}{\#<j>+C}=s_{ij}=\text{conf}(j,i)
$$

$$
score_{i\in I}=\sum_\text{r $\in$ rules recommending $i$}\text{support}_r\cdot \color{red}\text{conf}_r
$$

it's a weighted sum of supports, the weights are the confidences.

Generally speaking:

- you compute the support for any pair of items $(i,j)$ and if it is greater or equal to $minsup$, you consider it as a frequent itemset.
- for each frequent itemset $(i,j) $, you consider all the association rules associated to such frequent itemset (in this case $i \to j$, $j \to i$)  and for each association rule, if its confidence is greater than $minconf$ it is a valuable association rule.