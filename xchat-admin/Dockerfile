FROM java:8

LABEL author="XX <xieyaqi11@gmail.com>"

EXPOSE 8080

VOLUME /tmp
COPY xchat-admin.jar /usr/local/xchat-admin.jar
WORKDIR /usr/local
ENTRYPOINT ["java", "jar", "/usr/local/xchat-admin.jar"]
