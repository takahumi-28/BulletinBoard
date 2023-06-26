<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String userName = request.getParameter("sendUserName");
	String comment	= request.getParameter("sendComment");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>コメント投稿確認画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/tableGridAndWhite.css">
	</head>
	<body>
		<div class="margin10_200">
			<header>
				<ul>
					<li><a href="ThreadListServlet">TOP</a><span>></span></li>
					<li><a href="ThreadDetailServlet">スレッド詳細画面</a><span>></span></li>
					<li><a href="PostCommentServlet">スレッド作成画面</a><span>></span></li>
					<li><span>スレッド作成確認画面</span></li>
				</ul>
				<h1>コメント投稿確認</h1>
			</header>
			<div>
				<p>入力内容を確認してください</p>
				<form action="ConfirmPostCommentServlet" method="post">
					<table>
						<tr>
							<th class="width20 back-white">・ユーザー</th>
							<td class="back-white"><%=userName %></td>
						</tr>
						<tr>
							<th class="back-white">・コメント本文</th>
							<td class="back-white">
								<%=comment %>
								<input type="hidden" name="sendComment" value=<%=comment %>>
							</td>
						</tr>
					</table>
					<button class="create btn floatR margin10" type="submit" value="">
						<img src="/BulletinBoard/images/comment.png">　投稿する
					</button>
				</form>
			</div>
		</div>	
	</body>
</html>