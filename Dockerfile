FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/*.war
ENTRYPOINT ["java","-jar","*.war"]
