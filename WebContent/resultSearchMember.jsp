<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>検索結果</h2>
<hr>
<table>
	<tr>
		<td width="60px">ID</td>
		<td>氏名</td>
		<td width="100px">郵便番号</td>
		<td width="300px">住所</td>
		<td>電話番号</td>
		<td>メール</td>
		<td width="100px">生年月日</td>
		<td width="100px">入会年月日</td>
		<td width="100px">退会年月日</td>
		<td width="100px">更新</td>
		<td width="100px">退会</td>
	</tr>
	<c:forEach items="${info}" var="infos">
		<tr>
			<td width="60px">${infos.id}</td>
			<td>${infos.familyName}${infos.lastName}</td>
			<td width="100px">${infos.postal}</td>
			<td width="300px">${infos.address}</td>
			<td>${infos.tel}</td>
			<td>${infos.email}</td>
			<td width="100px">${infos.birthday}</td>
			<td width="100px">${infos.joinday}</td>
			<td width="100px">${infos.unsubscribeday}</td>
			<td width="100px">
			<form action="/slibsys/UpdateMemberServlet" method="post">
			<input type="submit" value="更新" class="btn-flat-border">
			<input type="hidden" name="id" value="${infos.id}">
			<input type="hidden" name="action" value="update">
			</form>
			</td>
			<td width="100px">
			<form action="/slibsys/UnsubscribeMemberServlet" method="post">
			<input type="submit" value="退会" class="btn-flat-border">
			<input type="hidden" name="id" value="${infos.id}">
			<input type="hidden" name="action" value="unsubscribe">
			</form>
			</td>
	</c:forEach>

</table>
<p>
	${message }
</p>
