import gzip
import json
import re
from pprint import pprint
obj = []
pattern = r'\[\[Category:.+\]\]'
repattern = re.compile(pattern)

with gzip.open('jawiki-country.json.gz', 'rt', 'utf-8') as f:
    for i, line in enumerate(f):
        obj.append(json.loads(line))
        if(obj[i]['title'] == 'イギリス'):
            england  = obj[i]['text']

matchList = repattern.findall(england)
if matchList:
    pprint(matchList)