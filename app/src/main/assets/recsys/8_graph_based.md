# Graph Based Algorithms 

##### Basic Algorithm

I'm gonna talk about it without considering features. the version with features is worth to be mentioned though (simply add nodes for the feature and you are on the horse (it is an italian dictum, it means you are good to go LeL)).

- we model our data as a graph.

- nodes represent users and items.

- an arc goes from a user node to an item node if such user consumed such item, and viceversa.

- random walk for $n$ steps, where $n$ is odd. usually $n=3$.

- the graph can be formulated as a matrix $A$ such that $|A|=(U+I)\times (U+i)$.  
  $$
  A_{i,j}=\begin{cases} 1 \text{   if $i$ consumed $j$ or if $i$ was consumed by $j$} \\ 0 \text{  otherwise} \end{cases}
  $$

- we create a probability matrix $P$ of the same dimension of $M$ that tells us what is the probability of going to node $j$ from node $i$ ($i$ is strictly a user, $j$ is strictly an item)
  $$
  P_{ij}=\frac{A_{ij}}{\sum_{k}^{U+I} A_{ik}}
  $$

- the probability of getting to node $j$ after $n=3$ steps from node $i$ is  
  $$
  P_{ij}=\sum_a\sum_bP_{ia}P_{ab}P_{bj}
  $$

##### P3Alpha

exactly as I've described before, it does not consider feature. the only thing to be said is that now $P_{ij}$ becomes
$$
P_{ij}=\Bigg(\frac{A_{ij}}{\sum_{k}^{U+I} A_{ik}}\Bigg)^\alpha
$$
It considers $3$ steps, as I've said earlier.

<div style="page-break-after:always"> </div>

##### Page Rank

It is an example of Random Walk with Restart **probably**.

PageRank is an algorithm that measures the transitive influence or connectivity of nodes.   
It can be computed by either iteratively distributing one node’s rank (originally based on degree) over its neighbors or by randomly traversing the graph and counting the frequency of hitting each node during these walks. 
PageRank is used to rank websites in Google’s search results.   
It counts the number, and quality, of links to a page which determines an estimation of how important the page is. The underlying assumption is that pages of importance are more likely to receive a higher volume of links from other pages. 

Google recalculates PageRank scores each time it crawls the Web and rebuilds its index. As Google increases the number of documents in its collection, the initial approximation of PageRank decreases for all documents.

It’s defined as: 
$$
PR(A)=(1-d)+d\bigg(\frac{PR(T_1)}{C(T_1)}+\dots+ \frac{PR(T_n)}{C(T_n)}\bigg)
$$
where 

- we assume that a page $A$ has pages $T_1$ to $T_n$ which point to it (i.e., are citations).
- $d$ is a damping factor which can be set between $0$ and $1$. It is usually set $0.85$.
- $C(A)$ is defined as the number of links going out of page $A$.

The ***normalized*** sum of the the PageRanks is equal to one.

It is an iterative process.

