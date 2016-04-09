import logging
import random
import csv

count = 0
# partion based on the last element A[r]
def PARTITION(A,p,r):
    global count
    x = A[r]
    i = p - 1
    for j in range(p,r):
        if A[j] <= x: 
            count+=1
            i = i + 1
            temp = A[i]; A[i] = A[j]; A[j] = temp;
        
    temp = A[r]; A[r] = A[i+1]; A[i+1] = temp;
    logging.info("call partition %d" % (i+1))
    return i+1

# randomly select out the pivot A[i] and do the partition
def RANDOMIZED_PARTITION(A,p,r):
    i = random.randint(p,r)
    temp = A[r]
    A[r] = A[i]
    A[i] = temp
    return PARTITION(A,p,r)

# randomized quicksort 
def RANDOMIZED_QUICKSORT(A,p,r):
    if p < r:
        q = RANDOMIZED_PARTITION(A, p, r)
        logging.info("randomized parition on %d" % q)
        RANDOMIZED_QUICKSORT(A, p, q-1)
        RANDOMIZED_QUICKSORT(A, q+1,r)

if __name__=="__main__":
    logging.basicConfig(filename='regularQS.log',level=logging.DEBUG)
    #used regularQS.csv to record data size vs. comparison times
    with open('regularQS.csv', 'wb') as f:
        writer = csv.writer(f)
        for num in [10, 100]:
            logging.info("Input array with %d elements selected from [1,10000)" % num)
            Input = random.sample(range(0,200000),num)
            logging.info("Input is "+str(Input))
            RANDOMIZED_QUICKSORT(Input,0,len(Input)-1)
            writer.writerows([[str(num), str(count)]])
            logging.info( "By sorting %d elements, it went through %d comparisions" % (num, count))
