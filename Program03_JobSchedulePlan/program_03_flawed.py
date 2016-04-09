
# coding: utf-8

# In[33]:

import logging
import numpy as np
#L1 = [10, 10, 10]
#h1 = [5, 50, 100]


# In[35]:

def job_plan_flawed(L,h):
    count = 0
    
    n = len(h)
    #empty array c store the received revenue, numeric
    c = np.zeros(n) 
    #empthy array b store the "L" and "h", string; help us construct an optimal job plans later
    b = map(str, np.zeros(n)) 
    
    if L[0] >= h[0]: 
        c[0] = L[0]; b[0] = "L"
    else: 
        c[0] = h[0]; b[0] = "h"
    count += 1
    logging.info("In week %dth, the maximum value returned is %d", count, c[0])
    
    
    if h[1] > L[0] + L[1]:
        c[1] = h[1]; b[1] = "h"; b[0] = None
    else:
        c[1] = L[0] + L[1]; b[1] = "L"; b[0] = "L"
    count += 1
    logging.info("In week %dth, the maximum value returned is %d", count, c[1])    
            
    
    j = 2
    while j<n:
        if j+1 == n:
            c[j]=c[j-1]+L[j]
            b[j] = "L"
            j+=1
            #count += 1
            #logging.info("In week %dth, the maximum value returned is %d", count, c[j])
            continue #rejects all the remaining statements
            
        if h[j+1] > L[j] + L[j+1]:
            c[j+1] = c[j-1] + h[j+1]
            b[j+1] = "h"
            b[j] = None
            
            count += 2
            logging.info("In week %dth, the maximum value returned is %d", count, c[j+1])
            j = j + 2
            
        else:
            c[j] = c[j-1] + L[j]
            b[j] = "L"
            count += 1
            logging.info("In week %dth, the maximum value returned is %d", count, c[j])
            j = j + 1
            
    return c[n-1], b


# In[31]:




# In[31]:

def print_job_plan_flawed(b):
    n = len(b)
    out = []
    for i in range(n):
        if b[i] != None:
            out.append(b[i]+str(i+1))
        else:
            out.append("None")
    return ' --> '.join(out)


# In[ ]:



