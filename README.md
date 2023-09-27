# trafficLabAPI
## Branch info
Master branch has solution using resttemplate,Webclient branch has a solution using webclient, and te a solution that returns top 10 buslines with the unnamed bustations filtered.
## About
This Application is a Sprint boot application that uses RestTemplate to communicate with another API.
This program's purpose is to consume TraficLabs API, grab all Buslines and its stops, also consume another endpoint to grab all the names of all bus stations, and match the JourneyPatternPointOnLine with the name of the stations.


## How to run
1. To run the program, all you have to do is build the maven project if not done yet, and start the application in IntelliJ.
2. To run the request, go to localhost:8080/api/top-10-busiest-buslines and refresh the page once, everytime it is refreshed, it will run the request again.

## Result
The application prints the top 10 buslines with its busstops, and the name of the stops if they are available, otherwise it will just print the busline and the ID of the bus stop, and these values are printed in the Console.
