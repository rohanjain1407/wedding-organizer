##Steps to build and run  
  1. Run mvn clean install.  
  2. Export the generated .war file to the webapps folder of the host server.  
  3. Start the server.  
  4. Make the rest api call  
    `http://localhost:8080/weddingApi/wedding/weddinginfo`  
    _*assuming server is running on port 8080_


Wedding API

Wedding
Get (GET)
http://localhost:8080/weddingApi/rest/wedding/get/1

Query (GET)
http://localhost:8080/weddingApi/rest/wedding/query

Device Token
Add (POST)
http://localhost:8080/weddingApi/rest/deviceToken/add
BODY -
{
    "device_token": "token_here",
    "weddingId": "1"
}


