import re

print('Scratchcards')

scratch_cards = None
with open('Scratchcards.txt') as file:
    scratch_cards = file.readlines()

winning_numbers_list = []
own_numbers_list = []
for card in scratch_cards:
    winning_numbers_list.append(re.findall("(?<=: )(.*)(?= \|)", card)[0].split())
    own_numbers_list.append(re.findall("(?<=\| )(.*)(?=)", card)[0].split())

part1_result = 0
for list_index, winning_numbers in enumerate(winning_numbers_list):
    points = 0
    for winner in winning_numbers:
        if winner in own_numbers_list[list_index]:
            points = 1 if points == 0 else points * 2
    part1_result += points

print('part 1:', part1_result)

# Most efficient code ever:
copies_of_cards_list = [0] * len(scratch_cards)
for list_index, winning_numbers in enumerate(winning_numbers_list):
    for i in range(copies_of_cards_list[list_index] + 1):
        copies = 0
        for winner in winning_numbers:
            if winner in own_numbers_list[list_index]:
                copies += 1
        for j in range(copies):
            copies_of_cards_list[list_index + j + 1] += 1

part2_result = sum(copies_of_cards_list) + len(scratch_cards)
print('part 2:', part2_result)
