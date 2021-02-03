import time
import numpy as np
import h5py
import matplotlib.pyplot as plt
import scipy
from PIL import Image
from scipy import ndimage

np.random.seed(1)


def load_data(path):
    train_dataset = h5py.File(path + '/train_catvnoncat.h5', "r")

    train_set_x_orig = np.array(train_dataset["train_set_x"][:])  # your train set features
    train_set_y_orig = np.array(train_dataset["train_set_y"][:])  # your train set labels

    test_dataset = h5py.File(path + '/test_catvnoncat.h5', "r")
    test_set_x_orig = np.array(test_dataset["test_set_x"][:])  # your test set features
    test_set_y_orig = np.array(test_dataset["test_set_y"][:])  # your test set labels

    classes = np.array(test_dataset["list_classes"][:])  # the list of classes

    train_set_y_orig = train_set_y_orig.reshape((1, train_set_y_orig.shape[0]))
    test_set_y_orig = test_set_y_orig.reshape((1, test_set_y_orig.shape[0]))

    return train_set_x_orig, train_set_y_orig, test_set_x_orig, test_set_y_orig, classes


def initialize_parameters_deep(layer_dims):
    """
    Arguments:
    layer_dims -- python array (list) containing the dimensions of each layer in our network

    Returns:
    parameters -- python dictionary containing your parameters "W1", "b1","W2", "b2":
    """

    np.random.seed(3)
    parameters = {}

    parameters["W1"] = np.random.randn(layer_dims[1], layer_dims[0]) * 0.1
    parameters["b1"] = np.zeros((layer_dims[1], 1))
    parameters["W2"] = np.random.randn(layer_dims[2], layer_dims[1]) * 0.1
    parameters["b2"] = np.zeros((layer_dims[2], 1))

    return parameters


def sigmoid(x):
    return 1 / (1 + np.exp(-x))


def relu(z):
    return np.maximum(0, z)


def linear_activation_forward(X, parameters):
    """
    Implement the forward propagation

    Arguments:
    X -- input data:
    parameters -- python dictionary containing your parameters "W1", "b1","W2", "b2":

    Returns:
    AL -- output of the forward propogation
    """
    caches = []

    Z1 = np.add(np.dot(parameters["W1"], X), parameters["b1"])
    # relu
    A1 = relu(Z1)

    cache = (X, parameters["W1"], parameters["b1"], Z1)
    caches.append(cache)

    Z2 = np.add(np.dot(parameters["W2"], A1), parameters["b2"])
    # sigmoid
    A2 = sigmoid(Z2)

    cache2 = (A1, parameters["W2"], parameters["b2"], Z2)
    caches.append(cache2)
    # AL --> A2
    return A2, caches


def compute_cost(AL, Y):
    """
    Implement the cost function defined by equation (7).

    Arguments:
    AL -- probability vector corresponding to your label predictions, shape (1, number of examples)
    Y -- true "label" vector (for example: containing 0 if non-cat, 1 if cat), shape (1, number of examples)

    Returns:
    cost -- cost
    """
    m = Y.shape[1]
    costt = np.add(np.multiply(Y, np.log(AL)), np.multiply(1 - Y, np.log(1 - AL)))
    cost = (-1 / m) * np.sum(costt, axis=1)

    return cost


def dRelu(x):
    x[x <= 0] = 0
    x[x > 0] = 1
    return x


def dSigmoid(Z):
    s = 1 / (1 + np.exp(-Z))
    dZ = s * (1 - s)
    return dZ


def linear_activation_backward(X, cost, caches):
    """
    Implement the backward propagation

    Arguments:
    X -- input data
    cost -- cost

    Returns:
    grads --  A dictionary with the gradients
             grads["dW1"]
             grads["db1"]
             grads["dW2"]
             grads["db2"]
    """
    grads = {}
    # sigmoid
    A, W, b, Z = caches[-1]
    m = A.shape[1]
    dZ = cost * dSigmoid(Z)

    dA = np.dot(W.transpose(), dZ)
    dW = (1 / m) * np.dot(dZ, A.transpose())
    db = (1 / m) * np.sum(dZ, axis=1, keepdims=True)

    grads["dA2"] = dA
    grads["dW2"] = dW
    grads["db2"] = db

    # relu
    A, W, b, Z = caches[0]
    m = A.shape[1]
    dZ = dRelu(Z)

    dA = np.dot(W.transpose(), dZ)
    dW = (1 / m) * np.dot(dZ, A.transpose())
    db = (1 / m) * np.sum(dZ, axis=1, keepdims=True)

    grads["dA1"] = dA
    grads["dW1"] = dW
    grads["db1"] = db

    return grads


def update_parameters(parameters, grads, learning_rate):
    """
    Update parameters using gradient descent

    Arguments:
    parameters -- python dictionary containing your parameters
    grads -- python dictionary containing your gradients, output of L_model_backward

    Returns:
    parameters -- python dictionary containing your updated parameters
             parameters["W1"]
             parameters["b1"]
             parameters["W2"]
             parameters["b2"]
    """
    parameters["W1"] = parameters["W1"] - learning_rate * grads["dW1"]
    parameters["b1"] = parameters["b1"] - learning_rate * grads["db1"]

    parameters["W2"] = parameters["W2"] - learning_rate * grads["dW2"]
    parameters["b2"] = parameters["b2"] - learning_rate * grads["db2"]

    return parameters


def two_layer_model(X, Y, layers_dims, learning_rate=0.0075, num_iterations=3000):
    """
    Implements a two-layer neural network: LINEAR->RELU->LINEAR->SIGMOID.

    Arguments:
    X -- input data, of shape (n_x, number of examples)
    Y -- true "label" vector (containing 1 if cat, 0 if non-cat), of shape (1, number of examples)
    layers_dims -- dimensions of the layers (n_x, n_h, n_y)
    num_iterations -- number of iterations of the optimization loop
    learning_rate -- learning rate of the gradient descent update rule
    print_cost -- If set to True, this will print the cost every 100 iterations

    Returns:
    parameters -- a dictionary containing W1, W2, b1, and b2
    """

    np.random.seed(1)
    costs = []  # to keep track of the cost

    # Initialize parameters dictionary, by calling one of the functions you'd previously implemented
    ### START CODE HERE ### (≈ 1 line of code)
    parameters = initialize_parameters_deep(layers_dims)
    ### END CODE HERE ###

    # Loop (gradient descent)

    for i in range(0, num_iterations):

        # Forward propagation: LINEAR -> RELU -> LINEAR -> SIGMOID. Inputs: "X, W1, b1, W2, b2". Output: "A1, cache1, A2, cache2".
        ### START CODE HERE ###
        AL, caches = linear_activation_forward(X, parameters)
        ### END CODE HERE ###

        # Compute cost
        ### START CODE HERE ###
        cost = compute_cost(AL, Y)
        ### END CODE HERE ###

        # Backward propagation.
        ### START CODE HERE ###
        # Compute derivation of Cost wrt A
        dCost = -(np.divide(Y, AL) - np.divide(1 - Y, 1 - AL))
        grads = linear_activation_backward(AL, dCost, caches)
        ### END CODE HERE ###

        # Update parameters.
        ### START CODE HERE ###
        parameters = update_parameters(parameters, grads, learning_rate)
        ### END CODE HERE ###

        # Print the cost every 100 training example
        if i % 100 == 0:
            print("Cost after iteration {}: {}".format(i, np.squeeze(cost)))
        if i % 100 == 0:
            costs.append(cost)

    return parameters, costs

def plot_loss(costs):
    # Ploting the Cost
    x = [i for i in range(0,3000,100)]
    plt.plot(x, costs)
    plt.xlabel("n iteration")
    plt.ylabel("cost")


def predict(test_x, test_y, parameters):
    """
    Predict test data
    test_x -- test data
    test_y -- true "label" vector (for example: containing 0 if non-cat, 1 if cat), shape (1, number of examples)

    Returns:
    accuracy -- accuracy of your model

    """
    m = test_x.shape[1]
    # forward propagation
    A2, caches = linear_activation_forward(test_x, parameters)

    A2 = A2.reshape(-1)
    pre_value = np.where(A2 > 0.5, 1, 0)

    accuracy = np.sum(pre_value == test_y) / m

    return accuracy

### CONSTANTS DEFINING THE MODEL ####
n_x = 12288  # num_px * num_px * 3
n_h = 7
n_y = 1
layers_dims = (n_x, n_h, n_y)

# Caching Weights
import pickle

def main(layers_dims):
    # train_x, train_Y, test_x, test_Y, classes
    train_x_orig, train_y, test_x_orig, test_y, classes = load_data('datasets')
    # Reshape the training and test examples
    train_x_flatten = train_x_orig.reshape(train_x_orig.shape[0], -1).T
    test_x_flatten = test_x_orig.reshape(test_x_orig.shape[0], -1).T

    # Standardize data to have feature values between 0 and 1.
    train_x = train_x_flatten / 255.
    test_x = test_x_flatten / 255.

    print("train_x's shape: " + str(train_x.shape))
    print("test_x's shape: " + str(test_x.shape))

    parameters, costs = two_layer_model(train_x, train_y, layers_dims)
    plot_loss(costs)

    train_accuracy = predict(train_x, train_y, parameters)
    print("Train Data Accuracy: " + str(train_accuracy))
main(layers_dims)
