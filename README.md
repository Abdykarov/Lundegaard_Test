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

To launch react client, install npm packages and start react app

```bash
cd client
npm install
npm start
```