./mvnw clean package
docker-compose down
docker-compose up -d --build
