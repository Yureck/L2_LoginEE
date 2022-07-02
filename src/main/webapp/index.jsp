<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Prog Academy</title>
      <style>
            form{
                width: 30%;
                margin: 0 auto;
                border: 1px solid #c6c6c6;
                padding: 1rem;
                border-radius: .5rem;
                box-sizing: border-box;
            }
            input{
                width: 80%;
                box-sizing: border-box;
                margin-bottom: .5rem;
            }
            input::placeholder {
                color: #4d4d4d;
            }
            label{
                width: 20%;
                box-sizing: border-box;
                display: inline-block;
                clear-after: left;
            }
            button {
                clear: both;
                padding: .3rem .5rem;
                margin: 0 .5rem;
            }
      </style>
  </head>
  <body>
    <% String login = (String)session.getAttribute("user_login"); %>

    <% if (login == null || "".equals(login)) { %>
        <form action="/login" method="POST">
            <label for="login">Login:</label><input type="text" name="login" id="login" autofocus required
                                                    placeholder="Enter your login"><br>
            <label for="password">Password: </label><input type="password" name="password" id="password" required
                                                           placeholder="Enter your password"><br>
            <label for="age">Age: </label><input type="text" name="age" id="age" required placeholder="Enter your age"><br>
            <button type="reset">Reset</button>
            <button type="submit">Sign in</button>
        </form>
    <% } else { %>
        <h1>You are logged in as: <%= login %></h1>
        <br>Click this link to <a href="/login?a=exit">logout</a>
    <% } %>
  </body>
</html>
