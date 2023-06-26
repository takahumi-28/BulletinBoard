<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>コメント削除確認画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/thread.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="threadDetail.jsp">スレッド詳細画面</a><span>></span></li>
				<li><span>コメント削除確認画面</span></li>
			</ul>
			<h1>コメントの削除確認</h1>
		</header>
		<div class="margin10_200">
			<p>削除内容を確認してください</p>
			<form>
				<div class="comment">
					<p>名前：ダラリーマン 2022/01/23 00:11:34:42</p>
					<p>スレ立て乙</p>
				</div>
			</form>
			<input type="submit" value="削除" class="delete btn floatR margin10">
		</div>
	</body>	
</html>