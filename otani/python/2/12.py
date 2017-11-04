from pprint import pprint

file = open('hightemp.txt', 'r')
string = file.readlines()
col1 = open('col1.txt', 'w')
col2 = open('col2.txt', 'w')
for line in string:
    col1.write(line.split("\t")[0] + "\n")
    col2.write(line.split("\t")[1] + "\n")
col1.close()
col2.close()
