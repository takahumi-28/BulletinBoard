<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー追加画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
	</head>
	<body>
		<div class="margin10_200">
			<header>
				<ul class="padding0">
					<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
					<li><a href="/BulletinBoard/CategoryListServlet">カテゴリー一覧・検索画面</a><span>></span></li>
					<li><span>カテゴリー追加画面</span></li>
				</ul>
				<h1>カテゴリー追加</h1>
			</header>
			<div>
				<form action="ConfirmAddCategoryServlet">
					<table>
						<tr>
							<th class="width30">・カテゴリー名</th>
							<td class="width60">
								<input type="text" name="additionCategory" 
									required="required" size="30">
							</td>
							<td><span>※必須</span></td>
						</tr>
						<tr>
							<th class="width30">・カテゴリー名(カナ)</th>
							<td class="width60">
								<input type="text" name="additionCategoryKana" size="30"
									required="required" pattern="[ァ-ヴ | ー]*">※全角カナのみ
							</td>
							<td><span>※必須</span></td>
						</tr>
					</table>
					<input class="create btn right15 floatR margin10" type="submit" value="確認">
				</form>
			</div>
		</div>
	</body>
</html>