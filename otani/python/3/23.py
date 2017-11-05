import gzip
import json
import re
from pprint import pprint
obj = []
pattern = r'\={2,}.*\={2,}'
repattern = re.compile(pattern)
with gzip.open('jawiki-country.json.gz', 'rt', 'utf-8') as f:
    for i, line in enumerate(f):
        obj.append(json.loads(line))
        if(obj[i]['title'] == 'イギリス'):
            england  = obj[i]['text']
# with open("england.txt", "w") as file:
#     file.write(england)
matchList = repattern.findall(england)

n= []
for i, section in enumerate(matchList):
    n.append(section.count('='))
    section = section.replace('=', '')
    print(str((n[i] // 2) - 1) + ' ' + section)
