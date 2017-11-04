import re
from pprint import pprint

def cipher(string):
    res = re.sub(r'[a-z]', chr(219), string)
    return res

string = "KeTsuGeNoKaMiSaMa"
string = cipher(string)

pprint(string)
