#!/usr/bin/env python
# coding: utf-8

# In[37]:


import urllib.request
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options


# In[38]:


quote_page = "https://support.juniper.net/support/eol/hardware/c_series/"
#quote_page ="https://support.juniper.net/support/eol/hardware/j_series/"
#quote_page ="https://support.juniper.net/support/eol/hardware/netscreen_hw/"
#因為這個網頁是js，所以要用程式開瀏覽器再抓資料，可能要下載或安裝一些包
webdriver_path = 'C:\\chromedriver_win32\\chromedriver.exe'
options = Options()
driver = webdriver.Chrome(executable_path=webdriver_path, options=options)

driver.get(quote_page)
content_element =WebDriverWait(driver, 5).until(
    expected_conditions.presence_of_element_located((By.CLASS_NAME, 'wrapper'))
)

soup = BeautifulSoup(driver.page_source, "html.parser")
name_box = soup.find("tbody")

for tr in name_box.findAll('tr'):
    ja3000=1
    j6300=1
    ns=1
    for td in tr.findAll('td'):
       
        text = td.text
        
        if("JA-C3000" in text and ja3000==1):
            print("title:",text) 
            ja3000+=1
        elif(ja3000==2):
            print("EOL:",text)
            ja3000+=1
        elif(ja3000==7): 
            print("EOS:",text)
            ja3000+=1
        elif(ja3000==3 or ja3000==4 or ja3000==5 or ja3000==6):
            ja3000+=1
        
        if("J6300" in text and j6300==1):
            print("title:",text) 
            j6300+=1
        elif(j6300==2):
            print("EOL:",text)
            j6300+=1
        elif(j6300==7): 
            print("EOS:",text)
            j6300+=1
        elif(j6300==3 or j6300==4 or j6300==5 or j6300==6):
            j6300+=1
            
        if("NS-DI-SSG20" in text and ns==1):
            print("title:",text) 
            ns+=1
        elif(ns==2):
            print("EOL:",text)
            ns+=1
        elif(ns==7): 
            print("EOS:",text)
            ns+=1
        elif(ns==3 or ns==4 or ns==5 or ns==6):
            ns+=1    
           
    
driver.close()       


# In[ ]:





# In[ ]:




