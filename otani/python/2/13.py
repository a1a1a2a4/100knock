from pprint import pprint

col1 = open('col1.txt', 'r')
col1 = col1.readlines()
col2 = open('col2.txt', 'r')
col2 = col2.readlines()

col1_col2 = open('col1_col2.txt', 'w')
for i in range(len(col1)):
    col1[i] = col1[i].replace('\n', '')
    col2[i] = col2[i].replace('\n', '')
    col1[i] = col1[i] + '\t' + col2[i]
    col1_col2.write(col1[i] + "\n")
col1_col2.close()
