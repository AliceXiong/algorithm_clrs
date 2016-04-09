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

def medianThree(A, i1, i2, i3):
    global count
    findMedian = [i1, i2, i3]
    for i in range(0,2):
        for j in range(i+1,3):
            if A[findMedian[i]]>A[findMedian[j]]:
                count +=1
                temp = findMedian[i]
                findMedian[i] = findMedian[j]
                findMedian[j] = temp
    logging.info("Used %d as pivot among [%d, %d, %d]" % (A[findMedian[1]], A[0], A[1], A[2]))
    #sorted and pick up median
    return findMedian[1]
    

# median of three partitioning
def mPARTITION(A,p,r):
    #return back the pivot index
    i = medianThree(A,p,r,(p+r)/2)
    temp = A[r]
    A[r] = A[i]
    A[i] = temp
    return PARTITION(A,p,r)

# randomized quicksort 
def mQUICKSORT(A,p,r):
    if p < r:
        q = mPARTITION(A, p, r)
        logging.info("randomized parition on %d" % q)
        mQUICKSORT(A, p, q-1)
        mQUICKSORT(A, q+1,r)

if __name__=="__main__":
    logging.basicConfig(filename='mQuickSort.log',level=logging.DEBUG)
    #used mQS.csv to record data size vs. comparison times
    with open('mQS.csv', 'wb') as f:
        writer = csv.writer(f)
        for num in [10, 100]:
            logging.info("Input array with %d elements selected from [1,10000)" % num)
            Input = random.sample(range(0,200000),num)
            logging.info("Input is "+str(Input))
            mQUICKSORT(Input,0,len(Input)-1)
            writer.writerows([[str(num), str(count)]])
            logging.info( "By sorting %d elements, it went through %d comparisions" % (num, count))
