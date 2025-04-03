# DeZafio Kotlin

[![Security Pipeline](https://github.com/GuillaumeFalourd/dezafio-kotlin/actions/workflows/security_pipeline.yml/badge.svg)](https://github.com/GuillaumeFalourd/dezafio-kotlin/actions/workflows/security_pipeline.yml)

## How to use?

Run the application on the `VManagementApplication.kt` class or executing the command line `mvn spring-boot:run` on the repository root.

Other option is to use Docker: `docker-compose up --build`

### Services

You can import this [Postman Collection](https://www.getpostman.com/collections/0a95939f289339e039ca) to test the API locally.

#### Add User

**POST:** `http://localhost:8080/users` with body:

```json
{
    "name":"Name",
    "cpf":"cpf",
    "email":"email",
    "birthDate":"1900-01-01"
}
```

#### Get User by CPF

**GET:** `http://localhost:8080/users/{cpf}` will return:

```json
{
    "name":"Name",
    "cpf":"cpf",
    "email":"email",
    "birthDate":"1900-01-01",
    "vehicles": ["..."]
}
```

#### Add Vehicles

**POST:** `http://localhost:8000/vehicles/{cpf}` with body:

```json
{
    "brand": "7",
    "model": "8819",
    "yearAndFuel":"2021-1"
}
```

#### Get Vehicles by CPF

**GET:** `http://localhost:8080/vehicles/{cpf}` will return:

```json
[
    {
        "id": 6,
        "brand": "BMW",
        "model": "i3 Bev Full 170cv Aut. (Elétrico)",
        "yearAndFuel": "2021-1",
        "rotation": true,
        "price": "R$ 246.161,00"
    },
    {"..."}
]
```
