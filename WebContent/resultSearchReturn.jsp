<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>検索結果</h2>
<hr>
<table>
	<tr>
		<td style="width: 100px;">会員ID</td>
		<td style="width: 100px;">貸出ID</td>
		<td style="width: 100px;">資料ID</td>
		<td>貸出年月日</td>
		<td style="font-weight: bold;">返却期日</td>
		<td>備考</td>
		<td style="width: 100px;">返却</td>
	</tr>
	<c:forEach items="${infos}" var="info">
		<tr>
			<td>${info.userId }</td>
			<td>${info.rentalId }</td>
			<td>${info.bookStateId }</td>
			<td>${info.rentalRent }</td>
			<td style="font-weight: bold;">${info.rentalDeadline }</td>
			<td>${info.comment }</td>
			<td>
				<form action="/slibsys/ReturnServlet" method="post" onsubmit="return dial();">
					<input type="submit" value="返却"  class="btn-flat-border"><input
						type="hidden" name="action" value="return"> <input
						type="hidden" name="rentalId" value="${info.rentalId }">
				</form>
			</td>
	</c:forEach>
</table>
<p>${message }</p>

<!-- onclick="dial()" -->

<script>
	function dial() {
		if(window.confirm('この資料を返却します')){
		// 「OK」時の処理
		} else {
		// 「キャンセル」時の処理
			return false;
		}
	}
</script>
