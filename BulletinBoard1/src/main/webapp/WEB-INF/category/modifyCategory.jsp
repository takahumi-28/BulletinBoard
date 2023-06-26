<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String categoryCode = request.getParameter("sendCategoryCode");
	String categoryName = request.getParameter("sendCategoryName");
	String categoryNameKana = request.getParameter("sendCategoryNameKana");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー修正画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="CategoryListServlet">カテゴリー一覧・検索画面</a><span>></span></li>
				<li><span>カテゴリー修正画面</span></li>
			</ul>
			<h1>カテゴリー修正</h1>
		</header>
		<div class="margin10_200">
			<form action="ConfirmModifyCategoryServlet">
				<table>
					<tr>
						<th class="width30">・カテゴリー名</th>
						<td class="width60">
							<input type="text" name="additionCategory" value=<%=categoryName %> size="30">
						</td>
						<td><span>※必須</span></td>
					</tr>
					<tr>
						<th class="width30">・カテゴリー名(カナ)</th>
						<td class="width60">
							<input type="text" name="additionCategoryKana" value=<%=categoryNameKana %> size="30">※全角のみ
						</td>
						<td><span>※必須</span></td>
					</tr>
				</table>
				<input type="hidden" name="sendCategoryCode" value=<%=categoryCode %>>
				<input class="create btn right15 floatR margin10" type="submit" value="確認">
			</form>
		</div>

	</body>
</html>