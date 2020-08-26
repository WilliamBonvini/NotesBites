# Ridge Regression

**Describe the supervised learning technique called ridge regression for regression problems.**    
Ridge Regression is a regularization technique that aims to reduce model complexity and prevent over-fitting which may result from simple linear regression.  
In ridge regression, the cost function is altered by adding a penalty equivalent to the square of the magnitude of the coefficients.  
$$
cost \space function=\sum_{i=1}^M(y_i-\sum_{j=0}^p(w_j\times x_{ij})\space)^2+\lambda\sum_{j=0}^pw_j^2
$$
where ${M}$ is the number of samples and ${p}$ is the number of features.

The penalty term ${\lambda}$ regularizes the coefficients of the features such that if they take large values the optimization function is penalized. 

When ${\lambda \to 0}$, the cost function becomes similar to the linear regression cost function. So lowering ${\lambda}$, the model will resemble the linear regression model.

It is always principled to standardize the features before applying the ridge regression algorithm. Why is this? The coefficients that are produced by the standard least squares method are scale equivariant, i.e. if we multiply each input by ${c}$ then the corresponding coefficients are scaled by a factor of ${\frac{1}{c}}$. Therefore, regardless of how the predictor is scaled, the multiplication of the coefficient and the predictor ${(w_jx_j)}$ remains the same. However, this is not the case with ridge regression, and therefore, we need to standardize the predictors or bring the predictors to the same scale before performing ridge regression. the formula used to do this is given below.
$$
\hat{x}_{ij}=\frac{x_{ij}}{\sqrt{\frac{1}{n}\sum^n_{i=1}(x_{ij}-\bar{x}_j)^2}}
$$
Since ${\lambda}$ is not defined a priori, we need a method to select a good value for it.  
We use Cross-Validation for solving this problem: we choose a grid of ${\lambda}$ values, and compute   
the cross-validation error rate for each value of ${\lambda}$.  
​We then select the value for ${\lambda}$ for which the cross-validation error is the smallest.  
​Finally, the model is re-fit using all of the available observations and the selected value of ${\lambda}$.  
​Restelli offers the following cost function notation:

${L(\mathbf{w})=L_D(\mathbf{w})+\lambda L_W(\mathbf{w}) }$

where ${L_D(\mathbf{w})}$ is the error on data terms (e.g. RSS) and ${L_W(\mathbf{w})}$ is the model complexity term.

By taking ${L(\mathbf{w})=\frac{1}{2} \mathbf{w}^T\mathbf{w}=\frac{1}{2}||\mathbf{w}||^2_2}$

we obtain:
$$
L(\mathbf{w})=\frac{1}{2}\sum_{i=1}^N(t_i-\mathbf{w}^T\phi(\mathbf{x}_i))^2+\frac{\lambda}{2}||\mathbf{w}||^2_2
$$
We observe that the loss function is still quadratic in **w**:
$$
\hat{\mathbf{w}}_{ridge}=(\lambda \mathbf{I} + \mathbf{\Phi}^T\mathbf{\Phi})^{-1}\mathbf{\Phi}^T\mathbf{t}
$$
(Source: Restelli's Slides)

Ridge Regression is, for example, used when the number of samples is relatively small wrt the  
number of features.  
Ridge Regression can improve predictions made from new data (i.e. reducing variance) by  
making predictions less sensitive to the Training Data.