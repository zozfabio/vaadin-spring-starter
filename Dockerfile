FROM amazoncorretto:21-alpine AS corretto-jdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
    --verbose \
    --add-modules java.base,java.management,java.naming,java.security.jgss,java.instrument,java.desktop,java.logging,jdk.zipfs \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /customjre

FROM amazoncorretto:21-alpine AS builder
WORKDIR /app
COPY target/*.jar ./app.jar
RUN java -Djarmode=tools -jar app.jar extract
RUN java -Dspring.aot.enabled=true \
    -Dspring.context.exit=onRefresh \
    -Dspring.main.banner-mode=off \
    -XX:ArchiveClassesAtExit=app/app.jsa \
    -jar app/app.jar
# main app image
FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# copy JRE from the base image
COPY --from=corretto-jdk /customjre $JAVA_HOME

# Add app user
ARG APPLICATION_USER=appuser
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER

# Configure working directory
RUN mkdir /app && \
    chown -R $APPLICATION_USER /app

USER 1000

WORKDIR /app
COPY --from=builder --link /app/app/ ./

EXPOSE 8080
ENTRYPOINT ["/jre/bin/java", \
"-Djava.net.preferIPv4Stack=true", \
"-XX:SharedArchiveFile=app.jsa", \
"-Duser.language=pt", "-Duser.country=BR", "-Duser.timezone=GMT-3", \
"-Dspring.aot.enabled=true", \
"-jar", "app.jar" \
]