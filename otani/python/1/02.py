import sys
string1 = "パトカー"
string2 = "タクシー"
for n in range(1, 5):
    sys.stdout.write(string1[n - 1:n] + string2[n - 1:n])
