<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Coding Ideal Type World Cup</title>
    <style>
        .candidate {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }
        .candidate img {
            max-width: 200px;
            height: auto;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>Coding Ideal Type World Cup</h1>

<% for (int i = 1; i <= 2; i++) { %>
<div class="candidate">
    <h2><%= request.getAttribute("name" + i) %></h2>
    <img src="<%= request.getAttribute("image" + i) %>" alt="<%= request.getAttribute("name" + i) %>">
    <p><%= request.getAttribute("description" + i) %></p>
</div>
<% } %>

<button onclick="alert('결정!');">선택 완료</button>
</body>
</html>