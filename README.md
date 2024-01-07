# Virtual Animals EDE Project

## Mapping:

### Adopted Animal Service: 

http://localhost:8082/api/adoptedanimal

#### POST: Create Adopted Animal items by Nickname

http://localhost:8082/api/adoptedanimal

    {
        "animalCode": "ABC123",
        "nickname": "Johnny"
    }
    {
        "animalCode": "tube6in",
        "nickname": "Mark"
    }
    {
        "animalCode": "DBC321",
        "nickname": "Lisa"
    }



#### GET: Get Adopted Animal item by Animal Code:

http://localhost:8082/api/adoptedanimal?animalCode=ABC123

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/1GET1.png)

#### GET: Get Adopted Animal items

http://localhost:8082/api/adoptedanimal/all

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/1GET2.png)

#### GET: Get Adopted Animal items by Nickname

http://localhost:8082/api/adoptedanimal/search/Mar

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/1GET3.png)

#### PUT: Update Adopted Animal items by Animal Code

http://localhost:8082/api/adoptedanimal/update/DBC321

    {
        "animalCode": "DBC321",
        "nickname": "Claudette"
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/1PUT1.png)

#### DELETE: Delete Adopted Animal items by Animal Code

http://localhost:8082/api/adoptedanimal/delete/DBC321

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/1DELETE1.png)

### Animals Service: 

http://localhost:8080/api/animal

#### POST: Create Animal item:

http://localhost:8080/api/animal

    {
        "animalCode": "ABC123",
        "name": "Golden Retriever",
        "description": "An energetic, golden dog.",
        "price": 13
    }
    {
        "animalCode": "tube6in",
        "name": "Shiba Inu",
        "description": "An golden dog very popular on the internet.",
        "price": 20
    }
    {
        "animalCode": "DBC321",
        "name": "Tabby Cat",
        "description": "A very common breed of cat.",
        "price": 26
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2POST1.png)

(Opmerking: de code werkt zoals te zien is in de volgende afbeelding, aangezien het item is gemaakt.)

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2POST2.png)

#### GET: Get Adopted Animal item by Animal Code:

http://localhost:8080/api/animal?animalCode=ABC123

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2GET1.png)

#### GET: Get All Animal items:

http://localhost:8080/api/animal/all

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2GET2.png)

#### GET: Get Animal items by Name or Description:

http://localhost:8080/api/animal/search/internet

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2GET3.png)

#### PUT: Update Animal item:

http://localhost:8080/api/animal/update/DBC321

    {
        "animalCode": "DBC321",
        "name": "Tabby Cat 1",
        "description": "A very common breed of cat. First version.",
        "price": 30
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2PUT1.png)

#### DELETE: Delete Animal item:

http://localhost:8080/api/animal/delete/DBC321

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/2DELETE1.png)

### Food Service: 

http://localhost:8083/api/food

#### POST: Post Food item:

http://localhost:8083/api/food

    {
        "foodcode": "POP932",
        "name": "pie"
    }
    {
        "foodcode": "DFG889",
        "name": "Cupcake"
    }
    {
        "foodcode": "PPP999",
        "name": "burger"
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3POST1.png)


#### GET: Get all Food items:

http://localhost:8083/api/food/all

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3GET1.png)

#### GET: Get Food item by foodcode:

http://localhost:8083/api/food?foodcode=POP932

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3GET2.png)

#### GET: Get Food item by name:

http://localhost:8083/api/food/search/pi

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3GET3.png)

#### PUT: Update Food item:

http://localhost:8083/api/food/update/PPP999

    {
        "foodcode": "PPP999",
        "name": "hamburger"
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3PUT1.png)

#### DELETE: Delete Food item:

http://localhost:8083/api/food/delete/PPP999

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/3DELETE1.png)

### Operation service: 

http://localhost:8081/api/operation

#### POST: Place Order:

    {
      "orderLineItemsDtoList": [
        {
          "animalCode": "ABC123",
          "nickname": "Johnny"
        },
        {
          "animalCode": "tube6in",
          "nickname": "Mark"
        }
      ]
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4POST1.png)

#### POST: Feed an adopted animal food:

http://localhost:8081/api/operation/feeding

    {
      "feedingTimeItemsDtoList": [
        {
          "animalCode": "ABC123",
          "foodcode": "DFG889"
        },
        {
          "animalCode": "tube6in",
          "foodcode": "POP932"
        }
      ]
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4POST2.png)

#### GET: Get all operation items:

http://localhost:8081/api/operation/all

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4GET11.png)
![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4GET12.png)


#### GET: Get all operation items by operation number:

http://localhost:8081/api/operation?operationNumber=dd4bddc4-2f2a-4c79-816f-bbb9c7a257d1

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4GET2.png)

#### GET: Get all operation items by date:

(Opmerking: het toont alle bewerkingen waarvan de datum na de opgegeven datum valt.)

http://localhost:8081/api/operation/after/2024-01-01T00:03:52.353969

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4GET3.png)

#### PUT: Update Operation:

(Opmerking: in het voorbeeld wordt alleen de datum gewijzigd, waardoor het jaar verandert van 2024 naar 2025.)

http://localhost:8081/api/operation/update/dd4bddc4-2f2a-4c79-816f-bbb9c7a257d1

    {
            "operationNumber": "dd4bddc4-2f2a-4c79-816f-bbb9c7a257d1",
            "date": "2025-01-07T00:03:52.353969",
            "orderLineItemsList": [
                {
                    "id": 25,
                    "animalCode": "ABC123",
                    "price": 13.00,
                    "name": "Golden Retriever",
                    "nickname": "Johnny"
                },
                {
                    "id": 26,
                    "animalCode": "tube6in",
                    "price": 20.00,
                    "name": "Shiba Inu",
                    "nickname": "Mark"
                }
            ],
            "feedingTimeItemsList": []
    }

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4PUT1.png)

DELETE: Delete Operation:

http://localhost:8081/api/operation/delete/cb432450-09f3-404c-af49-adf7b52fa0fa

![alt text](https://github.com/AndyVandekerckhoveDelgra/VirtualAnimals/blob/main/images/4DELETE1.png)
