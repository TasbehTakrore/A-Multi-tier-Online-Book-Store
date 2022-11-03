# A-Multi-tier-Online-Book-Store
this project represents a book store in which **end-users** can search for a book in specified topic, ask for info about a certain book by the book id or purchase a book. In addition, **admin** can see all orders and change number of books.


## Architecture
this store consists of 4 RESTFull microservices each one of them can run in a different machine:

**1-Catalog-Server:** this service can access a database that store information about available books, and it provide services to query a book information by topic or by book item number, also a service to update book price or quantity.

**2-Order-Server:** this service responisable for purchase processes, it communicate with Catalog-Server to ensure that the requested book is available, and then store the order in a database and decrement number of books by a request to catalog-server.

**3-FrontEnd-Server:** this sevice is exposed to end-users who can use it to purcahse books, search for books, and get information abouta certain book.

**4-Admin-Server:** using services applied by this service an admin can change the number of books in store by a request to catalog-server, and get all orders by a request to order-server.


## Implementation
this project was implemented using **java**, **maven**  and **spark framework** which supports implementing microservices, in pom.xml for each project you can find all the needed dependencies, spark dependency and Gson dependency for JSON formatting are in common for all servises, in catalog-server and order-server a dependency for opencsv which is a CSV parser library was added, since a CSV file was used as a database for those two sevices.

//other things should be added about how each service works.



## Usage

### Installation and running

First, make sure that **jdk 17** or heigher is installed and **$JAVA_HOME** environment variable is set and pointing to your jdk download folder, next a detailed steps to run each service from an **Executable jar file** that contains everything needed to run each service.

**1. Catalog-Server:**
  - Download the executable jar file for Catalog-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/catalog-server-executable.jar). <br>
     
  - Download catalog database csv file from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/catalogDatabase.CSV). <br>
     
  - Put the files that were downloaded in the previous steps in the same folder. <br>
     
  - Open a tereminal on the folder where the files in and run the following: <br>
     
          java -jar catalog-server-executable.jar     
  - Catalog-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
    
    
      
**2. Order-Server:** 
  - Download the executable jar file for order-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/order-server-executable.jar). <br>
     
  - Download order database csv file from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/orderDatabase.csv). <br>
     
  - Put the files that were downloaded in the previous steps in the same folder. <br>
  
  - Order-Server use services from Catalog-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where those files are in and run the following: <br>
     
          java -jar order-server-executable.jar     
  - Order-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
  
  
  **3. FrontEnd-Server:**
  - Download the executable jar file for frontend-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/frontend-server-executable.jar). <br>
     
  - FrontEnd-Server use services from Catalog-Server and Order-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, and an environment variable for the order-server ip address called **'ORDER_IP_ADDRESS'** more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where the file is in and run the following: <br>
     
          java -jar frontend-server-executable.jar   
  - FrontEnd-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
     
   
   

**4. Admin-Server:**
  - Download the executable jar file for order-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/admin-server-executable.jar). <br>
     
  - Admin-Server use services from Catalog-Server and Order-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, and an environment variable for the order-server ip address called **'ORDER_IP_ADDRESS'** more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where the file is in and run the following: <br>
     
        java -jar admin-server-executable.jar   
  - Admin-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>

  
  
  
  ### APIs
  every microservisce provide certain services that can be accessed using API calling, and they used JSON  format data for both request parameters and response.
  
  
  **Catalog APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/query/topic/<topic>`  | `GET`  | query all the books with the specified `<topic>`, returns JSON object represinting matching books information.  |
| `/query/itemNumber/<itemNumber>`  | `GET`  | query the book with the specified `<itemNumber>`, returns a JSON object represinting the book if the book. |
| `/update/<itemNumber>`  | `PATCH`  | change quantity or price or both for the specified book, new quantity or new price or both should be sent through request body in JSON format.  |


  **Order APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/purchase/<itemNumber>`  | `POST`  | buy the book with this `<itemNumber>`, in a succsesful operation returns a `success` message, otherwise returns a message stating the problem.  |
| `/orders`  | `GET`  | returns all the successful orders.  |


  **FrontEnd APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/search/<topic>`  | `GET`  | search for books by their `<topic>`, returns all matching books `itemNumber` and `tittle`.   |
| `/info/<itemNumber>`  | `GET`  | get information about a book by its `<itemNumber>`, returns the matching book `tittle`, `quantity` and `price` if the book exixts or a `message` descriping the problem.    |
| `/purchase/<itemNumber>`  | `POST`  | buy a book by its `<itemNumber>`, in a succsesful operation returns a `success` message, otherwise returns a message stating the problem.   |


  **Admin APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/orders`  | `GET`  | returns all the successful orders.  |
| `/update/<itemNumber>`  | `PATCH`  | change quantity or price or both for the specified book, new quantity or new price or both should be sent through request body in JSON format.  |



## Deployment 

Each service was deployed on EC2 instance on AWS Cloud, and was associated with an elastic IP address, so they are available world wide using the following IP addresses:

  | Service name  | IP address | 
| ------------- | ------------- | 
| Catalog Server  | `54.189.228.214`  |
| Order Server  | `44.226.22.148`  |
| Frontend Server  | `52.24.191.246`  |
| Admin Server  | `52.88.93.191`  |

#### Note: Free Tier AWS account includes 750 hours of running instances each month, that means intances may be stopped at anytime to stay within the free tier.

## Testing
In this test serveral API calls to Frontend and Admin servers will take place to make sure the system is operating as desiered.

Frontend address: `http://52.24.191.246:4000`

Admin address: `http://52.88.93.191:4000`

Client IP was: `77.91.158.171`


### 1.Search for books by theri topic
 **Request URL**
      
       http://52.24.191.246:4000/search/distributed systems
      
 <br>
       
 **Response**
 
![image](https://user-images.githubusercontent.com/104044414/199623879-a250f4ab-2bca-463d-aa36-05839cfceeb3.png)


 

      
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
| ![image](https://user-images.githubusercontent.com/104044414/199624452-ddacb184-7af2-45ef-8c1b-4c060892e4be.png)  | ![image](https://user-images.githubusercontent.com/104044414/199624587-a8b3889c-3da3-4212-bbea-2f3d3dad4a7a.png) |

<br><br>

### 2.Ask for inforamtion about a selected book.
 **Request URL**
      
       http://52.24.191.246:4000/info/1
       
 <br>
       
 **Response**

![image](https://user-images.githubusercontent.com/104044414/199625127-fce81aa3-cc30-435b-958f-9c93512732dd.png)

     
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
|![image](https://user-images.githubusercontent.com/104044414/199625190-dc5e2bce-27d7-41af-8d8d-3a027d75df45.png)| ![image](https://user-images.githubusercontent.com/104044414/199625214-ce31baf1-077d-4a98-8dc9-5d893ded5610.png)|

<br><br>
 
### 3.Ask for inforamtion about not existing book.
 **Request URL**
      
       http://52.24.191.246:4000/info/7
       
 <br>
       
 **Response**

![image](https://user-images.githubusercontent.com/104044414/199625468-efad7fd5-b64a-4f21-bf5d-86d59761982e.png)



      
| **Frontend server log message:**    | **Catalog server log message:**   | 
| ------------- | ------------- | 
|![image](https://user-images.githubusercontent.com/104044414/199625561-a8368717-7992-448f-9b3d-16b82d23efe9.png)|![image](https://user-images.githubusercontent.com/104044414/199625592-eacd2c52-a9d0-4f07-90b9-987de6d00d4b.png)|


 <br><br>
 
### 4.Enter a non integer `<ItemNumber>` in request.
 **Request URL**
      
       http://52.24.191.246:4000/info/book1
      
 <br>
        
 **Response**

![image](https://user-images.githubusercontent.com/104044414/199625735-2be749df-e0c6-4461-84db-90aabf4ebb9f.png)



      
| **Frontend server log message:**    
| ------------- |
|![image](https://user-images.githubusercontent.com/104044414/199625785-7359f570-4397-4c76-995d-7e9dc08da660.png)|

 <br><br>
 
### 5.purchase a book.
 **Request cURL**
      
       curl --location --request POST 'http://52.24.191.246:4000/purchase/1' 
    
 <br>  
    
 **Response**

![image](https://user-images.githubusercontent.com/104044414/199626079-d75e1bc5-b73a-4056-80a0-2d9afd324868.png)



      
| **Frontend server log message:**    | **Catalog server log message:**   |  **Order server log message:**   | 
| ------------- | ------------- |  ------------- |
|![image](https://user-images.githubusercontent.com/104044414/199626161-bc68cbd3-eac8-41c7-89ef-6a5ccff20165.png)|![image](https://user-images.githubusercontent.com/104044414/199626199-7519a5c1-e703-4614-9795-7cb7d723a236.png)|![image](https://user-images.githubusercontent.com/104044414/199626239-5dadc364-bcd6-4073-8a10-2de06ab4d99b.png)|

 <br><br>
 
**Ordered Book quantity was updated**

![image](https://user-images.githubusercontent.com/104044414/199626477-4036aaba-1292-4211-a2be-4e2a4cc4b054.png)

 <br><br>

### 6.purchase book with `<itemNumber>`=1 until it becomes out of stock.

**Book Info when it becomes out of stock**

![image](https://user-images.githubusercontent.com/104044414/199626751-fedfc217-efb7-4c91-b6f4-ae214e97138d.png)
 
 <br>

**Purchase out of stock Book**


![image](https://user-images.githubusercontent.com/104044414/199626692-4110fa19-4fc8-4c66-8502-896b7135712e.png)



      
| **Frontend server log message:**    | **Catalog server log message:**   |  **Order server log message:**   | 
| ------------- | ------------- |  ------------- |
|![image](https://user-images.githubusercontent.com/104044414/199626931-27613aec-5589-4011-b168-a044179dc258.png)|![image](https://user-images.githubusercontent.com/104044414/199626975-550d4ae8-c3f7-49e7-af04-901991241550.png)|![image](https://user-images.githubusercontent.com/104044414/199627007-9749fea2-3dd3-4e44-80fe-c6d0dd8d2ee8.png)|






 








