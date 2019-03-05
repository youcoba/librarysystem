<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料の貸出</title>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+JP:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:400,900"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<link href="/slibsys/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/header.jsp" />

	<h2>資料の貸出</h2>
	<hr>
	<form action="/slibsys/RentServlet" method="post">
	
	会員ID：<input type="text" name="userId" value="" size="" maxlength="">
	<br>

	
	資料ID：<input type="text" name="bookStateId1" value="" size="" maxlength=""
			placeholder="">
		
	<div id="tb1" class="display">
		資料ID：<input type="text" name="bookStateId2" value="" size="" maxlength="">
		</div>
		
	<div id="tb2" class="display">
		資料ID：<input type="text" name="bookStateId3" value="" size="" maxlength="">
		</div>
		
	<div id="tb3" class="display">
		資料ID：<input type="text" name="bookStateId4" value="" size="" maxlength="">
		</div>
		
	<div id="tb4" class="display">
		資料ID：<input type="text" name="bookStateId5" value="" size="" maxlength="">
		</div>
		

	<p>
		<input type="submit" value="確認画面へ" class="btn-flat-border">
		<input type="hidden" name="action" value="confirm">
	</p>
	</form>
	<button id="btn" class="btn-flat-border">追加</button>
	<jsp:include page="/footer.jsp" />

<script>
(function() { 
	"use strict";
	
	var tb1 = document.getElementById("tb1");
	var tb2 = document.getElementById("tb2");
	var tb3 = document.getElementById("tb3");
	var tb4 = document.getElementById("tb4");
	var btn = document.getElementById("btn");
	var count = 0;
	
	btn.addEventListener('click', function() {
		if(count === 0) {
			tb1.className = "";
			count++;
		} else
		if(count === 1) {
			tb2.className = "";
			count++;
		} else
		if(count === 2) {
			tb3.className = "";
			count++;
		} else
			if(count === 3) {
				tb4.className = "";
				count++;
			} else {
				return;
			}
	})
})();
</script>
</body>
</html>