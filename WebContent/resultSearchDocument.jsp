<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>検索結果</h2>
<hr>
<table>
	<tr>
		<td>資料ID</td>
		<td>ISBN</td>
		<td>分類コード</td>
		<td>分類</td>
		<td>資料名</td>
		<td>著者名</td>
		<td>出版社</td>
		<td>出版日</td>
		<td>入荷日</td>
		<td>廃棄日</td>
		<td>備考</td>
		<td>更新</td>
		<td>廃棄</td>
	</tr>
	<c:forEach items="${infos}" var="info">
		<tr>
			<td>${info.bookStateId }</td>
			<td>${info.bookInfoIsbn }</td>
			<td>${info.categoryCode }</td>
			<td>${info.categoryName }</td>
			<td>${info.bookInfoName }</td>
			<td>${info.bookInfoAuthor }</td>
			<td>${info.publisher }</td>
			<td>${info.publishDay }</td>
			<td>${info.stockDay }</td>
			<td>${info.disposalDay }</td>
			<td>${info.comment }</td>
			<td>
				<form action="/slibsys/SearchDocumentServlet" method="post">
					<input type="submit" value="更新" class="btn-flat-border"><input
						type="hidden" name="action" value="update"> <input
						type="hidden" name="bookStateId" value="${info.bookStateId }">
					<input type="hidden" name="bookInfoIsbn"
						value="${info.bookInfoIsbn }">
				</form>
			</td>
			<td>
				<form action="/slibsys/SearchDocumentServlet" method="post"
					onsubmit="return dial();">
					<input type="submit" value="廃棄" class="btn-flat-border"><input
						type="hidden" name="action" value="dispose"> <input
						type="hidden" name="bookStateId" value="${info.bookStateId }">
				</form>
			</td>
	</c:forEach>
</table>
<p>${message }</p>

<script>
	function dial() {
		if (window.confirm("この資料を廃棄します")) {
		} else {
			return false;
		}
	}
</script>