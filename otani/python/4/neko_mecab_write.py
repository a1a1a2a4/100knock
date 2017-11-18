import MeCab
from pprint import pprint

mecab = MeCab.Tagger()

file = open('neko.txt', 'r')
text = file.read()
mecab.parse('')
node = mecab.parseToNode(text)
s = ''
while node:
    surface = node.surface
    base = node.feature.split(",")[0]
    pos = node.feature.split(",")[1]
    pos1 = node.feature.split(",")[2]
    # print('{0} , {1}, {2}, {3}'.format(surface, base, pos, pos1))
    s = s + surface + ', ' + base + ', ' + pos + ', ' + pos1 + '\n'
    node = node.next
neko_txt = open('neko.txt.mecab', 'w')
neko_txt.write(s)
neko_txt.close()
