<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String threadTitle 	= request.getParameter("sendThreadTitle");
	String userName		= (String)request.getAttribute("sendLoginedUserName");
	String categoryName = request.getParameter("sendCategoryName");
	String threadDetail = request.getParameter("sendThreadDetail");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>スレッド作成確認画面</title>
		<link rel="stylesheet" type="text/css" href=" /BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href=" /BulletinBoard/css/tableGridAndWhite.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="CreateThreadServlet">スレッド作成画面</a><span>></span></li>
				<li><span>スレッド作成確認画面</span></li>
			</ul>
			<h1>スレッド作成確認</h1>
		</header>
		<div>
			<p>入力内容を確認してください</p>
			<form action="ConfirmCreateThreadServlet" method="post">
				<table>
					<tr>
						<th class="back-white">・タイトル</th>
						<td class="back-white">
							<%=threadTitle %>
							<input type="hidden" name="sendThreadTitle" value=<%=threadTitle %>>
						</td>					
					</tr>
					<tr>
						<th class="back-white">・カテゴリ</th>
						<td class="back-white">
							<%=categoryName %>
							<input type="hidden" name="sendCategoryName" value=<%=categoryName %>>
						</td>
						
					</tr>
					<tr>
						<th class="back-white">・ユーザー名</th>
						<td class="back-white">
							<%=userName %>
						</td>
					</tr>
					<tr>
						<th class="back-white">・スレッド内容</th>
						<td class="back-white">
							<%=threadDetail %>
							<input type="hidden" name="sendThreadDetail" value=<%=threadDetail %>>
						</td>
					</tr>
				</table>
				<input class="create btn floatR margin10" type="submit" value="作成">
			</form>
		</div>
	</body>
</html>