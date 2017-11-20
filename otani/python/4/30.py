import MeCab
from pprint import pprint

file = open('neko.txt.mecab', 'r')
text = file.read()

l = text.split('\n')
morpheme_li = [l[i].split(',') for i in range(len(l))]
c = 0
for i in range(len(l)):
    c = c + morpheme_li[i][0].count('。')
morpheme_dic = [[] for i in range(c)]
i = 0
j = 0
k = 0
while(i < len(l)):
    if(morpheme_li[i][0]):
        morpheme_dic[k].append([])
        morpheme_dic[k][j].append({})
        morpheme_dic[k][j] = {'surface' : morpheme_li[i][0], 'base' : morpheme_li[i][1], 'pos' : morpheme_li[i][2], 'pos1' : morpheme_li[i][3]}
        j = j + 1
        if(morpheme_li[i][0] == '。'):
            k = k + 1
            j = 0
    i = i + 1
pprint(morpheme_dic)
