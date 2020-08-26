# $\alpha$-$\beta$ Pruning

- ${\alpha}$ initial value = + infinity 
- ${\beta}$ initial value = - infinity
- ${v}$ initial value = none.
- ${\alpha}$ & ${\beta}$ are inherited by the daddy
- ${v}$ is inherited by the children
- the first ${v}$ is computed on the left leaf (depth first search)
- Pruning condition
  - <u>If the utility function v is bounded</u>  
    ${\to}$ as soon as we find find a winning path (starting from the root!) for max we end the search there
  - <u>if the utility function ${v}$ is not bounded</u>   
    ${\to  \alpha \ge \beta \implies prune}$



**Zero Sum Game**

A zero sum game is (confusingly) defined as one where the total payoff to all players is the same for every instance of the game.
Chess is zero sum because every game has payoff of either 0+1, 1+0,or 1/2 +1/2.
"constant-sum" would have been a better term,.