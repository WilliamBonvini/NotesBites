# Ridge vs Lasso

***Describe and compare the ridge regression and the LASSO algorithms.***    

Before diving into the definitions let's define what is Regularization: it's a technique that makes slight modifications to the learning algorithm such that the model avoids overfitting, so performing better on unseen data. 

Ridge Regression is a Regularization Technique which consists in adding to the Linear Regression Loss Function a penalty term called L2 regularization element:  

${L(\mathbf{w})=\frac{1}{2}\sum_{i=1}^N(t_i-\mathbf{w}^T\phi(\mathbf{x}_i))^2+\frac{\lambda}{2}||\mathbf{w}||^2_2} $ 

Lasso Regression is a Regularization Technique very similar to Ridge Regression, but instead of adding a L2 regularization element, it adds the so called L1 regularization element:  

${{L(\mathbf{w})=\frac{1}{2}\sum_{i=1}^N(t_i-\mathbf{w}^T\phi(\mathbf{x}_i))^2+\frac{\lambda}{2}||\mathbf{w}||_1}} $ 

The main difference between Ridge and Lasso Regression is that Ridge Regression can only shrink to weights of the features close to 0 while Lasso Regression can shrink them all the way to 0.   
This is due to the fact that Ridge Regression squares the features weights, while Lasso Regression considers the absolute value of such weights.  

This means that Lasso Regression can exclude useless features from the loss function, so being better than Ridge Regression at reducing the variance in models that contain a lot of useless features. In contrast, Ridge Regression tends to do a little better when most features are useful.  

You may ask yourself why Lasso is able to shrink some weights exactly to zero, while Ridge doesn't.  
The following example may be explanatory:  

Consider a model with only one feature ${x_1}$. This model learns the following output: ${\hat{f}(x_1)=4x_1}$. 
Now let's add a new feature to the model: ${x_2}$.  Suppose that such second feature does not tell anything new to the model, which means that it depends linearly from ${x_1}$. Actually, ${x_2 = x_1}$.   
This means that any of the following weights will do the job:  
${\hat{f}(x_1,x_2)=4x_1}$

${\hat{f}(x_1,x_2)=2x_1+2x_2}$

${\hat{f}(x_1,x_2)=x_1+3x_2}$

${\hat{f}(x_1,x_2)=4x_2}$

We can generalize saying that ${\hat{f}(x_1,x_2)=w_1x_1+w_2x_2 \space \space\space with \space\space w_1+w_2=4}$.    

Now consider the ${l_1}$ and ${l_2}$ norms of various solutions, remembering that ${l_1=|w_1+w_2|}$ and that  ${l_2=(w_1^2+w_2^2)}$ .  

| ${w_1}$ | ${w_2}$ | ${l_1}$ | ${l_2}$ |
| ------- | ------- | ------- | ------- |
| 4       | 0       | 4       | 16      |
| 2       | 2       | 4       | 8       |
| 1       | 3       | 4       | 10      |
| -1      | 5       | 6       | 26      |

we can see that minimizing ${l_2}$ we obtain ${w_1=w_2=2}$, which means that it, in this case, tends to spread equally the weights.  
While ${l_1}$ can choose arbitrarily between the first three options, as long as the weights have the same sign it's ok. 

Now suppose ${x_2=2x_1}$, which means that ${x_2}$ does not add new information to the model, but such features have different scale now. We can say that all functions with ${w_1+2w_2=k}$ (in the example above ${k=4}$ ) give the same predictions and have same empirical risk.  

For clarity I will show you some of the possible values we can assign to the weights.  

| ${w_1}$ | ${w_2}$ | ${l_1}$ | ${l_2}$ |
| ------- | ------- | ------- | ------- |
| 4       | 0       | 4       | 16      |
| 3       | 0.5     | 3.5     | 9.25    |
| 2       | 1       | 3       | 5       |
| 1       | 1.5     | 2.5     | 3.25    |
| 0       | 2       | 2       | 4       |

${l_1}$ (which translates into Lasso Regression) chooses ${w_1=0 \space ; \space w_2=2}$  
${l_2}$ (which translates into Ridge Regression) chooses ${w_1=1 \space ; \space w_2=1.5}$  
Obviously I'm oversimplifying,  these won't be the actual chosen values for ${l_2}$. Ridge will choose similar values that will better minimize ${l_2}$, I just didn't list all the possible combinations for ${w_1}$ and ${w_2}$, but the important thing is that Lasso will actually go for ${w_1=0;w_2=2}$.  

What have we noticed then?  

- For Identical Features
  - ${l_1}$ regularization spreads weight arbitrarily (all weights same sign)
  - ${l_2}$ regularization spreads weight evenly
- For Linearly Related Features
  - ${l_1}$ regularization chooses the variable with the largest scale, 0 weight to the others
  - ${l_2}$ prefers variables with larger scale, it spreads the weight proportional to scale