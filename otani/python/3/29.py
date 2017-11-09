import gzip
import json
import re
import urllib.parse, urllib.request
import function
import pdb
from pprint import pprint

england = function.summarize20()
basic_infoOb = function.summarize21_27(england)
basic_infoOb = function.summarize28(basic_infoOb)

national_flag = basic_infoOb['国旗画像']

url = 'https://www.mediawiki.org/w/api.php?' \
    + 'action=query' \
    + '&titles=File:' + urllib.parse.quote(national_flag) \
    + '&format=json' \
    + '&prop=imageinfo' \
    + '&iiprop=url'
connection = urllib.request.urlopen(url)
data = json.loads(connection.read().decode())
print(data['query']['pages']['-1']['imageinfo'][0]['url'])
