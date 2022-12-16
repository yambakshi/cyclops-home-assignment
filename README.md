# Home Assignment
In this task, you need to write a simple HTTP Denial-of-Service protection system. 

You may implement it using C# or Java. C++ is also fine but might take considerably more time. 

You may use any libraries that will make your code cleaner, or better in any way. 

## Server

1. Starts listening for incoming HTTP requests on two separate endpoints different from one another by a page URL, with simulated HTTP client identifier as a query parameter 
 (e.g. 

http://localhost:8080/StaticWindow?clientId=3 

http://localhost:8080/DynamicWindow?clientId=3 

) 

2. For each incoming HTTP request you will do the following: 
   1. Handle the request in a separate thread/task
   2. Check if this specific client reached the max number of requests per time frame threshold (no more than 5 requests per 5 secs with the following logic: 
      1. Static window - The time frame starts on each client’s first request and ends 5 seconds later, After the time frame has ended, the client’s first request will open a new time frame and so forth.  
       2. Dynamic window – The time frame slides with each client request, upon each received request making sure no more than 5 request being treated in each time frame.) 
    3. If the client hasn’t reached the threshold, it will get HTTP response with status code 200 (OK) otherwise status code 503 (Service Unavailable) 
3. The server will run until key press after which it will end up with all the threads/tasks and will exit. 
Please note, each of the window methods counts separately and should not share it’s window state. 


## General notes
1. Pay attention to thread safeness 
2. The solution should be as simple and clean as possible, avoid over design/engineering and stick to the requirements 