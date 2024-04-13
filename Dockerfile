FROM openjdk:21-jdk-slim

# Install OpenJDK
#RUN apk --no-cache add openjdk21-jdk

WORKDIR /app

COPY ./target/intuitErez.jar /app/intuitErez.jar

LABEL authors="erez fiengertz"

EXPOSE 8090

CMD ["java", "-jar", "intuitErez.jar"]
#ENTRYPOINT ["top", "-b"]