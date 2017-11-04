from pprint import pprint

def Ngram(string, num):
    ngram = [0 for i in range(len(string) - num + 1)] # 初期化
    for i in range(len(string) - num + 1):
        ngram[i] = string[i:(i + num)]
    return ngram

string = "ketsugenokamisama"
ngram = Ngram(string, 3)
pprint(ngram)
