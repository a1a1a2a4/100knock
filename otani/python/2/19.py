from pprint import pprint
def Ngram(string, num):
    ngram = [0 for i in range(len(string) - num + 1)] # 初期化
    for i in range(len(string) - num + 1):
        ngram[i] = string[i:(i + num)]
    return ngram

file = open('hightemp.txt', 'r')
string = file.readlines()
prefecture = []
pre = []
for i, line in enumerate(string):
    pre.append(line.split("\t")[0])
    prefecture.append(set(Ngram(pre[i], 1)))
pre_str = ""
for i in range(len(prefecture) - 1):
    pre_str = pre_str + pre[i]
    prefecture[i + 1] = prefecture[i] | prefecture[i + 1]
pre_str = pre_str + pre[-1]

frequency = {}
for unit in list(prefecture[-1]):
    frequency[unit] = pre_str.count(unit)

pprint(frequency)
