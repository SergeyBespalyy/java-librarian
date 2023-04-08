FROM tomcat:9.0.73-jre11
RUN rm -rf /usr/local/tomcat/webapps/*
WORKDIR /code
COPY ./target/java-bibliotecar.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]
#FROM maven:3-openjdk-11
#
#WORKDIR /code
#ADD pom.xml /code/pom.xml
#RUN ["mvn", "dependency:resolve"]
#RUN ["mvn", "verify"]
#
#ADD src /code/src
#RUN ["mvn", "package"]
#
#CMD ["java", "-war", "code/java-bibliotecar.war"]