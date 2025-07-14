# Fileupload

A Spring Boot application for uploading files to AWS S3 using secure IAM credentials.

## Description

This project demonstrates how to build a Java web service that allows users to upload files to AWS S3. It uses Spring Boot for rapid setup and integrates with AWS SDK to interact with S3 buckets, leveraging IAM for security.

## Technologies Used

- Java
- Spring Boot
- AWS SDK
- Amazon S3
- IAM (AWS Identity and Access Management)

## Getting Started

### Prerequisites

- Java 11 or higher
- Spring Tool Suite (or any IDE supporting Spring Boot)
- AWS account with IAM credentials (access key & secret key)
- Maven or Gradle

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/fileupload.git
   ```

2. **Open the project in Spring Tool Suite or your preferred IDE.**

3. **Add AWS SDK and Spring Boot dependencies**
   - For Maven:
     ```xml
     <dependency>
       <groupId>com.amazonaws</groupId>
       <artifactId>aws-java-sdk-s3</artifactId>
       <version>1.12.700</version>
     </dependency>
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     ```
   - For Gradle:
     ```groovy
     implementation 'com.amazonaws:aws-java-sdk-s3:1.12.700'
     implementation 'org.springframework.boot:spring-boot-starter-web'
     ```

4. **Configure your AWS credentials in `application.properties`**
   ```
   aws.accessKeyId=YOUR_ACCESS_KEY
   aws.secretKey=YOUR_SECRET_KEY
   aws.s3.bucket=YOUR_BUCKET_NAME
   aws.region=YOUR_AWS_REGION
   ```

### Running the Application

```bash
mvn spring-boot:run
```
or
```bash
./gradlew bootRun
```

## Usage

- Use the provided REST API endpoint to upload files to your configured S3 bucket.
- Example request (using curl):
  ```bash
  curl -F "file=@path/to/your/file.txt" http://localhost:8080/upload
  ```

## Credits

Project maintained by yahoshuva.


