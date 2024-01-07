# VirtualAnimals

Mapping:

Adopted Animal Service: Localhost:8082/api/adoptedanimal

POST: Create Adopted Animal items by Nickname
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

GET: Get Adopted Animal item by Animal Code:
http://localhost:8082/api/adoptedanimal?animalCode=ABC123

GET: Get Adopted Animal items
http://localhost:8082/api/adoptedanimal/all

GET: Get Adopted Animal items by Nickname
http://localhost:8082/api/adoptedanimal/search/Mar

PUT: Update Adopted Animal items by Animal Code
http://localhost:8082/api/adoptedanimal/update/DBC321

    {
        "animalCode": "DBC321",
        "nickname": "Claudette"
    }


DELETE: Delete Adopted Animal items by Animal Code
http://localhost:8082/api/adoptedanimal/delete/DBC321


Animals Service: http://localhost:8080/api/animal

POST: Create Animal item:
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


GET: Get Adopted Animal item by Animal Code:
http://localhost:8080/api/animal?animalCode=ABC123

GET: Get All Animal items:
http://localhost:8080/api/animal/all

GET: Get Animal items by Name or Description:
http://localhost:8080/api/animal/search/internet

PUT: Update Animal item:
http://localhost:8080/api/animal/update/DBC321

    {
        "animalCode": "DBC321",
        "name": "Tabby Cat 1",
        "description": "A very common breed of cat. First version.",
        "price": 30
    }

DELETE: Delete Animal item:
http://localhost:8080/api/animal/delete/DBC321

Food Service: http://localhost:8083/api/food

POST: Post Food item:
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


GET: Get all Food items:
http://localhost:8083/api/food/all

GET: Get Food item by foodcode:
http://localhost:8083/api/food?foodcode=POP932

GET: Get Food item by name:
http://localhost:8083/api/food/search/pi

PUT: Update Food item:
http://localhost:8083/api/food/update/PPP999

    {
        "foodcode": "PPP999",
        "name": "hamburger"
    }

DELETE: Delete Food item:
http://localhost:8083/api/food/delete/PPP999

Operation service: http://localhost:8081/api/operation

POST: Place Order:

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

POST: Feed an adopted animal food:
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


GET: Get all operation items:
http://localhost:8081/api/operation/all

GET: Get all operation items by operation number:
http://localhost:8081/api/operation?operationNumber=dd4bddc4-2f2a-4c79-816f-bbb9c7a257d1

GET: Get all operation items by date:
(Note: it shows all the operations whose date come after the given date.)
http://localhost:8081/api/operation/after/2024-01-01T00:03:52.353969

PUT: Update Operation:
Note: In the example, all that’s being changed is the date, changing the year from 2024 to 2025.
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

DELETE: Delete Operation:
http://localhost:8081/api/operation/delete/cb432450-09f3-404c-af49-adf7b52fa0fa
