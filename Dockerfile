FROM openjdk:17
EXPOSE 8080
ADD target/tickets.jar tickets.jar
ENTRYPOINT ["java","-jar","/tickets.jar"]
CMD ["java", "com.tickets.ticketsv2.TicketsV2Application", "--spring.profiles.active=prod"]
