# BankProj

# BankProj HTTP API

## Base path: http://139.162.227.121:8080/bankProj/ 
### Пример http://139.162.227.121:8080/bankProj/auth/signup - регистрация

## POST auth/signup

Создание нового пользователя.

### Заголовок 
Content-Type: application/json

### Параметры


- *required* **str** `firstName`. Имя
- *required* **str** `lastName`. Фамилия
- *required* **str** `email`. мейл
- *required* **str** `password`. Пароль
- *required* **str** `phone`. Телефон




### Формат ответа
статус код


### Примеры

Запрос:

```
{
  "password": "izuken676",
  "firstName": "Dimetrus",
  "lastName": "Pedota",
  "email": "superhuman2@mail.ru",
  "phone": "436544464"
}
```


Ответ:

```


```


## POST auth/signin

Авторизация пользователя.

### Заголовок 
Content-Type: application/json

### Параметры
- *required* **str** `email`. мейл
- *required* **str** `password`. Пароль

### Пример ответа залоговка
```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW0xIiwiZXhwIjoxNDg5NjA5OTgyfQ.iYznUqaZfRjrgmktuK8CxcJP7Au4QVTDcULe4WAvps_fP8lsCOPzTtVplRd9u5t1xQAHuZFvTJ61OUTFCtkZVQ

```


## GET users

Получение юзеров

### Заголовки 
Content-Type: application/json
```
#пример
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW0xIiwiZXhwIjoxNDg5NjA5OTgyfQ.iYznUqaZfRjrgmktuK8CxcJP7Au4QVTDcULe4WAvps_fP8lsCOPzTtVplRd9u5t1xQAHuZFvTJ61OUTFCtkZVQ
```
### Параметры


- *optional* **str** `firstName`. Имя
- *optional* **str** `email`. мейл

(или то или то , ищет по подстроке)



### Пример ответа
```
[
    {
        "id": 3,
        "firstName": "denis",
        "lastName": "lis",
        "email": "den@gmail.com",
        "password": "1234",
        "phone": "+380958833",
        "wallets": [
            {
                "id": 4,
                "amount": 33000,
                "currency": "UAH",
                "createdAt": 1512917792388,
                "updatedAt": 1512917792388,
                "account": "acc2",
                "sendTransactions": [],
                "receiveTransactions": [],
                "incomes": [],
                "outcomes": []
            }
        ]
    }
]
```


## GET users/current

Получение текущего авторизованного юзера

### Заголовки 
Content-Type: application/json
```
#пример
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW0xIiwiZXhwIjoxNDg5NjA5OTgyfQ.iYznUqaZfRjrgmktuK8CxcJP7Au4QVTDcULe4WAvps_fP8lsCOPzTtVplRd9u5t1xQAHuZFvTJ61OUTFCtkZVQ
```
### Параметры

### Пример ответа
```
{
    "id": 9,
    "firstName": "igorek",
    "lastName": "kasoi",
    "email": "d@mail.ru",
    "password": "$2a$10$McIBUU.vnvK4qXNAdI3oPeeejO5QgdKMlE4lRCSg/TsylFURoGVz.",
    "phone": "12345",
    "wallets": [
        {
            "id": 11,
            "amount": 1000,
            "currency": "UAH",
            "createdAt": 1513019467503,
            "updatedAt": 1513019467503,
            "account": "d@mail.ruUAH",
            "sendTransactions": [],
            "receiveTransactions": [],
            "incomes": [],
            "outcomes": []
        },
        {
            "id": 10,
            "amount": 88,
            "currency": "USD",
            "createdAt": 1513019467503,
            "updatedAt": 1513019467503,
            "account": "d@mail.ruUSD",
            "sendTransactions": [],
            "receiveTransactions": [],
            "incomes": [],
            "outcomes": [
                {
                    "id": 12,
                    "amount": 12,
                    "accountNumber": "hkghghj",
                    "datetime": 1513023743294,
                    "description": "jhghjgdesssvcccc"
                }
            ]
        }
    ]
}
```

## GET wallets

получение моих кошельков.

### Заголовок 
Content-Type: application/json

Authorization: `<token>`


### Пример ответа
```
[
    {
        "id": 10,
        "amount": 100,
        "currency": "USD",
        "createdAt": 1512918412279,
        "updatedAt": 1512918412279,
        "account": "d@mail.ruUSD",
        "sendTransactions": [],
        "receiveTransactions": [],
        "incomes": [],
        "outcomes": []
    },
    {
        "id": 11,
        "amount": 1000,
        "currency": "UAH",
        "createdAt": 1512918412279,
        "updatedAt": 1512918412279,
        "account": "d@mail.ruUAH",
        "sendTransactions": [],
        "receiveTransactions": [],
        "incomes": [],
        "outcomes": []
    }
]
```


## GET wallets/{walletId}/transfer-transactions

Получение списка переводов на кошелек с id 

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры
- *required* **long** `dateFrom`. дата с 
- *required* **long** `dateTo`. дата до

### Пример ответа
```
[
    {
        "id": 12,
        "amount": 12,
        "datetime": 1512916556407,
        "description": "hjkhjk"
    }
]
```



## POST wallets/{fromWalletId}/transfer

Перевод денег между кошельками, с кошелька с id = fromWalletId

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры
- *required* **long** `toWalletId`. куда переводить (или toAccount)
- *required* **string** `toAccount`. куда переводить (или toWalletId)
- *required* **long** `amount`. сумма (или toWalletId)
- *required* **string** `description`. описание перевода 

### Формат ответа
Статус код



## POST wallets/{walletId}/payment

Оплата с кошелька на аккаунт toAccount 
(например коммуналка)

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры
- *required* **string** `toAccount`. банковский счет
- *required* **long** `amount`. сумма 
- *required* **long** `outcomeTypeId`. ид типа перевода (OutcomeType)
- *required* **string** `description`. описание перевода 

### Формат ответа
```
{
    "id": 12,
    "amount": 12,
    "accountNumber": "hkghghj",
    "datetime": 1513023743294,
    "description": "jhghjgdesssvcccc"
}
```


## GET wallets/{fromWalletId}/outcomes

Получение оплат с даты по дату

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры
- *required* **long** `dateFrom`. с даты
- *required* **long** `dateTo`. по дату

### Формат ответа
```
[
    {
        "id": 12,
        "amount": 12,
        "accountNumber": "hkghghj",
        "datetime": 1513023743294,
        "description": "jhghjgdesssvcccc"
    }
]
```

## GET wallets/outcome-types

Получение типов оплат
(например коммуналка, налог на авто)

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры

### Формат ответа
```
[
    {
        "id": 7,
        "title": "Налог 1",
        "description": "налог на жилье",
        "outcomes": [
            {
                "id": 12,
                "amount": 12,
                "accountNumber": "hkghghj",
                "datetime": 1513023743294,
                "description": "jhghjgdesssvcccc"
            }
        ]
    },
    {
        "id": 8,
        "title": "Налог 2",
        "description": "налог на авто",
        "outcomes": []
    }
]
```

## POST wallets/{fromWalletId}/transfer

Перевод денег между кошельками, с кошелька с id = fromWalletId

### Заголовок 
Content-Type: application/json

Authorization: `<token>`
### Параметры
- *required* **long** `toWalletId`. куда переводить (или toAccount)
- *required* **string** `toAccount`. куда переводить (или toWalletId)
- *required* **long** `amount`. сумма (или toWalletId)
- *required* **string** `description`. описание перевода (или toWalletId)

### Формат ответа
Статус код
