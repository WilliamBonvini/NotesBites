# Gaussian Processes

`The important stuff about *Gaussian Processes* is missing from this notes. Check the course's slides.`

***Describe the Gaussian Processes model for regression problems*** (*kriging*)

In probability theory and statistics, a **Gaussian process** is a stochastic process (a collection of random variables indexed by time or space), such that every finite collection of those random variables has a multivariate normal distribution, i.e. every finite linear combination of them is normally distributed. The distribution of a Gaussian process is the joint distribution of all those (infinitely many) random variables, and as such, it is a distribution over functions with a continuous domain, e.g. time or space.

A machine-learning algorithm that involves a Gaussian process uses `lazy learning` and a measure of the similarity between points (the *kernel function*) to predict the value for an unseen point from training data. The prediction is not just an estimate for that point, but also has uncertainty information—it is a one-dimensional Gaussian distribution (which is the marginal distribution at that point).

`*lazy learning* is a learning method in which generalization of the training data is, in theory, delayed until a query is made to the system, as opposed to in *eager learning* , where the system tries to generalize the training data before receiving queries. The main advantage gained in employing a lazy learning method is that the target function will be approximated locally, such as in the k-nearest neighbor algorithm. Because the target function is approximated locally for each query to the system, lazy learning systems can simultaneously solve multiple problems and deal successfully with changes in the problem domain.`

*Slides Definition*:

A *Gaussian Process* is defined as a probability distribution over functions $y(\mathbf{x})$ such that the set of values of $y(\mathbf{x})$ evaluated at an arbitrary set of points $\mathbf{x}_1,\dots,\mathbf{x}_n$ *jointly have a Gaussian distribution*. 

The distribution is completely specified by the second-order statistics, the mean and the covariance:

- Usually we do not have any prior information about the mean of $y(x)$, so we'll take it to be $0$.

- The covariance is given by the *kernel function*
  $$
  \mathbb{E}[y(\mathbf{x}_n)y(\mathbf{x}_m)] = k(\mathbf{x}_n,\mathbf{x}_m)
  $$

Let's talk about *Gaussian Process Regression* (aka *Kriging*)

Take into account the noise on the target
$$
t_n = y(\mathbf{x_n})+\epsilon_n
$$
Random noise under a *Gaussian distribution*
$$
p(t_n|y(\mathbf{x}_n))=\mathcal{N}(t_n|y(\mathbf{x}_n),\sigma^2)
$$
Because the noise is *independent* on each data point, the joint distribution is still *Gaussian*:
$$
p(\mathbf{t}|\mathbf{y})=\mathcal{N}(\mathbf{t}|\mathbf{y},\sigma^2\mathbf{I}_N)
$$
Since $p(\mathbf{y}) = \mathcal{N}(\mathbf{y}|\mathbf{0},\mathbf{K})$ , we can compute the marginal distribution:
$$
p(\mathbf{t})=\int p(\mathbf{t}|\mathbf{y})p(\mathbf{y})d\mathbf{y}=\mathcal{N}(\mathbf{t}|\mathbf{0},\mathbf{C})
$$
where $C(\mathbf{x}_n,\mathbf{x}_m) = k(\mathbf{x}_n,\mathbf{x}_m)+\sigma^2\delta_{nm}$.

Since the two *Gaussians* are *independent* their covariances simply *add*.