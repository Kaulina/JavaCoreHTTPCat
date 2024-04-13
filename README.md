Запрос на приведение списка фактов о кошках
Описание
По адресу https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats находится список фактов о кошках. Наша задача - сделать запрос к этому ресурсу и отфильтровать факты, за которые никто не проголосовал (поле голосов "за"). Формат каждой записи следующий:

{
  "id": "5b4910ae0508220014ccfe91",
  "text": "Кошки могуть создавать более 100 разных звуков, тогда как собаки только около 10.",
  "type": "cat",
  "user": "Alex Petrov",
  "upvotes": null
},
id - уникальный идентификатор записи
text - сообщение
type - тип животного
user - имя пользователя
upvotes - голоса
Что нужно сделать
Прочитайте весь список и верните только те факты, в поле которых голосов не равно null.
Реализация
Перед началом перехода URL-адрес https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats в браузере и просмотрите данные;
Создайте проект mavenили gradleи ссылки в pom.xml или gradle.build базы Apache httpclient. Пример:
<dependency>
   <groupId>org.apache.httpcomponents</groupId>
   <artifactId>httpclient</artifactId>
   <version>4.5.12</version>
</dependency>
создайте метод, в котором таблицы и настройте класс, CloseableHttpClientнапример, с помощью builder
CloseableHttpClient httpClient = HttpClientBuilder.create()
    .setDefaultRequestConfig(RequestConfig.custom()
        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
        .build())
    .build();
Добавьте запрос объекта HttpGet request = new HttpGet(" https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats ") и вызовите удаленный сервис CloseableHttpResponse response = httpClient.execute(request);
Добавьте в pom.xml или gradle.build библиотеку для работы с json. Пример:
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.11.1</version>
</dependency>
Создадим класс, в котором будем преобразовывать json-ответ с сервера;
Преобразуйте json в список java-объектов;
Отфильтруйте список — например, средствами потока API, с помощью метода filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0);
Вы выводите результаты на экран;
Отправьте задачу на проверку.
