import gzip
import json
import re
from pprint import pprint
obj = []
pattern = r'\{\{基礎情報 国\n(([^\{\}]|(\{\{[^\{\}]+\}\}))+)\}\}\n'
repattern = re.compile(pattern)
with gzip.open('jawiki-country.json.gz', 'rt', 'utf-8') as f:
    for i, line in enumerate(f):
        obj.append(json.loads(line))
        if(obj[i]['title'] == 'イギリス'):
            england  = obj[i]['text']
basic_info = repattern.findall(england)
basic_info_line = basic_info[0][0].split('\n|')
basic_infoOb = {}
for line in basic_info_line:
    line = line.split(' = ')
    line[1] = line[1].replace("'''''", "")
    line[1] = line[1].replace("'''", "")
    line[1] = line[1].replace("''", "")
    basic_infoOb[line[0]] = line[1]
    # print(line[1])

pprint(basic_infoOb)
