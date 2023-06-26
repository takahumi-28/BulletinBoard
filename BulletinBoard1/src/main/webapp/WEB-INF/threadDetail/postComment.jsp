<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="EsingleThreadsModel.UserAccountInfo" %>    
<%
	UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>コメント投稿画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
	</head>
	<body>
		<div class="margin10_200">
			<header>
				<ul class="padding0">
					<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
					<li><a href="/BulletinBoard/ThreadDetailServlet">スレッド詳細画面</a><span>></span></li>
					<li><span>コメント投稿画面</span></li>
				</ul>
				<h1>コメントを投稿する</h1>
			</header>
			<div>
				<form action="ConfirmPostCommentServlet">
					<table>
						<tr>
							<th>・ユーザー</th>
							<td>
								<%=loginedUser.getUserName() %>
								<input type="hidden" name="sendUserName" value=<%=loginedUser.getUserName() %>>
							</td>
						</tr>
						<tr>
							<th>・コメント本文</th>
							<td>
								<textarea cols="60" rows="20" name="sendComment" required="required"></textarea>
							</td>
							<td><span>※必須</span></td>
						</tr>
					</table>
					<input class="create btn right15 floatR" type="submit" value="確認">
				</form>
			</div>
		</div>	
	</body>
</html>