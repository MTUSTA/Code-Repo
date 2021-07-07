from collections import Counter
import numpy as np
import matplotlib.pyplot as plt
import filters
import os
import datetime
import cv2
from math import sqrt

from tqdm import tqdm # to measure processing times


class KNN:

    def __init__(self, k=3, k_fold=3, method='NoMethod', dist='euclidean', weighted=False, photo_size=(32, 32)):
        self.k = k
        self.k_fold = k_fold
        self.method = method

        if dist == 'euclidean':
            self.dist = self.euclidean_distance
        elif dist == 'manhattan':
            self.dist = self.manhattan_distance
        else:
            raise Exception

        self.weighted = weighted
        self.photo_size = photo_size
        self.user_based_table = None

    # function created to save the properties of the object to the file
    def __str__(self):
        copy_method = self.method
        unified = str(self.k) + ' ' + str(self.k_fold) + ' ' + copy_method + ' ' + str(self.dist) + ' ' + str(
            self.weighted) + ' ' + str(self.photo_size)
        return unified

    # save numpy array
    def save_knn(self, filename='knn_weight.npy'):
        with open(filename, 'wb') as f:
            np.save(f, np.array(self.user_based_table))

    # load -> saved numpy array
    def load_knn(self, filename='knn_weight.npy'):
        with open(filename, 'rb') as f:
            self.user_based_table = np.load(f, allow_pickle=True)

    # calculate euclidean distance
    def euclidean_distance(self, a, b):
        result = np.float64(0)
        for i in range(len(a)):
            result += np.power((a[i] - b[i]), 2, dtype=np.float64)
        return sqrt(result)

    # calculate manhattan distance
    def manhattan_distance(self, a, b):
        result = np.float64(0)
        for i in range(len(a)):
            result += abs(a[i] - b[i])
        return result

    def user_based_table_creator(self):
        # checking directory
        if not os.path.exists('train') or not os.path.isdir('train'):
            raise Exception("train directory not found")
        # get directories in train directory
        list_of_subdirectory = os.listdir('train')
        dict_list_of_subdirectory = {}
        # save image data
        data = []

        for i in range(len(list_of_subdirectory)):
            # if file is directory
            if os.path.isdir('train/' + list_of_subdirectory[i]):

                dict_list_of_subdirectory[i] = list_of_subdirectory[i]
                # get images from directory
                list_of_image = os.listdir('train/' + list_of_subdirectory[i])

                for j in list_of_image:
                    # read image from disk
                    img = cv2.imread('train/' + list_of_subdirectory[i] + '/' + j).astype(np.uint8)
                    # resize image
                    resized_img = cv2.resize(img, self.photo_size)
                    # converting image
                    out = None
                    if self.method == 'NoMethod':
                        out = resized_img
                    elif self.method == 'Gabor':
                        out = filters.Gabor_process(resized_img)
                    elif self.method == 'Canny':
                        out = filters.Canny_edge(resized_img)
                    # image to 1D array
                    image_to_array = out.flatten()
                    # add image to data list
                    data.append([i, image_to_array])
        # save data to object variable
        self.user_based_table = np.array(data, dtype=object)

    def calc_distance(self, train_data, test_data):
        # fills similarity matrix
        result = []
        for i in range(len(test_data)):
            test_list = []
            for j in range(len(train_data)):
                # self.dist = functional reference
                calc = self.dist(test_data[i][1], train_data[j][1])
                # save result
                test_list.append(calc)
            result.append(test_list)
        return result

    # calculate 2 matrix distance
    def calc_acc(self, sim_matrix, sorted_sim_matrix, train_data, test_data):

        true_val = 0
        false_val = 0
        # weighted knn
        if self.weighted:
            for i in range(len(sorted_sim_matrix)):
                # empty neightbour list
                neighbour = {}
                # get k nearest neighbour from list
                get_knn_list = sorted_sim_matrix[i][:self.k]
                # add neighbour type in list -> covid = 0 , normal = 1 , viral = 2
                for knn_value in get_knn_list:
                    # distance to weight
                    weight = np.divide(1, sim_matrix[i][knn_value], dtype=np.float64)
                    # save variable to dictionary
                    if train_data[knn_value][0] in neighbour:
                        neighbour[train_data[knn_value][0]] += weight
                    else:
                        neighbour[train_data[knn_value][0]] = weight
                # get max value
                keymax = max(neighbour, key=neighbour.get)
                # if prediction true increase true value
                if keymax == test_data[i][0]:
                    true_val += 1
                # else increase false value
                else:
                    false_val += 1
        # normal knn
        else:
            for i in range(len(sorted_sim_matrix)):
                # empty neighbour list
                neighbour = []
                # get k nearest neighbour from list
                get_knn_list = sorted_sim_matrix[i][:self.k]
                # add neighbour type in list -> covid = 0 , normal = 1 , viral = 2
                for knn_value in get_knn_list:
                    neighbour.append(train_data[knn_value][0])
                # count types list
                count_neighbour = Counter(neighbour)
                # get most max value from neighbour list
                max_value = count_neighbour.most_common(1)
                # if prediction true increase true value
                if max_value[0][0] == test_data[i][0]:
                    true_val += 1
                # else increase false value
                else:
                    false_val += 1
        # return calculated accuracy
        return 100 * (true_val / (true_val + false_val))

    def knn_process(self, file_path, output):
        if self.user_based_table is None:
            raise Exception('No training')

        current_time = datetime.date.today().strftime("%d/%m/%Y") + '_' + datetime.datetime.now().strftime("%H:%M:%S")
        current_time = current_time.replace(':', '_')
        current_time = current_time.replace('/', '_')

        acc_file = open(file_path.split('.')[0] + '_' + current_time + '.txt', 'w')
        acc_file.write(self.__str__())
        acc_file.flush()
        accuracy_list = []
        # K-Fold Cross validation
        piece = len(self.user_based_table) // self.k_fold  # lenght of the every test piece
        for i in range(1, self.k_fold + 1):
            # split test data
            test_data = self.user_based_table[(i - 1) * piece:i * piece]
            # previously used test data
            piece_before_test_data = self.user_based_table[:(i - 1) * piece]
            # data -> after test piece
            piece_after_test_data = self.user_based_table[i * piece:]
            # combine piece_before_test_data and piece_after_test_data -> train data
            train_data = np.concatenate((piece_before_test_data, piece_after_test_data))
            # distance matrix
            sim_matrix = self.calc_distance(train_data, test_data)
            # output sorted array indices
            sorted_sim_matrix = np.argsort(sim_matrix, axis=1)

            # accuracy process start
            accuracy = self.calc_acc(sim_matrix, sorted_sim_matrix, train_data, test_data)
            accuracy_list.append(accuracy)
            print('Kfold =', i, '-> Accuracy:', accuracy, file=output)

        print('\n' + str(accuracy_list), file=acc_file)
        print('\nAverage of the accuracies:', sum(accuracy_list) / len(accuracy_list))
        acc_file.close()


def generate_user_based_tables():
    if not os.path.exists('user_based_table'):
        os.makedirs('user_based_table')

    method_list = ["NoMethod", "Gabor", "Canny"]
    photo_size_list = [(4, 4), (8, 8), (16, 16), (28, 28), (32, 32), (64, 64)]
    for i in method_list:
        for j in photo_size_list:
            # Timer starts
            a = datetime.datetime.now()

            Knn_object = KNN(k=3, k_fold=5, method=i, dist='euclidean', weighted=True, photo_size=j)

            Knn_object.user_based_table_creator()
            # Timer ends
            b = datetime.datetime.now()
            print("User Based Table " + i + str(j) + '.npy ' + "Created and Saved", b - a, " ms", (b - a).seconds,
                  " second")

            Knn_object.save_knn('user_based_table/' + i + str(j) + '.npy')

# generate_user_based_tables()

def knn_process_given_file(file_path, output):
    # change k value
    for k_value in tqdm(range(3, 11, 2)):
        # change k fold value
        for k_fold_value in range(3, 11):
            # weighted knn or normal knn
            for on_off in tqdm([False, True]):
                print("filename=", file_path, "k_value=", k_value, "k_fold_value=", k_fold_value, "weight on/off=", on_off, file=output)
                knn_object = KNN(k=k_value, k_fold=k_fold_value, dist='euclidean', weighted=on_off)
                knn_object.load_knn(file_path)
                # Timer starts
                a = datetime.datetime.now()
                # KNN process start
                knn_object.knn_process(file_path, output)
                # Timer ends
                b = datetime.datetime.now()
                print("Running time", b - a, " ms", (b - a).seconds, "second", file=output)
                output.flush()


if not os.path.exists("user_based_table") or not os.path.isdir("user_based_table"):
    raise Exception("train directory not found")
else:
    # get models
    processsed_files = os.listdir("user_based_table")
    # select file and send process
    for i in processsed_files:
        output = open('output_'+i+'.txt', 'w')
        knn_process_given_file('user_based_table/'+i, output)


"""Knn_object.load_knn("Gabor_8_8")"""

"""Knn_object.knn_process()
# Timer starts
c = datetime.datetime.now()

print("Prediction and Accuracy Finished", c - b, " ms", (c - b).seconds, " second")"""
