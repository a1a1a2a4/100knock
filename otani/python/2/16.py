from pprint import pprint
import sys
import codecs

def line_counts(max_count, n):
    quo = max_count // n
    rem = max_count % n
    return [quo+1] * rem + [quo] * (n - rem)

n = int(sys.argv[1])
max_count = sum(1 for line in codecs.open("hightemp.txt", "r", "utf-8"))

with codecs.open("hightemp.txt", "r", "utf-8") as rf:
    for i, line_count in enumerate(line_counts(max_count, n)):
        with codecs.open("split.{0}.txt".format(i), "w", "utf-8") as wf:
            for _ in range(line_count):
                wf.write(rf.readline())
