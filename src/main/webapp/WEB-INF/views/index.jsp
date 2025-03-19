<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>티켓 팝니다</title>
</head>
<body>
    <h1>유저</h1>
    <p>
        <%= request.getAttribute("users") %>
    </p>
    <form method="post" action="<%= request.getContextPath() %>/user">
        <input type="hidden" name="user_id" value="">
        <label>
            이름
            <input name="name" required>
        </label>
        <label>
            이메일
            <input name="email" type="email" required>
        </label>
        <label>
            연락처
            <input name="phone" required>
        </label>
        <button>등록</button>
    </form>
    <h1>콘서트</h1>
    <p>
        <%= request.getAttribute("concerts") %>
    </p>
    <form method="post" action="<%= request.getContextPath() %>/concert">
        <input type="hidden" name="concert_id" value="">
        <label>
            제목
            <input name="title" required>
        </label>
        <label>
            날짜
            <input name="date" required>
        </label>
        <label>
            장소
            <input name="location" required>
        </label>
        <button>등록</button>
    </form>
    <h1>티켓</h1>
    <p>
        <%= request.getAttribute("tickets") %>
    </p>
    <form method="post" action="<%= request.getContextPath() %>/ticket">
        <input type="hidden" name="ticket_id" value="">
        <label>
            좌석번호
            <input name="seat_number" required>
        </label>
        <label>
            가격
            <input name="price" type="number" required>
        </label>
        <label>
            구매일
            <input name="purchase_date" required>
        </label>
        <label>
            콘서트ID
            <input name="concert_id" required>
        </label>
        <label>
            유저ID
            <input name="user_id" required>
        </label>
        <button>등록</button>
    </form>
</body>
</html>
