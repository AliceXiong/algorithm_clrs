
# coding: utf-8

# In[ ]:

#simulate 

#logging.info("In the %dth step, I update the received revenue to %d", count, c[i])


# In[5]:

import logging
import random
import numpy as np

#L = [10, 1, 10, 10]
#h = [5, 50, 5, 1]


# In[127]:

#L1 = [10, 10, 10, 10]
#h1 = [5, 50, 100, 10]


# In[6]:

# L denotes revenue Li returned by low stress job in week i
# h denotes revenue hi returned by high stress job in week i

def job_plan(L,h):
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
        
        
    for j in range(2,n):
        if c[j-1] + L[j] >= c[j-2]+h[j]:
            c[j] = c[j-1] + L[j]
            b[j] = "L"
            
        else:
            c[j] = c[j-2] + h[j]
            b[j] = "h"
            b[j-1] = None
            b[j-2] = "L"
        count += 1
        logging.info("In week %dth, the maximum value returned is %d", count, c[j])
            
    return c[n-1], b


# In[3]:

#L = [10, 1, 10, 10, 10, 10, 10, 40, 10]
#h = [5, 50, 5, 1, 50, 100, 50, 50, 100]


# In[7]:

#a, b = job_plan(L,h)


# In[9]:




# In[9]:




# In[157]:

def print_job_plan(b):
    n = len(b)
    out = []
    for i in range(n):
        if b[i] != None:
            out.append(b[i]+str(i+1))
        else:
            out.append("None")
    return ' --> '.join(out)


# In[157]:




# In[157]:




# In[ ]:



