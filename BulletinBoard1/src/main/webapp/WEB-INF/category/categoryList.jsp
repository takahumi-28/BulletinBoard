<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>     
<%@ page import="EsingleThreadsModel.CategoryInfo" %>
<%	
	ArrayList<CategoryInfo> categories = (ArrayList<CategoryInfo>)request.getAttribute("sendCategories"); 
	String message = (String)request.getAttribute("sendMessage");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー一覧</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body>
		<div class="margin10_200">
			<header>
				<ul class="padding0">
					<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
					<li><span>カテゴリー一覧・検索画面</span></li>
				</ul>
			<h1>カテゴリー一覧・検索</h1>
			<form action="CategoryListServlet">
				<input class="search" type="text" size="30" placeholder="カテゴリー" name="searchedCategoryName">
				<input class="searchBtn create" type="submit" value="検索">
				<span class="marginL50"></span>
			</form>
			</header>
			<%if(message != null){ %>
				<p class="msg"><%=message %></p>
			<%} %>
			<h4>カテゴリー一覧</h4>
				<table>
				<%for(CategoryInfo category : categories){ %>
					<tr class="border-bottom">
						<td>
							・<%=category.getCategoryName() %>
						<td>
						<td>
							<form action="ModifyCategoryServlet">
								<input type="hidden" name="sendCategoryCode" value=<%=category.getCategoryCode() %>>
								<input type="hidden" name="sendCategoryName" value=<%=category.getCategoryName() %>>
								<input type="hidden" name="sendCategoryNameKana" value=<%=category.getCategoryNameKana() %>>
								<button class="modify btn" type="submit"><img src="/BulletinBoard/images/modify.png"> 修正</button>
							</form>
						</td>
						<td>
							<form action="DeleteCategoryServlet">
								<input type="hidden" name="sendCategoryCode" value=<%=category.getCategoryCode() %>> 
								<input type="hidden" name="sendCategoryName" value=<%=category.getCategoryName() %>>
								<input type="hidden" name="sendCategoryNameKana" value=<%=category.getCategoryNameKana() %>>
								<button class="delete btn" type="submit"><i class="far fa-trash-alt"></i> 削除</button>
							</form>
						</td>	
					</tr>
				<%} %>
				</table>
			<form action="AddCategoryServlet">
				<button class="create btn" type="submit" value=""><img src="/BulletinBoard/images/plus.png"> カテゴリー追加</button> 
			</form>
		</div>	
	</body>
</html>