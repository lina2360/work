#!/usr/bin/env python
# coding: utf-8

# In[1]:


import urllib.request
from bs4 import BeautifulSoup


# In[151]:


#quote_page = "https://www.cisco.com/c/en/us/products/collateral/routers/12000-series-routers/end_of_life_notice_c51-456801.html"
#quote_page = "https://www.cisco.com/c/en/us/products/collateral/routers/7600-series-routers/end_of_life_notice_c51-728933.html"
#quote_page = "https://www.cisco.com/c/en/us/products/collateral/routers/asr-1000-series-aggregation-services-routers/end_of_life_c51-678412.html"
#quote_page = "https://www.cisco.com/c/en/us/products/collateral/switches/catalyst-3560-x-series-switches/eos-eol-notice-c51-736139.html"
#quote_page = "https://www.cisco.com/c/en/us/products/collateral/switches/catalyst-2960-series-switches/eos-eol-notice-c51-733348.html"
#quote_page = "https://www.cisco.com/c/en/us/products/collateral/switches/nexus-9000-series-switches/eos-eol-notice-c51-741302.html"
quote_page = "https://www.cisco.com/c/en/us/products/collateral/wireless/5500-series-wireless-controllers/eos-eol-notice-c51-740221.html"



page = urllib.request.urlopen(quote_page)
soup = BeautifulSoup(page, "html.parser")
print("Cisco")
#name_box = soup.find("table",attrs={"class": "birth-cert-table"})
name_box = soup.find("div",attrs={"id":"eot-doc-wrapper"})
#attrs={"class": "birth-cert-status"}
#name = name_box.text.strip()
i=0  


#print(name_box)
for tr in name_box.tbody.findAll('tr'):
    j=1
    eol=False
    eos=False
   
    for td in tr.findAll('td'):
        
       
        for p in td.findAll('p'):

            text = p.text.strip()
        
            if("End-of-Life" in text):
                j += 1
                eol=True
                
            elif(eol==True and j==3):
                print("EOL:",text)    
            elif(eol==True):
                j+=1
                
            if("End-of-Sale" in text):
                j += 1
                eos=True              
            elif(eos==True and j==3):
                print("EOS:",text)    
            elif(eos==True):
                j+=1
           
                
                
                
           
            

