# dockerized-spring-boot-lp-author
Repository for liveProject: Dockerized Spring Boot


mvwn spring-boot:run
mvwn  spring-boot:build-image create docker image

FAQs
How can I connect to the database?

You can connect to your database using the Adminer interface or a database client such as DBeaver. Run the application containers using Docker Compose. Use the following parameters to connect to the database:

System: PostgreSQL
Server or Host: db (if you are using Adminer) or localhost (if you are using an external client)
Port: 55432 (only for external clients)
database: compose-postgres
password: compose-postgres
Database: compose-postgres
Check the parameters that are set in the docker-compose.yml file if there is a problem during connection.

How can I build and start the project without running tests?

To build and run the project without executing the tests you can use the following command:

./mvnw clean spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=best_insurance/api && docker-compose -f docker/docker-compose.yml up
How can I start the containers without running the entire build process?

Sometimes you don’t need to rebuild the Docker images when you make configuration changes. In this case, running the following command will start only the project.

Important: Changes made to the application.properties file and other Spring-related configurations require a full rebuild.

docker-compose -f docker/docker-compose.yml up
When I try to run the containers with Docker Compose, I get the error “repository does not exist or may require 'docker login.’”

If you already ran the docker login command and didn’t get an error from the command output,
try running the following command:

docker-compose -f docker/docker-compose.yml down
to shut down all the containers, then

docker rmi best_insurance/api
to remove the already created image. If you get an error, don’t worry. Run

./mvnw clean spring-boot:build-image -Dspring-boot.build-image.imageName=best_insurance/api
if you are using Maven, or

gradlew bootBuildImage --imageName=best_insurance/api
if you are using Gradle. Finally, run the following to start the containers:

docker-compose -f docker/docker-compose.yml up
How can I free disk space used by Docker?

Docker can be a bit disk space consuming, especially when you build, update, and delete images. To free space you can use the following command that deletes all the dangling images:

docker image prune
When I build the Docker image with Maven/Gradle, I get the error “Connection to the Docker daemon at ‘localhost’ failed.” What’s wrong?

On some Mac systems when building the Docker image for Spring Boot with

mvnw spring-boot:build-image
or

gradlew bootBuildImage
the following error could show up:

Execution failed for task ':bootBuildImage’.
Connection to the Docker daemon at ‘localhost’ failed with error "[2] No such file or directory"; ensure the Docker daemon is running and accessible
It seems to be a problem related to the Docker runtime you are using, which creates the socket for the daemon in a different position than the default. The problem was reported in this Stack Overflow post, which provides a solution for working with Gradle. The solution is more or less the same for Maven: the DOCKER_HOST environment variables have to be set.

Refer to the Maven plugin documentation and the Gradle plugin documentation for more details.

If the previous solution creates errors during test execution, such as

[testcontainers/ryuk:0.3.4] : Could not start container 
try to create the following symlink:

sudo ln -s “$HOME/.docker/run/docker.sock” /var/run/docker.sock
When I run the project on my Mac (M1), during the container startup I receive a warning for the image platform. What can I do to avoid it?

On Mac M1 computers (or other ARM architectures), during the container startup this warning appears in the logs:

bestinsurance The requested image’s platform (linux/amd64) does not match the detected host platform (linux/arm64/v8) and no specific platform was requested” 
To fix this problem you have to set the following environment variable:

export DOCKER_DEFAULT_PLATFORM=linux/amd64
If you already built the images with Maven or Gradle, even after the variable setup you can get the following error:

Error response from daemon: image with reference postgres:14.5-alpine was found but does not match the specified platform: wanted linux/amd64, actual: linux/arm64/v8
This is because there are still old images built for AMD64. To fix this issue you have to remove the old images using the following commands:

docker-compose -f docker/docker-compose.yml down --remove-orphans
to shut down the containers and

docker rmi postgres:14.5-alpine
to remove the image generating the problem.

The following command is useful for cleaning the Docker images. Use it carefully if you are also using Docker for another project because it removes all the images in your Docker repository.

docker rmi -f $(docker images -aq)
During startup I see an error for the table databasechangeloglock.

When Liquibase initializes, the database tries to access the databasechangeloglock table and logs the following error.

ERROR: relation “public.databasechangeloglock” does not exist at character 22 db | 2023-04-20 15:30:32.307 UTC [57] STATEMENT: SELECT COUNT(*) FROM public.databasechangeloglock
If you are starting the application with an empty database this is not a problem because Liquibase is running against a noninitialized database, so it creates its tables and proceeds.