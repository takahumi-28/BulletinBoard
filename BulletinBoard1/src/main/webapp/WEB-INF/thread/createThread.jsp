<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="EsingleThreadsModel.CategoryInfo" %>
<%	ArrayList<CategoryInfo> categories = (ArrayList<CategoryInfo>)request.getAttribute("sendCategories");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>スレッド作成</title>
		<link rel="stylesheet" type="text/css" href=" /BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href=" /BulletinBoard/css/thread.css">
	</head>
	<body>
		<div class="margin10_200">
			<header>
				<ul>
					<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
					<li><span>スレッド作成画面</span></li>
				</ul>
				<h1>スレッド作成</h1>
			</header>
			<div>
				<form action="ConfirmCreateThreadServlet">
					<table>
						<tr>
							<th>・タイトル</th>
							<td>
								<input type="text" name="sendThreadTitle" size="40" 
									placeholder="〇〇スレ" required="required">
							</td>
							<td><span>※必須</span></td>
						</tr>
						<tr>
							<th>・カテゴリ</th>
							<td>
								<select name="sendCategoryName">
									<%for(CategoryInfo category : categories){ %>
										<option value=<%=category.getCategoryName()%>>
											<%=category.getCategoryName() %>
										</option>
									<%} %>
								</select>
							</td>
							<td><span>※必須</span></td>	
						</tr>
						<tr>
							<th>・スレッド内容</th>
							<td>
								<textarea cols="60" rows="20" name="sendThreadDetail"
									placeholder="〇〇について語り合いましょう" required="required"></textarea>
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