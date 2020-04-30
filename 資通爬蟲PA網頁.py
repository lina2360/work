#!/usr/bin/env python
# coding: utf-8

# In[1]:


import urllib.request
from bs4 import BeautifulSoup


# In[28]:


quote_page = "https://www.paloaltonetworks.com/services/support/end-of-life-announcements/hardware-end-of-life-dates"

page = urllib.request.urlopen(quote_page)
soup = BeautifulSoup(page, "html.parser")
#print("PA")
name_box = soup.find("table",attrs={"class":"table table-striped table-hover"})
#name = name_box.text.strip()
for tr in name_box.tbody.findAll('tr'):
    p200=1
    p4000=1
    eol=False
    eos=False
    
    for td in tr.findAll('td'):
       
        text = td.text
        if(text == "PA-200" and p200==1):
            print("title:",text) 
            p200+=1
        elif(p200==2):
            print("EOS:",text)
            p200+=1
        elif(p200==3): 
            print("EOL:",text)
            p200+=1
            
        if("PA-4000 Series" in text and p4000==1):
            print("title:",text) 
            p4000+=1
        elif(p4000==2):
            print("EOS:",text)
            p4000+=1
        elif(p4000==3): 
            print("EOL:",text)
            p4000+=1    
       
     


# In[ ]:




