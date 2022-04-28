#To improve performance, we can separate dependencies and applicatioin resources in a Soring Boot fat jar.
#The key is to create layers in the container filesystem
# The layers are cached both at build time and at runtime (in most runtimes),
#so we want the most frequently changing resources(usually the class and static resources in the application itself) to be layered after the more slowly changing resources.

FROM openjdk:11 as builder
ADD ./target/*.jar app.jar
#to exatract fatjar into the layeres dependencies, spring-boot-loader, snapshot-dependencies and application
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:11
#This Dockerfile extracts the layers from our fat jar, then copies each layer into the Docker image.
#Each COPY directive results in a new layer in the final Docker image.
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

EXPOSE 8081

# in case of debug
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","--trace"]
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
