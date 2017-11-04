from pprint import pprint
import sys
# argvs = sys.argv
# argc = len(argvs)
# argvs[1]
stdin = sys.stdin.readlines()
stdin[0] = int(stdin[0].replace('\n', ''))
for i in range(stdin[0]):
    stdin[i + 1] = stdin[i + 1].replace('\n', '')
    print(stdin[i + 1])
