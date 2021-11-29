FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/*.war  *.war
ENTRYPOINT ["java","-jar","*.war"]
