# Transaction Statistics Service
A RESTful API for transaction statistics. The main use case for the API is to calculate real time statistics for the last 30 seconds of transactions.
The API needs the following endpoints:

- ```POST /transaction```: called every time a transaction is made.
- ```GET /transaction```: returns the statistic based on the transactions of the last 30 seconds
- ```DELETE /transaction```: deletes all transactions.

## Tech/framework used

- Springboot 2.7.8
- jdk 1.8
- maven
- Swagger 2
- Lombok

## Step-By-Step guide to setup project on local system

- Clone project from the git repository using this link [Git Repo](https://github.com/WisdomEssien/transaction-statistics.git).
	
- Open command prompt and navigate to the desired directory. Copy, paste and execute the git command below on the command prompt.
_It's assummed you have to have git and maven installed on your system._

```
	git clone -b master https://github.com/WisdomEssien/transaction-statistics.git
```

- Open the downloaded project folder, launch command prompt from this directory. Copy, paste and execute the command below on the command prompt. 
This is to build and package the application as a jar.

```
	mvn clean package
```

- Navigate to the **target** folder which is within the project directory. Launch CMD here, copy, paste and execute the command below on the command prompt to run application. 

```
	java -jar transactionstatistics.jar
```

- Launch the swagger documentation page which can also be used to test the service. [Swagger Documentation](http://localhost:8989/api/swagger-ui.html#/)

Hope this was helpful to get you started

[wisdom essien](https://github.com/WisdomEssien/transaction-statistics.git) © 2022 