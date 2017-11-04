from pprint import pprint
def Ngram(string, num):
    ngram = [0 for i in range(len(string) - num + 1)] # 初期化
    for i in range(len(string) - num + 1):
        ngram[i] = string[i:(i + num)]
    return ngram
file = open('hightemp.txt', 'r')
string = file.readlines()
col = []
for line in string:
    col.append(set(Ngram(line.split("\t")[0], 1)))
for i in range(len(col) - 1):
    col[i + 1] = col[i] | col[i + 1]

print(len(col[len(col) - 1]))
