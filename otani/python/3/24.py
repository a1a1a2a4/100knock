import gzip
import json
import re
from pprint import pprint
obj = []
pattern = r'\[\[File:.+\]\]'
repattern = re.compile(pattern)
# repattern2 = re.compile(pattern2)
with gzip.open('jawiki-country.json.gz', 'rt', 'utf-8') as f:
    for i, line in enumerate(f):
        obj.append(json.loads(line))
        if(obj[i]['title'] == 'イギリス'):
            england  = obj[i]['text']
# with open("england.txt", "w") as file:
#     file.write(england)
matchList = repattern.findall(england)

pprint(matchList)
