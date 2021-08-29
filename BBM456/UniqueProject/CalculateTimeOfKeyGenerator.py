from Crypto.PublicKey import RSA
import time
import matplotlib.pyplot as plt

timer_list = []

size = [1024, 2048, 4096, 8192]
for i in size:

    start = time.time()
    key = RSA.generate(i)
    end = time.time()

    diff = end - start
    timer_list.append(diff)
    print(diff, "second")

plt.plot(size, timer_list, color='red', marker='o')
plt.title('Complexity', fontsize=14)
plt.xlabel('KeySize')
plt.ylabel('Seconds')
plt.show()
