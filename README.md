# Lundegaard test work

## Installation

clone project

```bash
git clone https://github.com/Abdykarov/Lundegaard_Test.git
```

open project directory

```bash
cd Lundegaard_Test
```

launch postgresql database with docker-compose

```bash
sudo docker-compose up -d
```

via maven start up spring boot application

```bash
./mvnw spring-boot:run
```

prerequisites

```bash
Node js - minimal version - v12.22.5
Npm version - 7.5.2
```

To launch react client, install npm packages and start react app

```bash
cd client
npm install
npm start
```

Swagger OpenAPI url

```bash
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

