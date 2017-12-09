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

### Формат ответа
Такой же как и в **POST auth/signup**, + поле **int** id, wallets