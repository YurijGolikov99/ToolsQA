# ![ToolsQA.png](ToolsQA.png)

# Пример работы с различными UI элементами на сайте [DemoQA][DemoQA.COM]
Этот проект представляет собой фреймворк для автоматизированного тестирования веб-приложения, 
основанный на принципах SOLID с применением паттернов проектирования Page Object Model (POM) и Page Factory Model (PFM) обеспечивая гибкость и удобство сопровождения кода.
Содержит автоматизированные тесты для проверки работы пользовательского интерфейса (UI) и веб-сервисов (API) сервиса [BookStore][Books] и различного рода элементов.
Для реализации функциональности проект реализован на разных тулах: 
- на проекте используются язык программирования Java;
- библиотека JUnit и TestNG;
- инструмент Selenium WebDriver и Selenide для работы с UI;
- библиотека RestAssured для работы с API;
- Allure для ведения отчетности;
- log4j для логирования действий.

## Содержание
- [Установкa](#установка)
- [Структура](#структура)
- [Использование](#использование)
- [Тесты](#тесты)
- [Содействие](#содействие)
- [Лицензия](#лицензия)

### Установка
1. Клонируйте репозиторий: `https://github.com/YurijGolikov99/IndexSoftTest.git`
2. Запустите IntelliJ IDEA.
3. В верхней панели меню Выберите "Get from VCS".
4. Выберите Version control: "Git"
5. Вставьте ссылку в поле "URL" и нажмите кнопку "clone".

*************************

### Структура
Структура проект папок Main и Test поделена на папки для Selenium и Selenide,
с одинаковой структурой для API тестов и разной для UI тестов.
- `api_module`: Пакет с модулями для работы с API, такими как отправка запросов, обработка ответов и т. д.
- - `constants`:
- - `data`:
- - `pages`:
- - `steps`:
- `common_module`: Пакет с общими утилитами и константами.
- - `constants`:
- - `data`:
- - `driver`:
- - `logs`:
- - `property`:
- - `utils`:
- `ui_module`: Пакет с модулями для работы с API, таких как объявление элементов, их обработка и использование.
- - `config`:
- - `constants`:
- - `data`:
- - `elements`:
- - `pages`:
- - `steps`:
- - `utils`:
- `resources`: Пакет с файлами свойств и другими ресурсами.
- `api`: Пакет с тестами и вспомогательными классами для взаимодействия с API.
- `ui`: Пакет с тестами и вспомогательными классами для взаимодействия с UI.

### Использование
1. Настройте ваше тестовое окружение.
2. Запустите нужный класс с тестами в папке [test](src%2Ftest).
3. Просматривайте результаты тестов в консоли.

### Тесты
Основной тестовый класс - BaseTest, который включает в себя следующие модули:
1. 1
2. а
3. /


### Содействие
Содействие приветствуется! Не стесняйтесь создавать вопросы или отправлять pull-запросы.


### Лицензия
Этот проект носит исключительно ознакомительный характер, копировать, и использовать может любой желающий, но только после того как он станет публичным. :)


#### Примечание
С более подробной информацией можно ознакомиться по ссылке https://github.com/topics/demoqa


*************************

[//]: # (### Run all tests: "mvn clean test -DsuiteXml=bookStoreAllTests -Dconfig=bookstore")

[//]: # ()
[//]: # (### Run Selenium tests: "mvn clean test -DsuiteXml=bookStoreSelenium -Dconfig=bookstore")

[//]: # ()
[//]: # (### Run Selenide tests: "mvn clean test -DsuiteXml=bookStoreSelenide -Dconfig=bookstore")

[//]: # ()
[//]: # (### Generate Allure report: "allure generate target/allure-results -c")



[DemoQA.COM]:https://demoqa.com
[Books]:https://demoqa.com/books
