# Codefactory

To build the project make sure you are in the root directory of the project
that is named as ```codefactory```

if you had made any changes to the project please follow step#1 otherwise goto step#2;
##### Step #1
```./mvnw clean package```

##### Step #2
NOTE: Docker is required to run the below commands.

Goto the docker folder in the root directory with
```cd docker```

Then run
```docker-compose up --build -d ```

Once the above process completes, Your api server will be running on port 8080

To create a short url use.
``` 
curl --location --request POST 'http://localhost:8080/api/v1/create-short' \
--header 'Content-Type: application/json' \
--data-raw '{
    "url": "http://myurlshortner.com"
}'
```

To get the short url use:

```
curl --location --request GET 'http://localhost:8080/api/v1/{id}'
```
Here id the hash generated by the url shortner.