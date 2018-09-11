# Simple Web Crawler

Simple web crawler to find all the internal links, images, external links

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
maven
git
```

### Installing

A step by step series of examples that tell you how to get a development env running

```
clone the repository
```

navigate to the project folder where the pom file is located
runt the following command
```
mvn clean install
mvn package
mvn spring-boot:run -Dspring-boot.run.arguments="--rooturl=https://www.prudential.co.uk"
```



the site map will be generated in the project root folder with the name sitemap.log

## Running the tests

mvn test


## Built With


* [Maven](https://maven.apache.org/) - Dependency Management

## Future extensions
Currently the processing of urls is in a sequential order, it can be parallelized for faster processing
display is rudimentary, tree like structure can be provided
testing scenarios can be added

