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
  - Download the executable jar file for Catalog-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/catalog-Server-executable.jar). <br>
     
  - Download catalog database csv file from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/catalogDatabase.CSV). <br>
     
  - Put the files that were downloaded in the previous steps in the same folder. <br>
     
  - Open a tereminal on the folder where the files in and run the following: <br>
     
          java -jar catalog-Server-Runnable.jar      
  - Catalog-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
    
    
      
**2. Order-Server:** 
  - Download the executable jar file for order-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/order-server-executable.jar). <br>
     
  - Download order database csv file from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/orderDatabase.csv). <br>
     
  - Put the files that were downloaded in the previous steps in the same folder. <br>
  
  - Order-Server use services from Catalog-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where those files are in and run the following: <br>
     
          java -jar Order-Server-Runnable.jar     
  - Order-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
  
  
  **3. FrontEnd-Server:**
  - Download the executable jar file for frontend-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/frontend-server-executable.jar). <br>
     
  - FrontEnd-Server use services from Catalog-Server and Order-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, and an environment variable for the order-server ip address called **'ORDER_IP_ADDRESS'** more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where the file is in and run the following: <br>
     
          java -jar Frontend-Server-Runnable.jar   
  - FrontEnd-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>
     
   
   

**4. Admin-Server:**
  - Download the executable jar file for order-Server from [here](https://multi-tier-online-book-store.s3.us-west-2.amazonaws.com/admin-server-executable.jar). <br>
     
  - Admin-Server use services from Catalog-Server and Order-Server,so you need to set an environment variable for the catalog-server ip address called **'CATALOG_IP_ADDRESS'**, and an environment variable for the order-server ip address called **'ORDER_IP_ADDRESS'** more information about setting environment variables from [here](https://chlee.co/how-to-setup-environment-variables-for-windows-mac-and-linux/). <br>
     
  - Open a tereminal on the folder where the file is in and run the following: <br>
     
        java -jar Admin-Server-Runnable.jar    
  - Admin-service is now up and running and can be accessed using the device IP address and **port 4000**. <br>

  
  
  
  ### APIs
  every microservisce provide certain services that can be accessed using API calling, and they used JSON  formatt data.
  
  
  **Catalog APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/query/topic/<topic>`  | `GET`  | Content Cell  |
| `/query/itemNumber/<itemNumber>`  | `GET`  | Content Cell  |
| `/update/<itemNumber>`  | `PATCH`  | Content Cell  |


  **Order APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/purchase/<itemNumber>`  | `POST`  | Content Cell  |
| `/orders`  | `GET`  | Content Cell  |


  **FrontEnd APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/search/<topic>`  | `GET`  | Content Cell  |
| `/info/<itemNumber>`  | `GET`  | Content Cell  |
| `/purchase/<itemNumber>`  | `POST`  | Content Cell  |


  **Admin APIs**
  
  | URI  | Method | Description |
| ------------- | ------------- | ------------- |
| `/orders`  | `GET`  | Content Cell  |
| `/update/<itemNumber>`  | `PATCH`  | Content Cell  |











