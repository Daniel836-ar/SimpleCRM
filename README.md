# Simple CRM System

RESTful API для управления информацией о продавцах (Sellers) и их транзакциях (Transactions) с модулем аналитики. Проект выполнен на Java с использованием фреймворка Spring Boot и СУБД PostgreSQL.

## Технологический стек
* **Язык разработки:** Java
* **Фреймворк:** Spring Boot (Spring Web, Spring Data JPA, Jakarta Validation)
* **База данных:** PostgreSQL
* **Сборщик проекта:** Gradle
* **Тестирование:** JUnit 5, Mockito

---

## Настройка и Запуск

 По умолчанию в проекте настроены следующие параметры подключения к PostgreSQL (`src/main/resources/application.properties`):
* **URL:** `jdbc:postgresql://localhost:5432/postgres`
* **Username:** `postgres`
* **Password:** `root`

### Способ 1. Запуск с дефолтными настройками
Если ваши локальные настройки PostgreSQL совпадают с дефолтными, просто запустите команду сборки:
```bash
./gradlew bootRun
```

### Способ 2. Запуск со своими параметрами БД
Если у вас другое название базы данных, имя пользователя или пароль, передайте их как переменные окружения при запуске проекта:
```bash
DB_URL=jdbc:postgresql://localhost:5432/имя_вашей_бд DB_USERNAME=ваше_имя DB_PASSWORD=ваш_пароль ./gradlew bootRun
```

### Запуск Unit-тестов
Бизнес-логика приложения изолированно протестирована с помощью Mockito. Для запуска тестов выполните:
```bash
DB_URL=jdbc:postgresql://localhost:5432/имя_вашей_бд DB_USERNAME=ваше_имя DB_PASSWORD=ваш_пароль ./gradlew test
```

---

## Примеры использования API (Тестирование)

### Вариант А. Готовая коллекция Postman
В корне проекта находится файл:
 **`SimpleCRM.postman_collection.json`**

Инструкция по использованию:
1. Откройте приложение Postman.
2. В верхнем левом углу нажмите кнопку трёх точек и выберите "Import".
3. Перетащите файл `SimpleCRM.postman_collection.json` из корня проекта.
4. В импортированной коллекции вы найдете полностью готовые папки со всеми запросами, включая преднастроенные JSON-тела (Body) для POST и PATCH методов.

### Вариант Б. Ручная проверка
Можно выполнить запросы вручную (через cURL или свою коллекцию), используйте следующие спецификации :(базовый URL: `http://localhost:8080`):

#### 1. Продавцы (Sellers) - `/api/sellers`
* `GET /api/sellers` - Получить список всех продавцов.
  * *Опциональный параметр фильтрации*: `/api/sellers?name=Иван` (поиск по имени).
* `GET /api/sellers/{id}` - Получить подробную информацию о конкретном продавце.
* `GET /api/sellers/{id}/transactions` - Получить список всех транзакций конкретного продавца.
* `POST /api/sellers` - Создать нового продавца. 
  * *Body (JSON)*: `{"name": "...", "contactInfo": "...", "registrationDate": "2026-05-22T15:30:00"}`
* `PATCH /api/sellers/{id}` - Частично обновить информацию о продавце.
* `DELETE /api/sellers/{id}` - Удалить продавца из системы.

#### 2. Транзакции (Transactions) - `/api/transactions`
* `GET /api/transactions` - Получить список всех транзакций в CRM.
* `GET /api/transactions/{id}` - Получить информацию о конкретной транзакции по ID.
* `POST /api/transactions` - Создать новую транзакцию (проверяет существование продавца).
  * *Body (JSON)*: `{"sellerId": 1, "amount": 1500, "paymantType": "CARD", "transactionDate": "2026-05-22T15:35:00"}`
* `PATCH /api/transactions/{id}` - Изменить данные транзакции .
* `DELETE /api/transactions/{id}` - Удалить транзакцию по ID.

#### 3. Модуль аналитики (Analytics) - `/api/analytics`
* `GET /api/analytics/lessThen?amount=5000` - Возвращает список продавцов, у которых общая сумма транзакций меньше переданного параметра `amount`.

* `GET /api/analytics` - Возвращает список продавцов, у которых сумма транзакций самая высокая в году, квартале, месяце, дне.
