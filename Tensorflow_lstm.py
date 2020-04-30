
# coding: utf-8

# <h1>Table of Contents<span class="tocSkip"></span></h1>
# <div class="toc" style="margin-top: 1em;"><ul class="toc-item"></ul></div>

# # RNN in TensorFlow - TimeSeries Data <a class="tocSkip">

# In[29]:


import math
import os

import numpy as np
np.random.seed(123)
print("NumPy:{}".format(np.__version__))

import pandas as pd
print("Pandas:{}".format(pd.__version__))

import sklearn as sk
from sklearn import preprocessing as skpp
print("sklearn:{}".format(sk.__version__))

import matplotlib as mpl
import matplotlib.pyplot as plt
mpl.rcParams.update({'font.size': 20,
                     'figure.figsize': [15,10] 
                    })
#plt.figure(figsize=(15,10))
print("Matplotlib:{}".format(mpl.__version__))

import tensorflow as tf
tf.set_random_seed(123)
print("TensorFlow:{}".format(tf.__version__))


# In[30]:


DATASETSLIB_HOME = '../datasetslib'
import sys
if not DATASETSLIB_HOME in sys.path:
    sys.path.append(DATASETSLIB_HOME)
get_ipython().run_line_magic('reload_ext', 'autoreload')
get_ipython().run_line_magic('autoreload', '2')
import datasetslib

from datasetslib import util as dsu
datasetslib.datasets_root = os.path.join(os.path.expanduser('~'),'datasets')


# # Read and pre-process the dataset

# In[31]:


filepath = os.path.join(datasetslib.datasets_root,
                        #'ts-data',
                        'TAUS_raw_data.csv'
                       ) 
dataframe = pd.read_csv(filepath,
                        usecols=[1],
                        header=0)
dataset = dataframe.values
dataset = dataset.astype(np.float32)


# In[32]:


plt.plot(dataset,label='Original Data')
plt.legend()
plt.xlabel('Timesteps')
plt.ylabel('Exchange')
plt.show()


# In[33]:


# normalize the dataset
scaler = skpp.MinMaxScaler(feature_range=(0, 1)) #標準化區間範圍0~1 最小最大值標準化
normalized_dataset = scaler.fit_transform(dataset) #對數據進行fit，然后標準化


# In[34]:


# split into train and test sets
train,test=dsu.train_test_split(normalized_dataset,train_size=0.67)#訓練和預測資料呈現的比例
# convert into supervised learning set of input data and label
n_x=1
n_y=1
X_train, Y_train, X_test, Y_test = dsu.mvts_to_xy(train,test,n_x=n_x,n_y=n_y)#訓練和預測的橫縱座標


# # TensorFlow LSTM for TimeSeries Data

# In[35]:


state_size = 1 #隱層
n_epochs = 400 #訓練次數
n_timesteps = n_x   # number of time steps loop?
n_x_vars = 1  # number of x variables
n_y_vars = 1  # number of y variables
learning_rate = 0.01 #訓練率

tf.reset_default_graph()#重置圖型
X_p = tf.placeholder(tf.float32, [None, n_timesteps, n_x_vars], name='X_p') #變數容器
Y_p = tf.placeholder(tf.float32, [None, n_timesteps, n_y_vars], name='Y_p')

# make a list of tensors of length n_x
rnn_inputs = tf.unstack(X_p,axis=1) #分解矩陣

cell = tf.nn.rnn_cell.LSTMCell(state_size)
rnn_outputs, final_state = tf.nn.static_rnn(cell, rnn_inputs,dtype=tf.float32)

W = tf.get_variable('W', [state_size, n_y_vars])
b = tf.get_variable('b', [n_y_vars], initializer=tf.constant_initializer(0.0))

predictions = [tf.matmul(rnn_output, W) + b for rnn_output in rnn_outputs]

y_as_list = tf.unstack(Y_p, num=n_timesteps, axis=1)

mse = tf.losses.mean_squared_error
losses = [mse(labels=label, predictions=prediction) for 
          prediction, label in zip(predictions, y_as_list)
         ]
total_loss = tf.reduce_mean(losses) #降维或者计算tensor（图像）的平均值
#optimizer = tf.train.AdagradOptimizer(learning_rate).minimize(total_loss)
#optimizer = tf.train.GradientDescentOptimizer(learning_rate).minimize(total_loss) #優化器
#optimizer = tf.train.AdadeltaOptimizer(learning_rate).minimize(total_loss)
#optimizer = tf.train.AdamOptimizer(learning_rate).minimize(total_loss)
#optimizer = tf.train.FtrlOptimizer(learning_rate).minimize(total_loss)
#optimizer = tf.train.RMSPropOptimizer(learning_rate).minimize(total_loss)

optimizer = tf.train.MomentumOptimizer(learning_rate, 0.9).minimize(total_loss)
#optimizer = tf.train.FtrlOptimizer(learning_rate).minimize(total_loss)

with tf.Session() as tfs:
    tfs.run(tf.global_variables_initializer())#所有變量初始化
    epoch_loss = 0.0
    for epoch in range(n_epochs):
        feed_dict={X_p: X_train.reshape(-1, n_timesteps, 
                                        n_x_vars), 
                   Y_p: Y_train.reshape(-1, n_timesteps, 
                                        n_x_vars)
                  }
        epoch_loss,y_train_pred,_=tfs.run([total_loss,
                                           predictions,
                                           optimizer], 
                                          feed_dict=feed_dict
                                         )
    print("train mse = {}".format(epoch_loss)) #印訓練的mse
    feed_dict={X_p: X_test.reshape(-1, n_timesteps, 
                                    n_x_vars), 
               Y_p: Y_test.reshape(-1, n_timesteps, 
                                    n_y_vars)
              }
    test_loss, y_test_pred = tfs.run([total_loss,predictions], 
                                feed_dict=feed_dict)
    
    print('test mse = {}'.format(test_loss)) #印預測的均方誤差
    print('test rmse = {}'.format(math.sqrt(test_loss))) #印預測的均方根誤差

y_train_pred=y_train_pred[0]
y_test_pred=y_test_pred[0]

#invert predictions
y_train_pred = scaler.inverse_transform(y_train_pred)
y_test_pred = scaler.inverse_transform(y_test_pred)

#invert originals
y_train_orig = scaler.inverse_transform(Y_train)
y_test_orig = scaler.inverse_transform(Y_test)

# shift train predictions for plotting 訓練資料繪圖設定
trainPredictPlot = np.empty_like(dataset)
trainPredictPlot[:, :] = np.nan
trainPredictPlot[n_x-1:len(y_train_pred)+n_x-1, :] = y_train_pred
# shift test predictions for plotting 預測資料繪圖設定
testPredictPlot = np.empty_like(dataset)
testPredictPlot[:, :] = np.nan
testPredictPlot[len(y_train_pred)+(n_x*2)-1:len(dataset)-1, :]=y_test_pred
# plot baseline and predictions 畫出基準和預測
plt.plot(dataset,label='Original Data')
plt.plot(trainPredictPlot,label='y_train_pred')
plt.plot(testPredictPlot,label='y_test_pred')
plt.legend()#圖標
plt.xlabel('Timesteps') #x軸名稱
plt.ylabel('Exchange') #y軸名稱
plt.show()

