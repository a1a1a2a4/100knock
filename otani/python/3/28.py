import gzip
import json
import re
import function
import pdb
from pprint import pprint

england = function.summarize20()
basic_infoOb = function.summarize21_27(england)
# remove to ^(*, #, :, ;), {{}}
deletions = {}
for key, value in basic_infoOb.items():
    deletions[key] = re.findall(r'(\{\{[^\{\}]+\}\})', value)
    if(deletions[key]):
        for k, deletion in enumerate(deletions[key]):
            deletions[key][k] = deletion.replace(deletion, deletion.replace('{{', '').replace('}}', ''))
            basic_infoOb[key] = basic_infoOb[key].replace(deletion, deletions[key][k])

pprint(basic_infoOb)
