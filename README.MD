# Проект - REST
[![Build Status](https://travis-ci.org/DmitriyYugai/job4j_rest.svg?branch=master)](https://travis-ci.org/DmitriyYugai/job4j_rest)
[![codecov](https://codecov.io/gh/DmitriyYugai/job4j_rest/branch/master/graph/badge.svg?token=CA9OXHP193)](https://codecov.io/gh/DmitriyYugai/job4j_rest)

# Содержание
* [Использованные технологии](#Использованные-технологии)
* [О проекте](#О-проекте)
* [Сборка](#Сборка)
* [Использование](#Использование)
* [Контакты](#Контакты)
## Использованные технологии
* Spring Boot
* REST Services
* Интеграция REST Services через RestTemplate
* Spring Core (DI, IoC)
* Spring MVC
* Spring Test
* Spring Data JPA
* Spring Security
* Авторизация Java JWT
* Tomcat
* PostgreSQL
* JUnit 5
* Mockito
* HSQLDB
* JaCoCo
* Travis CI
* Postman
## О проекте
* Данный проект представляет собой Spring boot приложение, включающее в себя 3 REST-сервиса<br><br>
* Неавторизованные пользователи с приложением работать не могут. Чтобы послать запрос на любой из 
REST-сервисов сначала необходимо пройти авторизацию, в противном случае будет выдана ошибка доступа.<br><br>
* Авторизация построена на Java JWT. Все зарегистрированные пользователи хранятся в БД. 
Все пароли пользователей хранятся в БД в закодированном виде<br><br>
* Приложение построено на двух слоях: слой по работе с БД и слой контроллеров<br><br>
* В качестве БД используется PostgreSQL<br><br>
* Работа с БД осуществляется через Spring Data JPA<br><br>
* Слой контроллеров использует Spring MVC<br><br>
* Все контроллеры построены по REST-архитектуре<br><br>
* Осуществлена интеграция REST-сервисов через RestTemplate<br><br>
* На все контроллеры написаны тесты с помощью Spring Test, Mockito и JUnit5<br><br>
* В качестве БД для тестов используется БД в памяти HSQLDB<br><br>
* Между сущностями используется связь OneToMany<br><br>
* В качестве системы непрерывной интеграции используется Travis CI<br><br>
* Для сбора информации о покрытии кода тестами и дальнейшего её анализа используются 
JaCoCo-plugin и сервис codecov.io<br><br>
* Для проверки приложения используется программа Postman (см. пример использования в 
разделе "[Использование](#Использование)")
## Сборка
1. Установить JDK 14.
2. Скачать Maven.
3. Создать переменные окружения JAVA_HOME и M2_HOME и прописать в переменную окружения Path 
пути до бинарных файлов для JDK и для Maven:<br><br>
![Java Maven installing](img/build/java_maven.PNG)<br><br>
4. Установить сервер БД PostgreSQL. При установке задать:<br>
    * имя пользователя - postgres 
    * пароль - root
    * порт - 5432<br>
Также при установке сервера БД PostgreSQL нужно установить программу pg_Admin.

5. Скачать исходный код проекта с GitHub.
6. Перейти в корень проекта, где лежит файл pom.xml:<br><br>
![Переход в корень проекта 1](img/build/cd.PNG)<br><br>
![Переход в корень проекта 2](img/build/cd1.PNG)<br><br>
7. С помощью Maven осуществить сборку проекта:<br><br>
![Сборка проекта](img/build/package.PNG)<br><br>
При успешной сборке должна появиться папка target, в которой располагается jar-архив:<br><br>
![Сборка прошла успешно](img/build/package1.PNG)<br><br>
![Папка target](img/build/target.PNG)<br><br>
![jar-архив](img/build/target1.PNG)<br><br>
8. Запустить сервер БД, указав путь до папки data:<br><br>
![Запуск сервера БД](img/build/server_start.PNG)<br><br>
9. Запустить программу pg_Admin, установленную вместе с сервером БД PostgreSQL:<br><br>
![Запуск pg_Admin](img/build/pg_admin.PNG)<br><br>
10. Создать БД с названием fullstack_auth:<br><br>
![БД fullstack_auth](img/build/fullstack_auth_db.PNG)<br><br>
11. Открыть Query Tool для созданной БД и запустить SQL-скрипт update_001.sql, 
находящийся в папке src/main/resources/db (папка src находится в корне скачанного проекта):<br><br>
![Скрипт update_001.sql](img/build/script.PNG)<br><br>
![Запуск скрипта update_001.sql](img/build/pg_admin_script.PNG)<br><br>
Сборка завершена. Теперь можно проверить работу приложения.
## Использование
* Переходим в папку target, где располагается собранный jar-архив и запускаем Spring boot приложение:<br><br>
![Запуск приложения 1](img/usage/start_app1.PNG)<br><br>
![Запуск приложения 2](img/usage/start_app2.PNG)<br><br>
* Для проверки работы приложения будем использовать программу Postman, чтобы посылать соответствующие 
HTTP-запросы на REST-сервисы:<br><br>
![Программа Postman](img/usage/postman.PNG)<br><br>
* Будем посылать запросы на REST-сервис, представленный классом EmployeeController, 
чтобы проверить связь OneToMany между сущностями.<br><br>
* Сейчас в БД нет данных:<br><br>
![БД пуста 1](img/usage/empty_db1.PNG)<br><br>
![БД пуста 2](img/usage/empty_db2.PNG)<br><br>
![БД пуста 3](img/usage/empty_db3.PNG)<br><br>
* Попробуем отправить POST-запрос на REST-сервис:<br><br>
![Доступ запрещён 1](img/usage/deny1.PNG)<br><br>
![Доступ запрещён 2](img/usage/deny2.PNG)<br><br>
![Доступ запрещён 3](img/usage/deny3.PNG)<br><br>
* Как видно в ответе мы получили ошибку 403 о запрете доступа. Это произошло потому, что мы отправили запрос 
под неавторизованным пользователем (без токена).<br><br>
* Сейчас в БД нет зарегистрированных пользователей, поэтому пройдём регистрацию, отправив следующий POST-запрос:<br><br>
![Регистрация пользователя 1](img/usage/reg1.PNG)<br><br>
![Регистрация пользователя 2](img/usage/reg2.PNG)<br><br>
![Регистрация пользователя 3](img/usage/reg3.PNG)<br><br>
![Регистрация пользователя 4](img/usage/reg4.PNG)<br><br>
* Как видно, в БД появился пользователь. Регистрация прошла успешно.<br><br>
* Теперь пройдём авторизацию (получим токен), отправив следующий POST-запрос:<br><br>
![Авторизация пользователя 1](img/usage/auth1.PNG)<br><br>
![Авторизация пользователя 2](img/usage/auth2.PNG)<br><br>
![Авторизация пользователя 3](img/usage/auth3.PNG)<br><br>
* Токен располагается в заголовке Authorization ответа от сервера.<br><br>
* Теперь мы можем получить доступ ко всем REST-сервисам нашего приложения, 
указывая в заголовках отправляемых запросов полученный токен.<br><br>
* Ещё раз попробуем отправить POST-запрос на REST-сервис, указав при этом полученный токен в заголовке 
с названием Authorization:<br><br>
![POST-запрос 1](img/usage/post1.PNG)<br><br>
![POST-запрос 2](img/usage/post2.PNG)<br><br>
![POST-запрос 3](img/usage/post3.PNG)<br><br>
![POST-запрос 4](img/usage/post4.PNG)<br><br>
![POST-запрос 5](img/usage/post5.PNG)<br><br>
![POST-запрос 6](img/usage/post6.PNG)<br><br>
* Как видно, POST-запрос был успешно отправлен и новый сотрудник был добавлен в БД.<br><br>
* Сделаем ещё один POST-запрос (добавим ещё одного сотрудника):<br><br>
![POST-запрос 11](img/usage/post11.PNG)<br><br>
![POST-запрос 12](img/usage/post12.PNG)<br><br>
![POST-запрос 13](img/usage/post13.PNG)<br><br>
![POST-запрос 14](img/usage/post14.PNG)<br><br>
![POST-запрос 15](img/usage/post15.PNG)<br><br>
![POST-запрос 16](img/usage/post16.PNG)<br><br>
* Теперь можно получить всех сотрудников, отправив следующий GET-запрос:<br><br>
![GET-запрос 1](img/usage/getall1.PNG)<br><br>
![GET-запрос 1](img/usage/getall2.PNG)<br><br>
![GET-запрос 1](img/usage/getall3.PNG)<br><br>
![GET-запрос 1](img/usage/getall4.PNG)<br><br>
* Получим сотрудника по id, отправив следующий GET-запрос:<br><br>
![GET-запрос по id 1](img/usage/getid1.PNG)<br><br>
![GET-запрос по id 2](img/usage/getid2.PNG)<br><br>
![GET-запрос по id 3](img/usage/getid3.PNG)<br><br>
* Обновим сотрудника с именем Dmitry и все его аккаунты, отправив следующий PUT-запрос:<br><br>
![PUT-запрос 1](img/usage/put1.PNG)<br><br>
![PUT-запрос 2](img/usage/put2.PNG)<br><br>
![PUT-запрос 3](img/usage/put3.PNG)<br><br>
![PUT-запрос 4](img/usage/put4.PNG)<br><br>
![PUT-запрос 5](img/usage/put5.PNG)<br><br>
![PUT-запрос 6](img/usage/put6.PNG)<br><br>
* Удалим сотрудника с именем Anton и все его аккаунты из БД, отправив следующий DELETE-запрос:<br><br>
![DELETE-запрос 1](img/usage/del1.PNG)<br><br>
![DELETE-запрос 2](img/usage/del2.PNG)<br><br>
![DELETE-запрос 3](img/usage/del3.PNG)<br><br>
![DELETE-запрос 4](img/usage/del4.PNG)<br><br>
![DELETE-запрос 5](img/usage/del5.PNG)<br><br>
![DELETE-запрос 6](img/usage/del6.PNG)<br><br>
* На этом проверка данного REST-сервиса закончена. Аналогичным образом можно проверить и остальные REST-сервисы.
## Контакты
Почта: studentnstu97@mail.ru<br>
Skype: live:.cid.9cdc925fb48220b4