<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>検索結果</h2>
<hr>
<table>
	<tr>
		<td style="width: 100px;">貸出ID</td>
		<td style="width: 100px;">会員ID</td>
		<td style="width: 100px;">資料ID</td>
		<td>貸出年月日</td>
		<td>返却年月日</td>
		<td>返却期日</td>
		<td>備考</td>
	</tr>
	<c:forEach items="${infos}" var="info">
		<tr>
			<td>${info.rentalId }</td>
			<td>${info.userId }</td>
			<td>${info.bookStateId }</td>
			<td>${info.rentalRent }</td>
			<td>${info.rentalReturn }</td>
			<td>${info.rentalDeadline }</td>
			<td>${info.comment }</td>
	</c:forEach>
</table>
<p>
	${message }
</p>