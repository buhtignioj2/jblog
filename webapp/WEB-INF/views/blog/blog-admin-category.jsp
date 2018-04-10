<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<!-- jquery import -->
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>

<!-- execute code -->
<script>

$(function(){ // -> window.load()
	$(".cate-delete").click(function(){
		var rNo= $(this);
	    var no = rNo.attr("data-no")	//$("li").datㅁ("no"); 이렇게 써도됨 (html5) 
	    $.ajax({
	             url : "${pageContext.servletContext.contextPath}/${authUser.id}/api/category/removecategory", //요청할 URL
	             dataType : "json", //응답받을 데이터타입
	             type : "GET", //요청 방식
	             data : {"no" : no}, //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
	             success : function(response) { //요청 및 응답에 성공했을 경우
	            	 rNo.closest("tr").remove();
	             },
	             error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
	                console.error(status + ":" + e);
	             }
	          });
	});
	 
	$("#btn-addcategory").click(function(){
		var name = $("#name").val();
		var desc = $("#desc").val();
		if( name == "" ) {
			alert( "이름을 입력하세요." );
			 $("#name").val("").focus();
			return;
		} else if( desc == "" ) {
			alert( "이름을 입력하세요." );
			 $("#desc").val("").focus();
			return;
		}

		$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id}/api/category/addcategory",
			dataType: "json",
			type: "get",
			data: {"name":name, "description":desc},
			success: function(response){
				$('#admin-cat tr:first').after(
						"<tr>" +
						"<td>" + $('#admin-cat tr').length + "</td>" +
						"<td>" + name + "</td>" +
						"<td>" + 0 + "</td>" +
						"<td>" + desc + "</td>" +
						"<td><img class='cate-delete' data-no=" + response.no+" src='${pageContext.servletContext.contextPath}/assets/images/delete.jpg'></td>" +
						"</tr>"
				)
			},
			error: function( xhr, status, e ) {
				console.error( status + ":" + e );
			}
		});
	});
});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<div id="category">
					<c:import url="/WEB-INF/views/includes/blog-admin-header.jsp"/>
			      	<table class="admin-cat" id="admin-cat">
			      		
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
						
						<c:set var="length" value="${fn:length(categoryList) }" />
						<c:forEach items="${ categoryList}" var="categorylistvo" varStatus="status">
						<tr>
							<td>${length - status.index }</td>
							<td>${categorylistvo.name }</td>
							<td>${totalPost }</td>
							<td>${categorylistvo.description }</td>
							<td><img class="cate-delete" data-no="${categorylistvo.no}" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						</tr>  			
						</c:forEach>
					</table>
	      	
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name" id="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc" id="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><button id="btn-addcategory">카테고리 추가</button></td>
			      		</tr>      		      		
			      	</table> 
		      	</div>
			</div>
		</div>
	</div>
</body>
</html>