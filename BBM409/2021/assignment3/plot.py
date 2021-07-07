import matplotlib.pyplot as plt
import numpy as np


def plot_parameters(weight, size1, size2):
    plt.figure()
    plt.imshow(weight.reshape(size1,size2)) 
    plt.show() 

#Usage
a = np.random.rand(900)
plot_parameters(a,30,30)