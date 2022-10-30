<h1 align="center">OTUS курс для продвинутых тестировщиков</h1>
<h3 align="left">Домашняя работа №1</h3>

---
В [этом](https://github.com/neupokoev/otus-homework-1/blob/main/src/test/java/ui/courses/Homework1Tests.java) 
файле 4 теста, реализующих задание первой домашки.\
Для запуска тестов необходимо выполнить несколько шагов: 
1. Скачать данный репозиторий, запустив команду в терминале
```bash
git clone https://github.com/neupokoev/otus-homework-1.git
```
2. Перейти в каталог `otus-homework-1`
```bash
cd otus-homework-1
```
3. Запустить тесты в одном из браузеров (также должен быть установлен на запускаемой машине)

|                        Браузер для запуска                        | Команда в терминале для запуска тестов  |
|:-----------------------------------------------------------------:|:----------------------------------------|
| <img src="src/main/resources/chrome.png" width="40" height="40">  | `mvn -Dbrowser="chrome" clean test`     |
| <img src="src/main/resources/firefox.png" width="40" height="40"> | `mvn -Dbrowser="firefox" clean test`    |
|  <img src="src/main/resources/opera.png" width="40" height="40">  | `mvn -Dbrowser="opera" clean test`      |