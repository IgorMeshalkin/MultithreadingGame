Описание
-----------
Данное приложение представляет из себя модуль отвечающий за зачисление и списание игровых денег со счёта клана игрока. 
Изменение происходит по разным причинам в зависимости от того что выбрал игрок: Бой, Задание или Пополнение из своего кармана. 
Помимо этого приложение сохранаяет подробную информацию о каждом действии для службы поддержки (таблица events). 

База данных
-----------
Я использовал PostgreSQL. Перед запуском необходимо инициализировать базу данных этим скриптом:
[script.zip](https://github.com/IgorMeshalkin/MultithreadingGame/files/9898074/script.zip)

Пользовательский интерфейс
-----------
Я предусмотрел два варианта работы с приложением: <br>
1- Консольная версия доступна в классе Application который находится в папке java <br>
2- Для запуска WEB версии я использовал Tomcat 9 и ReactJS.  <br>
И то и другое я запускаю на стандартных портах.  <br> Таким образом REST API доступен до адресу: http://localhost:8080/,
а фронтэнд: http://localhost:3000/

WEB UI представляет из себя следующее:
![Демо](https://user-images.githubusercontent.com/97287038/198932281-1912df89-bb23-48e1-9a6b-a67a2ac30d4a.jpg)
Игрок может одновременно запустить любое количество событий, а по завершению посмотреть их результаты. 

Стек используемых технологий
-----------
Согласно ТЗ приложение написано без использования высокоуровневых библиотек на стороне бэкенда.   <br>
В работе я применил:  <br>
HttpServlets, JDBS API, Jackson, Maven и уже указанные выше ReactJS и PostgreSQL

Контакты
-----------
Меня зовут Игорь Мешалкин,   <br> буду рад замечаниям и предложениям по поводу своей работы.   <br>
тел(WhatsApp): 8-962-500-03-73   <br>
email: 770190@bk.ru
