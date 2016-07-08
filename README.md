# javaAssesmentNCDC - Java Association application
Project created for recrutation purposes in NCDC company.

Prerequisites:
- install: JDK 1.8 [Oracle jdk download link]
- install: Maven 3.x [Maven download link]

# Running application

## Building with maven

        mvn -T4 clean package

## Create db

	    java -jar app/target/app-1.0-SNAPSHOT.jar db migrate app/javaAssesment.yml

## Run

        java -jar app/target/app-1.0-SNAPSHOT.jar server app/javaAssesment.yml


[Oracle jdk download link]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Maven download link]: http://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache
