from pprint import pprint

def Ngram(string, num):
    ngram = [0 for i in range(len(string) - num + 1)] # 初期化
    for i in range(len(string) - num + 1):
        ngram[i] = string[i:(i + num)]
    return ngram

string1 = "paraparaparadise"
string2 = "paragraph"
x =  set(Ngram(string1, 2))
y =  set(Ngram(string2, 2))

union = x | y
intersection = x & y
difference = x - y

print("se" in union)
