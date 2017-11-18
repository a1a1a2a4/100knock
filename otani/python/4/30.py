import MeCab
from pprint import pprint

file = open('neko.txt.mecab', 'r')
text = file.read()

l = text.split('\n')
morpheme_dic = [{} for i in range(len(l))]
morpheme_li = [l[i].split(',') for i in range(len(l))]
for i in range(len(morpheme_dic)):
    if(morpheme_li[i][0]):
        morpheme_dic[i] = {'surface' : morpheme_li[i][0], 'base' : morpheme_li[i][1], 'pos' : morpheme_li[i][2], 'pos1' : morpheme_li[i][3]}
# pprint(morpheme_dic)
