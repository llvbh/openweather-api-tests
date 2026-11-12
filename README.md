<a id="readme-top"></a>
<p align="center">
<a href="https://openweathermap.org/"><img src="images/logo/OpenWeather.svg" width="300" alt="OpenWeather"/></a>
</p>

# Проект по автоматизации тестирования API <a href="https://openweathermap.org/stations">OpenWeatherMap Stations</a>

> OpenWeatherMap — сервис с данными о погоде. Weather Stations API позволяет создавать свои метеостанции и управлять ими

## :scroll: Содержание:

- <a href="#tools">Использованные инструменты</a>
- <a href="#cases">Реализованные тесты</a>
- <a href="#console">Запуск тестов из терминала</a>
- <a href="#jenkins">Сборка в Jenkins</a>
- <a href="#allure">Allure-отчет</a>
- <a href="#testops">Интеграция с Allure TestOps</a>
- <a href="#jira">Интеграция с Jira</a>
- <a href="#telegram">Уведомление в Telegram</a>

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
<a href="https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50" alt="Allure TestOps"/></a>
<a href="https://www.atlassian.com/software/jira"><img src="images/logo/Jira.svg" width="50" height="50" alt="Jira"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50" alt="Jenkins"/></a>
<a href="https://web.telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50" alt="Telegram"/></a>
</p>

Тесты написаны на языке <code>Java</code> с использованием <code>REST Assured</code>, сборщик — <code>Gradle</code>, запуск через <code>JUnit 5</code>.

В проекте есть:
- модели на <code>Lombok</code> для тела запроса и для ответа
- спецификации запросов и ответов
- Allure-листенер со своими шаблонами запроса и ответа

Для удаленного запуска сделана джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.

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

<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.qa.guru/job/llvbh-openweather-api-tests/)

<p align="center">
<img src="images/screen/jenkins.png" alt="Jenkins" width="850"/>
</p>

### Параметры сборки в Jenkins:
- `API_KEY` — API-ключ OpenWeatherMap
- `TELEGRAM_TOKEN`, `TELEGRAM_CHAT` — токен бота и чат для уведомлений

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/> [Allure-отчет](https://jenkins.qa.guru/job/llvbh-openweather-api-tests/allure/)

В отчете для каждого теста есть шаги, запросы и ответы. API-ключ в адресах запросов заменяется на `***`.

<p align="center">
<img title="Allure Overview" src="images/screen/allure.png" width="850"/>
</p>

### Тест-кейсы

<p align="center">
<img title="Allure Tests" src="images/screen/allure-tests.png" width="850"/>
</p>

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="testops"></a>
## <img alt="Allure TestOps" height="25" src="images/logo/Allure_TO.svg" width="25"/> Интеграция с [Allure TestOps](https://allure.qa.guru/project/5426/dashboards)

Результаты каждой сборки в <code>Jenkins</code> загружаются в <code>Allure TestOps</code>. Дополнительные проверки API описаны там же как ручные тест-кейсы.

### Дашборд

<p align="center">
<img title="Allure TestOps Dashboard" src="images/screen/testops-dashboard.png" width="850"/>
</p>

### Запуск тестов

<p align="center">
<img title="Allure TestOps Launch" src="images/screen/testops-launch.png" width="850"/>
</p>

### Автоматизированные и ручные тест-кейсы

<p align="center">
<img title="Allure TestOps Test Cases" src="images/screen/testops-cases.png" width="850"/>
</p>

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="jira"></a>
## [Интеграция с Jira](https://jira.qa.guru/browse/MUL-70)

В задаче на разработку API-автотестов привязаны ручные тест-кейсы из <code>Allure TestOps</code>.

<p align="center">
<img title="Jira" src="images/screen/jira.png" width="850"/>
</p>

<p align="right"><a href="#readme-top">наверх</a></p>

<a id="telegram"></a>
## <img alt="Telegram" height="25" src="images/logo/Telegram.svg" width="25"/> Уведомление в Telegram при помощи бота

После выполнения тестов бот отправляет сообщение с результатами прогона.

<p align="center">
<img title="Telegram" src="images/screen/telegram.png" width="550"/>
</p>

<p align="right"><a href="#readme-top">наверх</a></p>
