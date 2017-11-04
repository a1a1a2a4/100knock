string = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
# 1.単語に分解
string = string.replace(",", "")
string = string.replace(".", "")
string = string.split(" ")
print(string)
# 2. 文字数にして出力
for string_len in string:
    print(len(string_len))
