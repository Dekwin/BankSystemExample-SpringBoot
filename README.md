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

### Пример ответа
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


## POST wallets/{walletId}/transfer-transactions

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
- *required* **string** `description`. описание перевода (или toWalletId)

### Формат ответа
Статус код


