import numpy as np
import sys
import os
import ast
from PIL import Image
from tqdm import tqdm
import matplotlib.pyplot as plt


class CNN:
    def __init__(self, layer_dims=None, learning_rate=0.002, num_epoch=100, batch_size=16, layer_activation="relu",
                 last_layer_act_func="softmax"):
        if layer_dims is None:
            raise ImportError("You need enter layer dims Like [900,6] ")
        if len(layer_dims) < 2:
            raise ImportError("You must enter at least 2 layers ->1 input 1 output")
        self.layer_dims = layer_dims
        # setting dataset
        self.train_X = None
        self.train_Y = None
        self.test_X = None
        self.test_Y = None
        self.class_info = {}
        # hyper parameters
        self.learning_rate = learning_rate
        self.num_epoch = num_epoch
        self.batch_size = batch_size
        self.layer_activation = layer_activation
        self.last_layer_act_func = last_layer_act_func

    def read_train(self, path, modelname="train"):
        if not os.path.exists("models"):
            os.makedirs("models")
        onlydirectory = [name for name in os.listdir(path) if os.path.isdir(os.path.join(path, name))]

        train_X = []
        train_Y = []
        for i in range(len(onlydirectory)):
            print(path +"\\"+ onlydirectory[i])
            path_directory = path + "\\" + onlydirectory[i]
            files = os.listdir(path_directory)
            self.class_info[i] = onlydirectory[i]
            for image in files:
                im = Image.open(path_directory + "\\" + image)
                im = im.resize((30, 30))
                im = im.convert('L')
                # PIL to numpy array
                data = np.asarray(im)
                data = data.flatten()
                data = np.divide(data, 255)
                train_X.append(data)
                train_Y.append(i)

        self.save(np.array(train_X, dtype=float), path=modelname + "_X")
        self.save(np.array(train_Y, dtype=int), path=modelname + "_Y")
        file1 = open("models/" + modelname + "class_info.txt", "w")
        file1.write(str(self.class_info))
        file1.close()

    def load_train(self, modelname="train"):

        if os.path.exists('models/' + modelname + "_X.npy"):
            if modelname == "train":
                self.train_X = self.load(path="models/" + modelname + "_X.npy")
                self.train_Y = self.load(path="models/" + modelname + "_Y.npy")
                randomize = np.arange(len(self.train_X))
                np.random.shuffle(randomize)
                self.train_X = self.train_X[randomize]
                self.train_Y = self.train_Y[randomize]
            elif modelname == "validation":
                self.test_X = self.load(path="models/" + modelname + "_X.npy")
                self.test_Y = self.load(path="models/" + modelname + "_Y.npy")
        else:
            raise FileNotFoundError("models/" + modelname + " not found")

    def save(self, arr, path="model"):
        if not os.path.exists("models"):
            os.makedirs("models")
        with open("models/" + path + ".npy", 'wb') as f:
            np.save(f, arr)

    def load(self, path="model.npy"):
        with open(path, 'rb') as f:
            return np.load(f, allow_pickle=True)

    def save_models(self):
        self.save(self.parameters)

    def relu(self, Z):
        return np.maximum(0, Z)

    def sigmoid(self, Z):
        return 1 / (1 + np.exp(-Z))

    def softmax(self, Z):
        return np.exp(Z) / np.sum(np.exp(Z))

    def tanh(self, Z):
        return np.tanh(Z)

    def derivative_sigmoid(self, X):
        sigmo = self.sigmoid(X)
        return sigmo * (1 - sigmo)

    def derivative_relu(self, Z):
        return np.greater(Z, 0).astype(int)

    def init_parameter(self):
        self.parameters = {}
        len_of_layer = len(self.layer_dims)
        for i in range(1, len_of_layer):
            self.parameters['W' + str(i)] = np.random.randn(self.layer_dims[i], self.layer_dims[i - 1]) * 0.01
            self.parameters['b' + str(i)] = np.zeros((self.layer_dims[i], 1))

    def forward_activation(self, Z, activation):
        if activation == "sigmoid":
            return self.sigmoid(Z)
        elif activation == "relu":
            return self.relu(Z)
        elif activation == "softmax":
            return self.softmax(Z)
        elif activation == "tanh":
            return self.tanh(Z)
        else:
            raise ImportError("forward_activation function not found")

    def bacward_activation(self, Z):
        if self.layer_activation == "sigmoid":
            return self.derivative_sigmoid(Z)
        elif self.layer_activation == "relu":
            return self.derivative_relu(Z)
        elif self.layer_activation == "softmax":
            return self.softmax(Z)
        else:
            raise ImportError("bacward_activation function not found")

    def forward(self, X):
        layer_result = {}
        activation_result = {}
        # input vector -> A0 -> I need to save in activation_result (for loop)
        activation_result["A0"] = X
        len_of_layer = len(self.layer_dims)
        for i in range(1, len_of_layer - 1):
            layer_result["Z" + str(i)] = np.dot(self.parameters['W' + str(i)], activation_result['A' + str(i - 1)]) + self.parameters['b' + str(i)]
            activation_result["A" + str(i)] = self.forward_activation(layer_result["Z" + str(i)], self.layer_activation)

        # output -> softmax
        layer_result["Z" + str(len_of_layer - 1)] = np.dot(self.parameters['W' + str(len_of_layer - 1)],
                                                           activation_result["A" + str(len_of_layer - 2)]) + \
                                                    self.parameters['b' + str(len_of_layer - 1)]
        activation_result["A" + str(len_of_layer - 1)] = self.forward_activation(
            layer_result["Z" + str(len_of_layer - 1)], self.last_layer_act_func)

        return layer_result, activation_result

    def one_hot_encoding(self, Y):
        one_hot_Y = np.zeros((Y.size, self.layer_dims[-1]))
        one_hot_Y[np.arange(Y.size), Y] = 1
        one_hot_Y = one_hot_Y.T
        return one_hot_Y

    # sum of negative log-likelihood
    def compute_loss(self, train_Y, last_Activation):
        train_Y = self.one_hot_encoding(train_Y)
        L_sum = np.sum(np.multiply(train_Y, np.log(last_Activation)))
        m = train_Y.shape[1]
        L = -(1. / m) * L_sum

        return L

    def backward(self, layer_result, activation_result, Y):
        derivate_results = {}
        one_hot_Y = self.one_hot_encoding(Y)

        derivate_results["dZ" + str(len(self.layer_dims) - 1)] = activation_result[
                                                                     "A" + str(len(self.layer_dims) - 1)] - one_hot_Y
        derivate_results["dW" + str(len(self.layer_dims) - 1)] = (1 / self.batch_size) * np.dot(
            derivate_results["dZ" + str(len(self.layer_dims) - 1)],
            activation_result["A" + str(len(self.layer_dims) - 2)].T)
        derivate_results["db" + str(len(self.layer_dims) - 1)] = (1 / self.batch_size) * np.sum(
            derivate_results["dZ" + str(len(self.layer_dims) - 1)])

        for i in range(len(self.layer_dims) - 1, 1, -1):
            derivate_results["dZ" + str(i - 1)] = np.dot(self.parameters["W" + str(i)].T,
                                                         derivate_results["dZ" + str(i)]) * self.bacward_activation(
                layer_result["Z" + str(i - 1)])
            derivate_results["dW" + str(i - 1)] = (1 / self.batch_size) * np.dot(derivate_results["dZ" + str(i - 1)],
                                                                                 activation_result["A" + str(i - 2)].T)
            derivate_results["db" + str(i - 1)] = (1 / self.batch_size) * np.sum(derivate_results["dZ" + str(i - 1)])

        return derivate_results

    def update_parameters(self, derivative_results):
        for i in range(len(self.layer_dims) - 1):
            self.parameters["W" + str(i + 1)] = self.parameters["W" + str(i + 1)] - (
                        self.learning_rate * derivative_results["dW" + str(i + 1)])
            self.parameters["b" + str(i + 1)] = self.parameters["b" + str(i + 1)] - (
                        self.learning_rate * derivative_results["db" + str(i + 1)])

    def get_predictions(self, A2):
        return np.argmax(A2, 0)

    def get_accuracy(self, predictions, Y):
        return np.sum(predictions == Y) / Y.size

    def train(self):
        loss_list = []
        accuracy_list = []
        for i in tqdm(range(self.num_epoch)):
            # batch splitter
            total_loss = 0
            for j in range(self.train_X.shape[0] // self.batch_size):
                begin = j * self.batch_size
                end = begin + self.batch_size

                layer_result, activation_result = self.forward(self.train_X.T[:, begin:end])
                derivate_results = self.backward(layer_result, activation_result, self.train_Y[begin:end])

                self.update_parameters(derivate_results)

                # forward all data for loss

                total_loss += self.compute_loss(self.train_Y[begin:end], activation_result["A" + str(len(activation_result) - 1)])

            loss_list.append(total_loss)

            test_layer_result, test_activation_result = self.forward(self.test_X.T)
            print("\nIteration: ", i + 1, "Total Loss: ", total_loss)
            predictions = self.get_predictions(test_activation_result["A" + str(len(self.layer_dims) - 1)])
            acc = self.get_accuracy(predictions, self.test_Y)
            print("Accuracy:", acc)
            accuracy_list.append(acc)

        plt.plot(loss_list, "b")
        plt.xlabel("Epochs")
        plt.ylabel('Loss')
        plt.show()

        plt.plot(accuracy_list, "r")
        plt.xlabel("Epochs")
        plt.ylabel('Accuracy')
        plt.show()


# 900 must -> image size, 6 must -> classification size, add number between 900 and 6 -> like [900, 150, 6]
object1 = CNN(layer_dims=[900, 300, 125, 6], num_epoch=250, learning_rate=0.002, last_layer_act_func="sigmoid", batch_size=64)
# I already converted input images to numpy array
# object1.read_train(sys.argv[1])
# object1.read_train(sys.argv[2], modelname="validation")
object1.load_train("train")
object1.load_train("validation")
object1.init_parameter()
object1.train()
object1.save_models()
