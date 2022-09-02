# Monitor sensors service

# openApi:
http://localhost/swagger-ui/

# User:
Фарма входа:
http://localhost:80/api/v1/login method=GET

Вход:
http://localhost:80/api/v1/login?username=*&password=* method=POST

Из ответа берём токен и ложем в авторизацию.
Для Postman Можно взять из header AUTHORIZATION

# Admin
Сохранение сенсора:
http://localhost:80/api/v1/admin/sensors Method=POST

Редактирование сенсора:
http://localhost:80/api/v1/admin/sensors Method=PUT

Удаление сенсора:
http://localhost:80/api/v1/admin/sensors/delete/{uuid} Method=DELETE
uuid берётся из таблица View

#  View
Просмотр всех сохранённых сенсоров:
http://localhost:80/api/v1/user/sensors/view/all Method=GET

Просмотр черех поиск по параметку text
http://localhost:80/api/v1/user/sensors/view?text= Method=GET

# Руками:
1) Измените в настройках спринга url базы данных
2) Выполните /sql/.sql файлы иза папки по номерам.
3) Упакуйте в war архив
4) Выполните java -jar -"Название скомпилированного файла"

# Docker compose
1) Упакуйте.
2) Войдите в дерикторию проекта
3) Выполните: docker compose up

Зарегестрировано 2 пользователя: 

login: user  | password: user

login:  admin | password: admin

Содержит базу данных, прокси, pgAdmin(для проверки), проект

pgAdmin доступен по адресу: http://localhost:82/

Login: admin@admin.admin
Password: admin

База данных: password 172143