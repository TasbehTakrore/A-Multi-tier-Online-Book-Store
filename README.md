# A-Multi-tier-Online-Book-Store Version 2
in this vesion  both order and catalog server were replicated, two replicas for each, so load balancing was added to frontend server, also caching capability was added to frontend server to improve performance.
## Architecture
the same architecture was used in this version with the addition of 2 full replicas for order and catalog servers, all end-users request arrives for frontend server, if frontend server needs to do requests to either order or catalog the load balancer will 
determine to which replica the request will be forwarded, the implemented load balancer is a simple one, three requests will be sent to a certain server then switching to the other replica of the same server and so on.


frontend server will cache **info** and **searche** requests data as in-memory cache in a hashmap, when read requests -not purchase- arrive to the frontend server it will first check the in-memory 
cache if any record exist for this request, if the request was satisfied from the cache the frontend will respond without connecting to catalog server, if it a cache miss the frontend
 will request data from catalog server, in addition to send response to the user the frontend will set the data in the cache to use it for future requests, since the cache could be full, LRU cache replacement 
  policy was used in the implementation.
  
  Using replication and caching cause a consistency problem, to guarantee consistency any update to some catalog replica will sent an invalidate to the frontend server 
  through a **delete**  REST API which will cause the frontend to delete the cahce record for the book which was updated, it will send **update** request to the other replica of the catalog.
  






## Usage     
   
   
  ### APIs
  the APIs in the first version are still suported in addition to the following.

  **FrontEnd APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/ACK/<itemNumber>`  | `delete`  | delete the cache record with this `itemNumber`.   |



## Deployment 

a docker image was created for each service and was deployed on 3 EC2 instance on AWS Cloud, the first instance was used to run frontend server docker container, the seond 
instance was used to run the two replicas of the catalog server docker container, and the last instance was used to run the two replicas of the ordedr server docker container, 
all the servers could be deployed on the same instance but we prefered the 3 instance deployment to reflect a more realistic scenario since we are going to conduct a performance test later on.

these docker images are pushed to docker hub, you can find them from [here](https://hub.docker.com/repository/docker/shahdlubbadeh/multi-tier-book-store-v2).  

### Installation and running
follow the next steps to download and run the images, make sure docker is downloaded in your system.

**1. Catalog-Server:**
  - Download the docker image for Catalog-Server, run this command on the CLI : <br>
        
        docker pull shahdlubbadeh/multi-tier-book-store-v2:cataloglatest2
        
        
  - Run the docker image **shahdlubbadeh/multi-tier-book-store-v2:cataloglatest2**, you need set some environment varaibles when running the docker image: 
  
  `FRONTEND_IP_ADDRESS` and `FRONTEND_PORT`  : ip address and port for the frontend server.
 
  `CATALOG_2_IP_ADDRESS` and `CATALOG_2_PORT`  : ip address and port for the other replica of the catalog server.
 
  `MY_PORT`: the port which this catalog server will bind to.

    
      
**2. Order-Server:** 
  - Download the docker image for Catalog-Server, run this command on the CLI : <br>
        
        docker pull shahdlubbadeh/multi-tier-book-store-v2:ordersecond
        
        
  - Run the docker image **shahdlubbadeh/multi-tier-book-store-v2:ordersecond**, you need set some environment varaibles when running the docker image: 
  
  `CATALOG_IP_ADDRESS_1` and `CATALOG_PORT_1`  : ip address and port for the first replica of the catalog server.
  
  `CATALOG_IP_ADDRESS_2` and `CATALOG_PORT_2`  : ip address and port for the second replica of the catalog server.
 
 
  
  **3. FrontEnd-Server:**
  - Download the docker image for Catalog-Server, run this command on the CLI : <br>
        
        docker pull shahdlubbadeh/multi-tier-book-store-v2:frontendsecond
        
        
  - Run the docker image **shahdlubbadeh/multi-tier-book-store-v2:ordersecond**, you need set some environment varaibles when running the docker image: 
  
  `CATALOG_IP_ADDRESS_1` and `CATALOG_PORT_1`  : ip address and port for the first replica of the catalog server.
  
  `CATALOG_IP_ADDRESS_2` and `CATALOG_PORT_2`  : ip address and port for the second replica of the catalog server.
  
  `ORDER_IP_ADDRESS_1` and `ORDER_PORT_1`  : ip address and port for the first replica of the order server.
  
  `ORDER_IP_ADDRESS_2` and `ORDER_PORT_2`  : ip address and port for the second replica of the order server.

  

## Testing
In this test serveral API calls to Frontend servers will take place to make sure caching and consistency are well working.

Frontend address: `http://34.221.232.21/:4000`

Order server #1 :  `http://35.86.78.52/:4001`

Order server #2 :  `http://35.86.78.52/:4002`

Catalog server #1 :  `http://54.218.77.12/:4001`

Catalog server #2 :  `http://54.218.77.12/:4002`

Client IP was: `82.205.87.177`


### 1. Rquest Info about a book for the first time ( cache miss ).
 **Request URL**
      
       http://34.221.232.21:4000/info/1
      
 <br>
       
 **Response**
 
      {
          "title": "How to get a good grade in DOS in 40 minutes a day",
          "quantity": 29,
           "price": 50
      }

 

      
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
| ![image](https://user-images.githubusercontent.com/104044414/207110587-785c09d9-fb8a-4e93-a6c6-299d7a3729d5.png) |![image](https://user-images.githubusercontent.com/104044414/207110802-7e9bb7d9-cbeb-4baf-9c2f-16c18bec7220.png) |

<br><br>

### 2. Rquest the same book again ( cache hit ).
 **Request URL**
      
       http://34.221.232.21:4000/info/1
      
 <br>
       
 **Response**
 
      {
          "title": "How to get a good grade in DOS in 40 minutes a day",
          "quantity": 29,
           "price": 50
      }


 

      
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
| ![image](https://user-images.githubusercontent.com/104044414/207112965-07580b31-bb94-4594-89fa-ca68f49dfd3a.png) |no requests arrive to any catalog replica |

<br><br>


### 3. purchase  a book: purchasing a book  will cause an update to its quantity, to guarantee database consistancy the catalog replica which recive the update from the order server will send update to the other replica, to guarantee cache consistancyit it will also send a delete request to the frontend so that the frontend will delete this cache record.
 **Request URL**
      
       http://34.221.232.21:4000/purchase/1
      
 <br>
       
 **Response**
 
      {
          "message": "success"
      }


 

      
| **Frontend server log message:**    | ** Order server #2 log message:**   | ** Catalog server #1 log message:**   |** Catalog server #2 log message:**    
| ------------- | ------------- | ------------- | ------------- | 
|  ![image](https://user-images.githubusercontent.com/104044414/207117372-1e8ce19d-c041-4ff9-a40d-071bb14870a6.png)  | ![image](https://user-images.githubusercontent.com/104044414/207115914-01462e0d-9c16-46ed-b6d8-a626eae36863.png) | ![image](https://user-images.githubusercontent.com/104044414/207116148-4c8d36a9-afd1-460a-86cb-5e8411628e1a.png)|   ![image](https://user-images.githubusercontent.com/104044414/207116338-e5b8e5fd-01fa-46a4-bf1a-359caf6c5bcb.png) |


<br><br>

### 4. Rquest Info about the same book after purchase request ( cache miss ).
 **Request URL**
      
       http://34.221.232.21:4000/info/1
      
 <br>
       
 **Response**
 
      {
          "title": "How to get a good grade in DOS in 40 minutes a day",
          "quantity": 28,
           "price": 50
      }

 

      
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
| ![image](https://user-images.githubusercontent.com/104044414/207136745-6cecf6f3-7604-4b6f-8c23-8d646e89ae20.png) |![image](https://user-images.githubusercontent.com/104044414/207136877-903a875d-4f4a-492a-80aa-65a4c541c4e7.png) |

<br><br>

### 5. Rquest Info books that don't exist in the cache to illustrate the load balancer behavior the first 3 requests will be forwarded to the first replica then the next 3 requests will be forwarded to the other replica, the same thing goes for both order and catalog replicas.
 **Requests URL**
      
       http://34.221.232.21:4000/info/2
       http://34.221.232.21:4000/info/3
       http://34.221.232.21:4000/info/4
       http://34.221.232.21:4000/info/5
       http://34.221.232.21:4000/info/6
       http://34.221.232.21:4000/info/7
        
        
      
 <br>
      

 

      
| **Frontend server log message:**    | **Catalog server #1 log message:**   | **Catalog server #2 log message:**   | 
| ------------- | ------------- | ------------- | 
| ![Untitled](https://user-images.githubusercontent.com/104044414/207139434-7b824658-73de-47f6-a70d-805cc6d50576.png) | ![image](https://user-images.githubusercontent.com/104044414/207139601-7b1b9938-fc1a-47d6-98ae-58dd5377e1d4.png) |   |
| ![Untitled](https://user-images.githubusercontent.com/104044414/207140052-721beb3e-805a-4d35-a5fb-92931a9979a2.png) | |  ![image](https://user-images.githubusercontent.com/104044414/207140176-00bfce4e-fa91-4897-9880-6c01f13d6d4c.png) |

<br><br>



## Performance Test

the response time of the system was measured in different cases ( with or without caching ...), Apache JMeter was used to run this performance test, frontend server with and without caching capabilites was deployed on the same instance, the catalog server which the frontend server without caching will communicate with and the 2 replicas which the frontend server with cache will communicate with was deployed on the same instance, and the same thing for the order servers, the results are listed bellow.

### Response Time with and without cache.
4 GET requests `/info/<itemNumber>` was used in this test, a total 8 samples was used to measure average response in each case.

**`Tabel(1): Response time with in-memory cache, average= 334 m`**
 
  ![with1](https://user-images.githubusercontent.com/104044414/207145306-7457fca7-1db9-48b4-ac89-61585bf13b36.PNG)


**`Tabel(2): Response time without  cache, average= 369 ms`**

  ![without 1](https://user-images.githubusercontent.com/104044414/207145343-d57962f5-04fb-4adf-a883-0176812dbc98.PNG)


For this simple application we can notice a decrease of average response time by 35 ms, which is about 9.5% performance improvment, for more complicated applications where a real database is used instead of a simple CSV File the performance improvment will be significantly higher. 

**Note: from the above data we can notice a higher response time for the first request either with or without cache, thats because we are using HTTP/TCP communication which involve a hand shaking before sending real data, that will cause overhead for connect time which we notice in the first request.**



### Response Time with a cache miss caused by a purchase.

in this experiment :
1. request  `/info/<itemNumber>` for a book that exists in the in-memory cache (cache hit).
2. purchase this book to cause invalidate to its cache entry.
3. request  `/info/<itemNumber>` for the same book, this time it doesn't exist in the in-memory cache (cache miss).


| **`Tabel(3): Response time for the above descibed eperiment`**  |
|:-------------:| 
| ![cache miss](https://user-images.githubusercontent.com/104044414/207149864-249e8625-c0e7-47ed-b26a-cdab95e57a4f.PNG) |




| ![Untitled1](https://user-images.githubusercontent.com/104044414/207152311-f398f518-e139-48c2-afec-fce0162c1163.png) |
|:-------------:| 
| **`Figure(1): Response time cache miss, cache hit and purchase`** |

From Figure(1) we can see that  cache hit requests have responce time smaller than cache miss requests, from Table(3) the average response time for cache hit requests is 228 ms, and 
the average response time for cache miss requests is 237 ms.




















