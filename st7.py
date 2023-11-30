import random

weights = [[0, 20, 30, 10, 11], [15, 0, 16, 4, 2], [
    3, 5, 0, 2, 4], [19, 6, 18, 0, 3], [16, 4, 7, 16, 0]]
num_cities = len(weights)
mut_chance = 0.1
crossover_chance = .8
gen_size = 10
generation = 0
num_generation = 5
gnome_list = []
gnome_fitness = []

def fitness(path):
    i = 0
    cost = 0
    for i in range(num_cities):
        cost += int(weights[int(path[i-1]) % num_cities]
                    [int(path[i]) % num_cities])
    return cost


def mutation(path):
    if (random.random() > mut_chance):
        return path
    i = random.randrange(0, num_cities)
    j = random.randrange(0, num_cities)
    t = path[i]
    path[i] = path[j]
    path[j] = t
    return path


def crossover(parent1, parent2):
    if (random.random() > mut_chance):
        return parent1, parent2

    # Choose two random indices for the crossover
    start_index = random.randint(0, len(parent1) - 1)
    end_index = random.randint(start_index + 1, len(parent1))

    # Create empty offspring with the same length as the parents
    offspring1 = [-1] * len(parent1)
    offspring2 = [-1] * len(parent1)

    # Copy the selected subset from parent1 to offspring1 and from parent2 to offspring2
    offspring1[start_index:end_index] = parent1[start_index:end_index]
    offspring2[start_index:end_index] = parent2[start_index:end_index]

    # Fill in the remaining positions with cities from parent2 for offspring1 and from parent1 for offspring2, preserving the order
    remaining_indices1 = [i for i in range(
        len(parent2)) if parent2[i] not in offspring1]
    remaining_indices2 = [i for i in range(
        len(parent1)) if parent1[i] not in offspring2]

    remaining_indices1 = remaining_indices1[end_index:] + \
        remaining_indices1[:end_index]
    remaining_indices2 = remaining_indices2[end_index:] + \
        remaining_indices2[:end_index]

    for i in range(len(offspring1)):
        if offspring1[i] == -1:
            offspring1[i] = parent2[remaining_indices1.pop(0)]

    for i in range(len(offspring2)):
        if offspring2[i] == -1:
            offspring2[i] = parent1[remaining_indices2.pop(0)]

    return offspring1, offspring2


for i in range(gen_size):
    random_list = []
    random_list = random.sample(range(1, num_cities+1), num_cities)
    # print(random_list)
    gnome_list.append(random_list)

for gnome in gnome_list:
    gnome_fitness.append(fitness(gnome))
print(gnome_fitness)

for i in range(1, num_generation):
    elite = gnome_fitness.index(max(gnome_fitness))
    crossover_indice = random.sample(range(gen_size), 2)
    gnome_list[crossover_indice[0]], gnome_list[crossover_indice[1]] = crossover(
        gnome_list[crossover_indice[0]], gnome_list[crossover_indice[1]])

    for j in range(gen_size):
        if j == elite:
            continue

        temp = mutation(gnome)
        if (fitness(temp) < gnome_fitness[j]):
            gnome_list[j] = temp
    gnome_fitness.clear()
    for gnome in gnome_list:
        gnome_fitness.append(fitness(gnome))

print(gnome_fitness)
print(min(gnome_fitness))
print(gnome_list[gnome_fitness.index(min(gnome_fitness))])
