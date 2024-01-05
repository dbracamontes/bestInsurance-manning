FROM amazoncorretto:17-alpine-jdk
COPY target/bestInsuranceApi-0.0.1-SNAPSHOT.jar bestInsuranceApi.jar
ENTRYPOINT ["java","-jar","/bestInsuranceApi.jar"]