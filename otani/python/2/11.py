from pprint import pprint

file = open('hightemp.txt', 'r')
str_list = file.readlines()
text = []
for string in str_list:
    string = string.replace('\t', ' ')
    text.append(string)
pprint(text)
