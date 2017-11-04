import re
import random
from pprint import pprint

string = "I couldn't believe that I could actually understand what I was reading : the phenomenal power of the human mind ."
string = string.replace(",", "")
string = string.replace(".", "")
string = string.split(" ")

typoglycemia = []
for word in string:
    if(len(word) > 4):
        random_word = list(word[1:len(word) - 1])
        random.shuffle(random_word)
        random_word = ''.join(random_word)
        word = word[0:1] + random_word + word[-1]
    typoglycemia.append(word)
pprint(typoglycemia)
