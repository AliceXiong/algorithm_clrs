
# coding: utf-8

# In[4]:

import random
import logging
import program_03_corrected as pc
import program_03_flawed as pf


# In[6]:

if __name__ == "__main__":
    logging.basicConfig(filename = 'jobPlan.log', level = logging.DEBUG)
    for i in range(7):
        logging.info("******Simulate example below:*******")
    #randomly sample low stress job and high stress job values for 10 weeks
        weeks = 10
        L = random.sample(range(1,100),weeks)
        h = random.sample(range(10, 150), weeks)
        logging.info("L = "+str(L))
        logging.info("h = "+str(h))
        
        #correct algorithm
        a, b = pc.job_plan(L,h)
        logging.info("maximum revenue received from the corrected algorithm: %d",a)
        out = pc.print_job_plan(b)
        logging.info("with path: "+str(out))
        logging.info("                                                     ")
        
        #flawed algorithm
        a, b = pf.job_plan_flawed(L,h)
        logging.info("maximum revenue received from the flawed algorithm: %d",a)
        out = pf.print_job_plan_flawed(b)
        logging.info("with path: "+str(out))  
        logging.info("                                                     ")


# In[10]:




# In[ ]:



