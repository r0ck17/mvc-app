## Задание. MVC приложение

Создать новый проект по аналогии с https://github.com/AndreiBor/MVC-APP-ITA.git

1) Сделать `index.html` (или jsp) с формой аутентификации `login/password` и ссылкой на регистрацию нового пользователя
2) Сделать `registration.html` (или jsp) с формой регистрации: _name_, _age_, _email_, _login_, _password_
3) Сделать `menu.html` (или jsp) со ссылками на личный кабинет (UserServlet)
4) Создать 3 сервлета
   - AuthenticationServlet
   - RegistrationServlet
   - UserServlet

### AuthenticationServlet
- Проверяет есть ли в базе пользователь с такими login/password:
  - Если есть, то делает редирект на menu.html
  - Если нет - возвращает ошибку

### RegistrationServlet
- Принимает запрос от registration.html и проверяет есть ли пользователь с таким логином:
  - Если есть - возвращает ошибку
  - Если нет, то добавляет пользователя в базу

### UserServlet
- Отображает информацию о пользователе _name_, _age_, _email_, _login_, _password_
- Предоставляет возможность изменить **хотя бы** 1 поле

### Опциональная функциональность
- Валидация полей форм (*)
- Создание функционала - любая тема, к примеру ProductServlet с каталогом продуктов. Надо будет добавить другие классы dto dao (***)