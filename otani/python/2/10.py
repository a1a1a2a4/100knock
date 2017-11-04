from pprint import pprint

file = open('hightemp.txt', 'r')
string = file.readlines()
# pprint(string)
print(len(string))
