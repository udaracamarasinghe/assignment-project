cd ./rest-service
mvn clean package

cd ..

docker-compose up --build --force-recreate
