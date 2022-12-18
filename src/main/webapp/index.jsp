<%@ page import="ru.appline.logic.Model"  %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Домошняя страница по работе с пользователями</h1>
Введите ID пользователя (0 для вывода всего списка пользователя)
<br/>
Доступно: <%
    Model model =Model.getInstance();
        out.print(model.getFromList().size());
        %>

<form method="post" action="search">
    <label>ID
        <input type="text" name="id">
    </label>
    <button type="submit">Поиск</button>
</form>

<a href="addUser.html">Создать нового пользователя</a>
</body>
</html>