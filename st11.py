import random
import threading
import time


num_philosophers = 100
num_forks = num_philosophers
max_meals = 1
meal_counter = [0] * num_philosophers

forks = [threading.Semaphore(1) for _ in range(num_forks)]
mutex = threading.Semaphore(1)

philosopher_threads = []

def eating_philosopher(i):
    while meal_counter[i] != max_meals:
        print(f"Philosopher {i} is thinking...")
        mutex.acquire()
        left_fork = i
        right_fork = (i+1) % num_forks
        mutex.release()

        forks[left_fork].acquire()
        print(f"Philosopher {i} has picked up left fork")

        forks[right_fork].acquire()
        print(f"Philosopher {i} has picked up right fork")

        print(f"Philosopher {i} is eating...")
        time.sleep(random.randint(1, 5))

        forks[left_fork].release()
        forks[right_fork].release()
        print(f"Philosopher {i} has put down the forks")
        meal_counter[i]+=1


for i in range(num_philosophers):
    philosopher_threads.append(threading.Thread(target = eating_philosopher, args=(i,)))

for thread in philosopher_threads:
    thread.start()

for thread in philosopher_threads:
    thread.join()