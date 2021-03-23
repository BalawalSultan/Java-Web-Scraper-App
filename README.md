# Simple Web Scraper

This is a simple Java program that extracts the images, internal hyperlinks and andexternal hyperlinks from a HTML file.

## Installation

Use [Maven](https://maven.apache.org/) to prepare the application.

```bash
mvn install compile package
```
Once the command is executed Maven
will generate a folder called target, inside that folder you will find two jar files “WebScraper-1.0-SNAPSHOT.jar” and “WebScraper.jar”, the one we are interested in is WebSraper.jar. Once we have the WebScraper.jar file we can take it and place it anywhere we want.

## Usage

To run the web scraper we must run the following command

```bash
java -jar WebScraper.jar "my_HTML_file.html"
```

## License
[MIT](https://choosealicense.com/licenses/mit/)