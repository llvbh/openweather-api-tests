<a id="readme-top"></a>
# Проект по автоматизации тестирования API <a href="https://openweathermap.org/stations">OpenWeatherMap Stations</a>

> OpenWeatherMap — сервис с данными о погоде. Weather Stations API позволяет создавать свои метеостанции и управлять ими

## :scroll: Содержание:

- <a href="#tools">Использованные инструменты</a>
- <a href="#cases">Реализованные тесты</a>
- <a href="#console">Запуск тестов из терминала</a>
- <a href="#allure">Allure-отчет</a>

<a id="tools"></a>
## :hammer_and_wrench: Использованные инструменты

<p align="center">
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50" alt="Java"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50" alt="IDEA"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.png" width="50" height="50" alt="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50" alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.png" width="50" height="50" alt="Gradle"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/RestAssured.png" width="50" height="50" alt="REST Assured"/></a>
<a href="https://github.com/allure-framework"><img src="images/logo/Allure.svg" width="50" height="50" alt="Allure"/></a>
</p>

Тесты написаны на языке <code>Java</code> с использованием <code>REST Assured</code>, сборщик — <code>Gradle</code>, запуск через <code>JUnit 5</code>.

В проекте есть:
- модели на <code>Lombok</code> для тела запроса и для ответа
- спецификации запросов и ответов
- Allure-листенер со своими шаблонами запроса и ответа

<a id="cases"></a>
## :ballot_box_with_check: Реализованные тесты

### Создание станции
- Станция успешно создается
- Станция не создается без `external_id`
- Станция не создается без названия
- Станция не создается с неправильными координатами

### Получение станции
- Созданную станцию можно получить по id
- Созданная станция есть в списке станций
- Нельзя получить несуществующую станцию
- Нельзя получить станцию с неправильным id
- Нельзя получить список станций с неверным ключом

### Изменение станции
- Название и высота станции успешно изменяются

### Удаление станции
- Удаленная станция больше не находится
- Нельзя удалить несуществующую станцию

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="console"></a>
## :computer: Запуск тестов из терминала

Для запуска нужен API-ключ, его можно получить на сайте <a href="https://openweathermap.org/">OpenWeatherMap</a> после регистрации:
```
./gradlew clean test -DapiKey=<ключ>
```

Сформировать и открыть Allure-отчет:
```
./gradlew allureServe
```

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/> Allure-отчет

В отчете для каждого теста есть шаги, запросы и ответы. API-ключ в адресах запросов заменяется на `***`.

<p align="right"><a href="#readme-top">наверх</a></p>
