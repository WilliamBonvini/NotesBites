# Machine vs Deep Learning 

#### What is machine learning 

Machine Learning is a category of research and algorithms focused on finding patterns in data and using those patterns to make predictions.

A computer program is said to learn from experience $E$ with respect to some class of task $T$ and a performance measure $P$, if its performance at tasks $T$, as measured by $P$, improves because of experience $E$.

#### What are the machine learning paradigms and their characteristics

- Supervised Learning
- Unsupervised Learning
- Reinforcement Learning

#### What are the machine learning tasks and their characteristics

- Image recognition tasks
  - classification
  - object detection
- Regression
- Image captioning
- image/text generation
- Clustering
- Text Analysis
- Speech Recognition



#### What is deep learning and how it differs from machine learning

It is a branch of ML, has hidden layers. not easily explicable.

Usage of Big Data! Massive computational power.

deep learning is about learning data representation from data.

#### Examples of successful deep learning applications

classification, text generation, speech recognition, object detection,...

#### What is transfer learning and what it is used for

take a pretrained model and use it for the same task, eventually on a different type of dataset.

in neural networks for image processing tasks we could copy the architecture and the weights of the part of the network that is in charge of finding the high level features and concatenate to it fully connected layers to be able to solve the problem in hand.

in transfer learning the weights are freezed, while in fine tuning such weights are initialized with the successful architecture's weight and updated via gradient descent for the task in hand.

#### What is a feature and its role in machine and deep learning

A feature is the basic element to recognize patterns in the task. They can be crafted or learned, with pros and cons.   
They allow the NN to understand what information carries the data wrt the task, and correctly give an answer.

Data are made out of features, so features are data's intrinsic information. 

features are based on domain knowledge or heuristics, however they need to be carefully designed depending on the task. they are fixed and sometimes they do not generalize between datasets.

We need to get features right.

Deep Learning assumes it is possible to learn a hierarchy of descriptors with increasing abstraction

#### What is cross-validation and what it is used for

It's a technique used when not much data is available to train/validate, so the model is trained on the majority of the data and validated on a set of size n/k. This process is repeated k times, training every time on a different subset and validate on the n/k items out of the training data.  
If k is n, meaning validation dataset has size 1, is LOOCV (leave one out Cross validation)

#### What are hyperparameters and identify those for all the models presented in the course

- number of hidden layers
- number of neurons of hidden layers
- penalization rate
- filter size, filter number
- stride
- activation function

#### What is Maximum likelihood estimation and how does it work in practice

= choose parameters which maximize data probability.
	In practice: 
	-write $L = P(Data|\Theta)$
	-[optional] $l = \log(L)$
	-calculate $\frac{dl}{d\Theta}$
	-solve $\frac{dl}{d\Theta} = 0$
	-check that the result is a maximum

#### What is Maximum a posteriori and how does it work in practice 


Is $argmax_w P(w|Data) = argmax_w P(D|w)P(w)$ meaning P(w) is an assumption on parameters distribution. Reduces model freedom with an assumption made by us. e.g. small weights to improve generalization of neural networks