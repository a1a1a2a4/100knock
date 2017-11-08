import gzip
import json
import re
def summarize20():
    obj = []
    with gzip.open('jawiki-country.json.gz', 'rt', 'utf-8') as f:
        for i, line in enumerate(f):
            obj.append(json.loads(line))
            if(obj[i]['title'] == 'イギリス'):
                england  = obj[i]['text']
    return england
def summarize21_27(england):
    pattern = r'\{\{基礎情報 国\n(([^\{\}]|(\{\{[^\{\}]+\}\}))+)\}\}\n'
    repattern = re.compile(pattern)
    basic_info = repattern.findall(england)
    basic_info_line = basic_info[0][0].split('\n|')
    basic_infoOb = {}
    emphasisList = {}
    internalList = {}
    for line in basic_info_line:
        line = line.split(' = ')
        basic_infoOb[line[0]] = line[1]
        emphasisList[line[0]] = re.search(r'(\'{2,5})(.*?)(\1)', basic_infoOb[line[0]])
        if(emphasisList[line[0]]):
            basic_infoOb[line[0]] = basic_infoOb[line[0]].replace(emphasisList[line[0]].group(), emphasisList[line[0]].group().replace("'", ""))
        internalList[line[0]] = re.findall(r'(\[\[[^\[\]]+\]\])', basic_infoOb[line[0]])
        if(internalList[line[0]]):
            for internal in internalList[line[0]]:
                basic_infoOb[line[0]] = basic_infoOb[line[0]].replace(internal, internal.replace('[', '').replace(']', ''))
    return basic_infoOb
