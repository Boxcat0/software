<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
    <html lang = "kr" xmlns:th="http://www.thymeleaf.org">
    <body>
    <div class = "layout">
        <div>
            <th><h1>리뷰 작성</h1></th>
            <form action = "/create" method = "post">
                <sec:csrfInput>
                <input name="title" type="text"><br>
                <textarea name="content"></textarea><br>
                <button type="submit">작성</button>
            </form>
            <a href="/">뒤로 가기</a>
        </div>
    </div>
    </body>
    </html>