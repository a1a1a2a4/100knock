from pprint import pprint
file = open('hightemp.txt', 'r')
string = file.readlines()
temp = []
for line in string:
    temp.append(line.split("\t")[2])

temp.sort
temp.reverse
pprint(temp)
