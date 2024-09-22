# Проект автотестов для учебного сервиса «Яндекс.Самокат»

## Описание проекта
Этот проект предназначен для автоматизации тестирования учебного сервиса «Яндекс.Самокат». В проекте реализованы автотесты для проверки функциональности выпадающего списка в разделе «Вопросы о важном» и процесса заказа самоката.

## Технологии
- **Java 11**
- **JUnit 4.13.1**
- **Selenium 4.22.0**
- **Maven 3.9.0**

## Тестовые сценарии
### Выпадающий список в разделе «Вопросы о важном»
Тест проверяет, что при нажатии на стрелочку открывается соответствующий текст.

### Заказ самоката
Тест проверяет весь флоу позитивного сценария с двумя наборами данных. Проверяются две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.

#### Позитивный сценарий:
1. Нажать кнопку «Заказать». На странице две кнопки заказа.
2. Заполнить форму заказа.
3. Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.

## Настройка проекта
1. Убедитесь, что у вас установлены Java 11 и Maven 3.9.0.
2. Склонируйте репозиторий проекта.
3. Откройте проект в вашей IDE.

## Запуск тестов
Для запуска тестов выполните следующую команду в терминале: 

`mvn clean test` 