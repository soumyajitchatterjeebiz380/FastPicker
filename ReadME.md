# FastPick
The FastPick API is designed to enhance the shopping experience for customers by optimizing their item pickup process. With the FastPick API, customers can streamline their shopping lists and receive suggestions for alternatives, ultimately assisting them in choosing the fastest route through the store.

# Disclaimer
FastPick is an API which is suppose to be fed with configurations beforehand. Since most grocery stores do not allow access to their private API's, this API has placeholder configurations. In order to integrate with local grocery markets, please change the configurations manually.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
  - [Installation](#installation)
- [Future Features and Improvements](#futurefeaturesandimprovements)
- [Contributing](#contributing)
- [Ownership](#ownership)

## Features

- **Fastest Route Recommendations:** Get optimized routes for picking up items in the store, minimizing time spent navigating aisles.

- **Intuitive List Entry:** Effortlessly entering, deleteing and updating shopping items into a list using the user-friendly API interface.

- **Alternative Suggestions:** Receive suggestions for suitable replacements when an item is unavailable or alternatives are preferred(Currently in Progress).

- **Customized Experience:** Tailored recommendations based on individual shopping lists, preferences, and store layouts(Currently In Progress).

- **Seamless Integration:** Easily integrate the FastPick API into various shopping apps and platforms for an enhanced shopping experience.


## Getting Started
In order to use the API, I have created a swagger documentation file in the home directory to help visualize the endpoints. Please refer to that documentation. Examples are also provided.

### Installation
1. Please clone the GitHub directory onto your local computer.
2. Then head to the application.properties file under the `src/main/resources`. Change the credentials of the MySQL database to your MySQL credentials.
3. The API will run on the default `localhost:8080`, so if another application is already using that port, use the command `server.port=YOUR_PORT`, where you replace `YOUR_PORT`  with the port you have in mind. 
4. Please use the following code to create a simple table that stores the list.
```mysql
use testdb;
CREATE TABLE itemlist(
	id VARCHAR(12) PRIMARY KEY,
    aisle VARCHAR(2) ,
    itemname VARCHAR(20),
    quantity INT
);
```
5. Then just run the command mvn spring-boot:run.

``Reminder: Please check the application.properties file to correctly add your proper credentials for the mysql database.``

## Future Features and Improvements
The algorithm used to generate the map is a brute-force approach to implementing the Traveling Salesman Problem. The previous time complexity of this approach was O(n!), where n represents the number of unique items in a list. However, this approach was later reduced to the time complexity of O(m!), where m is the number of unique aisles. This approach sometimes will not guarantee the correct answer because every grocery market has unique organization methods. A better way to reduce the time complexity of this problem is to use dynamic programming, which is currently in the works.


## Contributing
I am always intrigued by other people's ideas and continue to welcome their knowledge to help me improve my applications. If you want to contribute, please feel free to contact me.


## Ownership

Name: Soumyajit Chatterjee

Linkedin: https://www.linkedin.com/in/soumyajit-chatterjee-b46758181/



