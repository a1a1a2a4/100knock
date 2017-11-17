import MeCab
from pprint import pprint

file = open('neko.txt.mecab', 'r')
text = file.read()

l = text.split('\n')
morpheme_dic = [{} for i in range(len(l))]
morpheme_li = [l[i].split(',') for i in range(len(l))]
for i in range(len(morpheme_dic)):
        if(morpheme_li[i][0]):
            morpheme_dic[i][(morpheme_li[i][1], morpheme_li[i][2], morpheme_li[i][3])] = morpheme_li[i][0]
pprint(morpheme_dic)
