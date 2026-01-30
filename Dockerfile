# ----------------------
# Build stage
# ----------------------
FROM maven:3.9.12-eclipse-temurin-21 AS build

ARG ARTIFACT_ID
ARG VERSION

WORKDIR /app


# Make sure .m2 exists
RUN mkdir -p /root/.m2


# Copy Jenkins-provided Maven settings from build context
COPY settings.xml /root/.m2/settings.xml

# Copy pom.xml and pre-download dependencies
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline -s /root/.m2/settings.xml


# Copy source code and package
COPY src ./src
RUN mvn -B -DskipTests package -s /root/.m2/settings.xml

# ----------------------
# Runtime stage
# ----------------------
FROM eclipse-temurin:21-jdk-alpine

ARG ARTIFACT_ID
ARG VERSION


ENV ARTIFACT_ID=${ARTIFACT_ID}
ENV VERSION=${VERSION}
ENV SPRING_PROFILES_ACTIVE=pprod

WORKDIR /app

COPY --from=build /app/target/${ARTIFACT_ID}-${VERSION}.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar"]
