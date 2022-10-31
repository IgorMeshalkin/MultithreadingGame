<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .main {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-flow: column wrap;
        font-family: 'Signika Negative', sans-serif;
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }

    .message {
        font-size: 22px;
    }

    .button {
        background-color: #A6A6C9;
        font-size: 18px;
        font-weight: bold;
        height: 40px;
        width: 150px;
        border-radius: 16px;
        cursor: pointer;
        border: none;
        margin-top: 15px;
    }
</style>
<head>
    <title>Автоматическая страница</title>
</head>
<body class="main">
<div class="message">Это автоматическая страница Tomcat</div>
<div class="message">После запуска React игра доступна по другому адресу</div>
<button class="button" onclick="location.href = 'http://localhost:3000/'">Перейти</button>
</body>
</html>
