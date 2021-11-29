FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/*.w
ENTRYPOINT ["java","-jar","*.war"]
