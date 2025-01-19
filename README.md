# Тестовое задание – Library API : MODSEN

---
# О запуске проекта:

Проект содержит кастомные gradle tasks, которые были созданы для упрощения запуска API.
Кастомные таски находятся в группе `api-custom-tasks`.

Чтобы запустить проект необходимо:

1. Необходимо сначала запустить таску `publishStarters`, которая опубликует все стартеры в приложении в ваш локальный репозиторий.
   
(Через CLI: gradle publishStarters || Или через интерфейс вашей IDE).

2. После этого запустить вторую таску: `buildApplicationBootJars`, которая создаст Jar файлы для запуска сервисов в Docker
   
(Через CLI: gradle buildApplicationBootJars || Или через интерфейс вашей IDE).

3. В корне проекта находится `docker-compose` файл, который необходимо запустить в терминале командой `docker-compose up -d`

После запуска `docker-compose`-файла необходимо подождать некоторое время, чтобы успела накатиться миграция баз данных через `Liquibase`

---
# Об Эндпоинтах:

После запуска приложения можно ознакомиться с его функционалом и эндпоинтами:

В корне проекта находится файл `LIbrary-API.postman_collection.json`, который можно импортировать в Postman.

Подробная информация об эндпоинтах приложения предоставлена в соответствующих документациях:

http://localhost:8080/swagger-ui/index.html - документация `book-storage-service`

http://localhost:8081/swagger-ui/index.html - user-service `book-tracking-service`

http://localhost:8082/swagger-ui/index.html - user-service `authentication-service`

---
## Как пользоваться API?

API использует `Spring Security`, поэтому не все эндпоинты открыты для не аутентифицированного пользователя

Для полноценной работы необходимо получить Bearer Token с помощью эндпоинта: `127.0.0.1:8080/tokens`.
Далее token использовать в запросах.

---

## Роли api

API имеет роли для пользователей.  По умолчанию определены следующие роли:

- Администратор (role `ADMIN`) может производить CRUD-операции со всеми
  сущностями
- Пользователь (role `USER`) может просматривать свободные книги, книгу по id и isbn

В приложении определены следующие пользователи по умолчанию:

- role `ADMIN` : login: `Vlad`, password: `111111`
- role `USER`: login `Lolita`, password `123123`

---

## Покрытие тестами

Код покрыт module тестами, а именно:

service-layer следующих модулей: `book-storage-service`, `book-tracking-service`, `authentication-service`

## Спасибо за посещение!)