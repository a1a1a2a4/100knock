from pprint import pprint
import sys
# argvs = sys.argv
# argc = len(argvs)
# argvs[1]
stdin = sys.stdin.readlines()
stdin[0] = int(stdin[0].replace('\n', ''))
stdin.reverse()
for i in range(stdin[len(stdin) - 1]):
    stdin[i] = stdin[i].replace('\n', '')
    print(stdin[i])
