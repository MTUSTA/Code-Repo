import numpy as np
import matplotlib.pyplot as plt

np.random.seed(1)


def initialize_parameters(layerdims):
    '''layerdims: List containing no. of units in each layer

    Returns:
    parameters: A dict consist of all learnable parameters (W1,b1, W2,b2, ...)
    '''
    parameters = {}
    L = len(layerdims)
    for i in range(1, L):
        parameters["W" + str(i)] = np.random.randn(layerdims[i], layerdims[i - 1]) * 0.1
        parameters["b" + str(i)] = np.zeros((layerdims[i], 1))

    return parameters


def forward(A_prev, W, b, activation):
    ''' Forward Propagation for Single layer
    A_prev: Activation from previous layer (size of previous layer, Batch_size)
        A[0] = X
    W: Weight matrix (size of current layer, size of previous layer)
    b: bias vector, (size of current layer, 1)

    Returns:
    A: Output of Single layer
    cache = (A_prev,W,b,Z), these will be used while backpropagation
    '''
    # Linear
    Z = np.add(np.dot(W, A_prev), b)

    # Activation Function
    if activation == "sigmoid":
        A = 1 / (1 + np.exp(-Z))

    if activation == "relu":
        A = np.maximum(0, Z)

    cache = (A_prev, W, b, Z)

    return A, cache


def L_layer_forward(X, parameters, layerdims):
    ''' Forward propagation for L-layer

     [LINEAR -> RELU]*(L-1)   ->    LINEAR->SIGMOID

     X: Input matrix (input size/no. of features, no. of examples/BatchSize)
     parameters: dict of {W1,b1 ,W2,b2, ...}
     layerdims: Vector, no. of units in each layer  (no. of layers,)

    Returns:
     y_hat: Output of Forward Propagation
     caches: (A_prev,W,b,Z) *(L-1 times , of 1,2,..L layers)
    '''
    caches = []
    L = len(layerdims) - 1
    A = X

    # L[0] is units for Input layer
    # [LINEAR -> RELU]*(L-1)    Forward for L-1 layers
    for l in range(1, L):
        A_prev = A
        A, cache = forward(A_prev, parameters["W" + str(l)], parameters["b" + str(l)], "relu")
        caches.append(cache)

    # Forward for Last layer
    # [Linear -> sigmoid]
    y_hat, cache = forward(A, parameters["W" + str(l + 1)], parameters["b" + str(l + 1)], "sigmoid")
    caches.append(cache)

    return y_hat, caches


def compute_cost(y_hat, Y):
    '''Computes the Loss between predicted and true label
    y_hat: Predicted Output (1, no. of examples)
    Y: Actual label vector consist of 0/1 (1, no. of examples)

    '''
    m = Y.shape[1]
    costt = np.add(np.multiply(Y, np.log(y_hat)), np.multiply(1 - Y, np.log(1 - y_hat)))
    cost = (-1 / m) * np.sum(costt, axis=1)

    return cost


def backward(dA, cache, activation):
    '''Backward propagation for single layer
    dz: Derivative of Cost wrt Z   (of current layer)
    cache: tuple of (A_prev,W,b,Z)

    Retuns:
    dW: Gradient of Cost wrt W, (having same shape as W)
    db: Gradient of Cost wrt b, (having same shape as b)
    dA_prev: Gradient of Cost wrt Activation (same shape as A_prev)
    '''
    A_prev, W, b, Z = cache
    m = A_prev.shape[1]

    # Computing derivative of Cost wrt Z

    # dA, Z,
    if activation == "relu":
        dZ = np.array(dA, copy=True)
        dZ[Z <= 0] = 0

    if activation == "sigmoid":
        s = 1 / (1 + np.exp(-Z))
        dZ = dA * s * (1 - s)

    # Computing derivative of Cost wrt A & W  & b
    dA_prev = np.dot(W.transpose(), dZ)
    dW = (1 / m) * np.dot(dZ, A_prev.transpose())
    db = (1 / m) * np.sum(dZ, axis=1, keepdims=True)

    return dA_prev, dW, db


def L_layer_backward(y_hat, Y, caches, layerdims):
    ''' Backward Propogation from layer L to 1
    y_hat:  predicted output
    Y:      true values
    caches: list of caches stored while forward Propagation
                (A_prev,W,b,Z) *(L-1 times , of 1,2,..L-1 layers) with relu
                (A_prev,W,b,Z) (for layer L, with sigmoid)
    layerdims:List having no. of units in each layer
    Returns:
    grads: A dict containing gradient (dA_i, dW_i, db_i), this will be used while updating parameters
    '''
    AL = y_hat
    L = len(layerdims) - 1
    grads = {}

    # Intializing Backpropagation

    # Compute derivation of Cost wrt A
    dAL = -(np.divide(Y, AL) - np.divide(1 - Y, 1 - AL))

    # Compute derivative of Lth layer (Sigmoid -> Linear) gradients.
    # Inputs: (AL, Y, caches)     Outputs: (grad["dAL"], grad["dWL] , grad["dbL"])
    grads["dA" + str(L)], grads["dW" + str(L)], grads["db" + str(L)] = backward(dAL, caches[-1], activation="sigmoid")

    # Compute derivative for (1,2,..L-1)layers (relu -> Linear) gradients.
    # Inputs:(grads[dAL], caches)   Outputs:(grads(dA_i, dW_i, db_i) )

    for i in list(reversed(range(L - 1))):
        current_cache = caches[i]

        a, b, c = backward(grads["dA" + str(i + 2)], current_cache, activation="relu")
        grads["dA" + str(i + 1)] = a
        grads["dW" + str(i + 1)] = b
        grads["db" + str(i + 1)] = c

    return grads


def update_params(params, grads, learning_rate):
    '''
    parameters: dict of (W1,b1, W2,b2,...)
    grads: Gradients of(A,W,b) stored while Backpropagation (dA,dW,db)

    returns: updated parameters
    '''
    # As each layer has 2 parameters (W,b)
    L = len(params) // 2

    for l in range(L):
        params["W" + str(l + 1)] = params["W" + str(l + 1)] - learning_rate * grads["dW" + str(l + 1)]
        params["b" + str(l + 1)] = params["b" + str(l + 1)] - learning_rate * grads["db" + str(l + 1)]

    return params


import h5py


def load_data():
    train_dataset = h5py.File('datasets/train_catvnoncat.h5', "r")
    train_set_x_orig = np.array(train_dataset["train_set_x"][:])  # your train set features
    train_set_y_orig = np.array(train_dataset["train_set_y"][:])  # your train set labels

    test_dataset = h5py.File('datasets/test_catvnoncat.h5', "r")
    test_set_x_orig = np.array(test_dataset["test_set_x"][:])  # your test set features
    test_set_y_orig = np.array(test_dataset["test_set_y"][:])  # your test set labels

    classes = np.array(test_dataset["list_classes"][:])  # the list of classes

    train_set_y_orig = train_set_y_orig.reshape((1, train_set_y_orig.shape[0]))
    test_set_y_orig = test_set_y_orig.reshape((1, test_set_y_orig.shape[0]))

    return train_set_x_orig, train_set_y_orig, test_set_x_orig, test_set_y_orig, classes


# Loading DataSet
train_x, train_Y, test_x, test_Y, classes = load_data()

# No. of Examples, Image Height, Image Width, no. of Channels(RBG)
print(f"Training Input Data {train_x.shape}")

# consist of 0/1, 1:if it's a cat else 0
print(f"Training labels{train_Y.shape}")

# Example
index = 11
plt.imshow(train_x[index])
plt.show()

# Reshape each image to Vector, X to (Single_Vector 64*64*3,  no.of examples)
train_Xflat = train_x.reshape(-1, train_x.shape[0])
test_Xflat = test_x.reshape(-1, test_x.shape[0])

# Scaling pixel values b/w 0 to 1
train_X = train_Xflat / 255
test_X = test_Xflat / 255

print(f"Training Data{train_X.shape}")
print(f"Test data{test_X.shape}")

# Model Configuration
# len(layer_dims), will be no. of layers with Input & Output layers
layer_dims = [12288, 7, 1]

learning_rate = 0.0075

# No. of Gradient Descent Iterations
num_itr = 3000


def image_classifier(X, Y, layer_dims, learning_rate, num_itr, parameters, initialize=False):
    ''' Implements a L-layer NN:
    [Linear->Relu] *(L-1)times  ->  [Linear->Sigmoid]

    X: Input data(Images) (Height*Weidth*3 , no. of examples)
    Y: True labels, consist of 0/1      (1, no. of examples)
    layer_dims: list, where each value is no. of units.
    learning_rate: for parameters update
    num_itr: no. of iterations of Gradient Descent

    Returs:
    parameters: parameters learnt during Model Training.
    '''
    costs = []

    if initialize:
        parameters = initialize_parameters(layer_dims)

    # Gradient Descent
    for i in range(num_itr):

        # Forward propagation: [LINEAR -> RELU]*(L-1) -> LINEAR -> SIGMOID
        y_hat, caches = L_layer_forward(X, parameters, layer_dims)

        # Compute Cost
        cost = compute_cost(y_hat, Y)

        # Backward propagation
        grads = L_layer_backward(y_hat, Y, caches, layer_dims)

        # Update Parameters
        parameters = update_params(parameters, grads, learning_rate)

        if i % 200 == 0:
            print(f"cost {i}: {np.squeeze(cost)}")
        if i % 100 == 0:
            costs.append(cost[0])

    # Ploting the Cost
    plt.plot(costs)
    plt.xlabel("n iteration")
    plt.ylabel("cost")
    plt.show()
    return parameters


parameters = image_classifier(train_X, train_Y, layer_dims, learning_rate, num_itr, 0, initialize=True)

# Caching Weights
import pickle

pickle.dump(parameters, open('parameters.pkl', 'wb'))
parameters = pickle.load(open("parameters.pkl", 'rb'))


# Defining predict function
def predict(X, Y, parameters):
    m = X.shape[1]
    # forward propagation
    y_hat, caches = L_layer_forward(X, parameters, layer_dims)

    y_hat = y_hat.reshape(-1)
    predicted = np.where(y_hat > 0.5, 1, 0)

    accuracy = np.sum(predicted == Y) / m

    return (accuracy, predicted)


# Show Wrong predicted Images
def mislabel(X, Y, y_hat):
    xx = np.array(Y + y_hat)
    mislabel_index = (np.where(xx == 1))[1]
    return mislabel_index


train_accuracy, train_predictedValues = predict(train_X, train_Y, parameters)
print(f"Train Data Accuracy: {train_accuracy}")
# More Accuracy can be obtain by training again at lower learning_rate

# Show Mislabeled Image
mislabel_index = mislabel(train_X, train_Y, train_predictedValues)
print("Mislabel Index", mislabel_index)

indexx = mislabel_index[1]
plt.imshow(train_x[indexx])
plt.show()
