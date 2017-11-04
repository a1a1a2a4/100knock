from pprint import pprint
string = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
# 1.単語に分解
string = string.replace(",", "")
string = string.replace(".", "")
string = string.split(" ")
# 2. if(1, 5, 6, 7, 8, 9, 15, 16, 19番目の単語)
union = {1, 5, 6, 7, 8, 9, 15, 16, 19}
for i in range(len(string)):
    n_union = {i + 1}
    if(n_union.issubset(union)):
        string[i] = string[i][0:1]
    else:
        string[i] = string[i][0:2]
pprint(string)
