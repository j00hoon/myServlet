<%@ page import="model.notice" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>
    
    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
            
            background: url("../../images/customer/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
    <!-- header 부분 -->

    <header id="header">
        
        <div class="content-container">
            <!-- ---------------------------<header>--------------------------------------- -->

            <h1 id="logo">
                <a href="/WEB-INF/view/index.jsp">
                    <img src="/images/logo.png" alt="뉴렉처 온라인" />

                </a>
            </h1>

            <section>
                <h1 class="hidden">헤더</h1>

                <nav id="main-menu">
                    <h1>메인메뉴</h1>
                    <ul>
                        <li><a href="/guide">학습가이드</a></li>

                        <li><a href="/course">강좌선택</a></li>
                        <li><a href="/answeris/index">AnswerIs</a></li>
                    </ul>
                </nav>

                <div class="sub-menu">

                    <section id="search-form">
                        <h1>강좌검색 폼</h1>
                        <form action="/course">
                            <fieldset>
                                <legend>과정검색필드</legend>
                                <label>과정검색</label>
                                <input type="text" name="q" value="" />
                                <input type="submit" value="검색" />
                            </fieldset>
                        </form>
                    </section>

                    <nav id="acount-menu">
                        <h1 class="hidden">회원메뉴</h1>
                        <ul>
                            <li><a href="/index">HOME</a></li>
                            <li><a href="/member/login.html">로그인</a></li>
                            <li><a href="/member/agree.html">회원가입</a></li>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">고객메뉴</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
                            <li><a href="/notice/list"><img src="/images/txt-customer.png" alt="고객센터" /></a></li>
                        </ul>
                    </nav>

                </div>
            </section>

        </div>
        
    </header>

	<!-- --------------------------- <visual> --------------------------------------- -->
	<!-- visual 부분 -->
	
	<div id="visual">
		<div class="content-container"></div>
	</div>
	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">

			<!-- --------------------------- aside --------------------------------------- -->
			<!-- aside 부분 -->


			<aside class="aside">
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current"  href="/customer/notice">공지사항</a></li>
						<li><a class=""  href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>
						
					</ul>
				</nav>


	<nav class="menu">
		<h1>협력업체</h1>
		<ul>
			<li><a target="_blank" href="http://www.notepubs.com"><img src="/images/notepubs.png" alt="NotePubs" /></a></li>
			<li><a target="_blank" href="http://www.namoolab.com"><img src="/images/namoolab.png" alt="TreeLab" /></a></li>
						
		</ul>
	</nav>
					
			</aside>
			<!-- --------------------------- main --------------------------------------- -->



		<main class="main">
			<h2 class="main title">Announcement</h2>
			
			<div class="breadcrumb">
				<h3 class="hidden">경로</h3>
				<ul>
					<li>home</li>
					<li>고객센터</li>
					<li>공지사항</li>
				</ul>
			</div>
			
			<div class="search-form margin-top first align-right">
				<h3 class="hidden">공지사항 검색폼</h3>
				<form class="table-form">
					<fieldset>
						<legend class="hidden">공지사항 검색 필드</legend>
						<label class="hidden">검색분류</label>
						<select name="f">
							<option ${(param.f == "title") ? "selected" : ""} value="title">Title</option>
							<option ${(param.f == "user_id") ? "selected" : ""} value="user_id">User</option>
						</select> 
						<label class="hidden">Search</label>
						<input type="text" name="q" value="${param.q}" />
						<input class="btn btn-search" type="submit" value="Search" />
					</fieldset>
				</form>
			</div>
			
			<div class="notice margin-top">
				<h3 class="hidden">Announcement</h3>
				<table class="table">
					<thead>
						<tr>
							<th class="w60">Number</th>
							<th class="expand">Title</th>
							<th class="w100">User</th>
							<th class="w100">Date</th>
							<th class="w60">Hit</th>
						</tr>
					</thead>
					<tbody>

<%--					<%--%>
<%--						List<notice> list = (List<notice>)request.getAttribute("list");--%>
<%--						for (notice n : list) {--%>
<%--							pageContext.setAttribute("n", n);--%>
<%--					%>--%>


					<c:set var="page" value="${(empty param.p) ? 1 : param.p}" />
					<%-- Print the 5 lists --%>
					<%-- <c:set var="startNum" value="${page - (page - 1) % 5}" />--%>
					<c:set var="startNum" value="${page}" />
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count / 5), '.')}" />


					<c:forEach var="n" items="${list}" varStatus="st">

						<tr>
							<td>${n.id}</td>
							<td class="title indent text-align-left"><a href="detail?id=${n.id}&p=${page}">${n.title}</a><span style="font-weight: bold;color: deeppink">[${n.hitCount}]</span></td>
							<td>${n.userid}</td>
							<td><fmt:formatDate value="${n.regdate}" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatNumber value="${n.hit}" /></td>
						</tr>

					</c:forEach>
<%--					<% } %>--%>

					</tbody>
				</table>
			</div>



			
			<div class="indexer margin-top align-right">
				<h3 class="hidden">Current Page</h3>
				<div><span class="text-orange text-strong">${(empty param.p) ? 1 : param.p}</span> / ${lastNum} pages</div>
			</div>

			<div class="margin-top align-center pager">


	<div>

		<c:if test="${startNum - 1 > 0}" >
			<a href="?p=${startNum - 3}&t=&q=" class="btn btn-prev"></a>
		</c:if>
		<c:if test="${startNum - 1 < 0}">
			<span class="btn btn-prev" onclick="alert('There are no previous pages.');">Previous</span>
		</c:if>
		
	</div>

	<ul class="-list- center">
		<c:forEach var="i" begin="0" end="2">
			<c:if test="${lastNum >= startNum + i}">
				<li><a class="-text- ${(page == startNum + i) ? 'orange' : ''} bold" href="?p=${startNum + i}&f=${param.f}&q=${param.q}" >${startNum + i}</a></li>
			</c:if>
		</c:forEach>
				
	</ul>
	<div>

		<c:if test="${startNum + 2 < lastNum}">
			<a href="?p=${startNum + 3}&t=&q=" class="btn btn-next"></a>
		</c:if>
		<c:if test="${startNum + 2 >= lastNum}">
			<span class="btn btn-next" onclick="alert('There are no next pages.');">Next</span>
		</c:if>
		
	</div>
	
			</div>
		</main>
		
			
		</div>
	</div>

    <!-- ------------------- <footer> --------------------------------------- -->



        <footer id="footer">
            <div class="content-container">
                <h2 id="footer-logo"><img src="/images/logo-footer.png" alt="회사정보"></h2>
    
                <div id="company-info">
                    <dl>
                        <dt>주소:</dt>
                        <dd>서울특별시 </dd>
                        <dt>관리자메일:</dt>
                        <dd>admin@newlecture.com</dd>
                    </dl>
                    <dl>
                        <dt>사업자 등록번호:</dt>
                        <dd>111-11-11111</dd>
                        <dt>통신 판매업:</dt>
                        <dd>신고제 1111 호</dd>
                    </dl>
                    <dl>
                        <dt>상호:</dt>
                        <dd>뉴렉처</dd>
                        <dt>대표:</dt>
                        <dd>홍길동</dd>
                        <dt>전화번호:</dt>
                        <dd>111-1111-1111</dd>
                    </dl>
                    <div id="copyright" class="margin-top">Copyright ⓒ newlecture.com 2012-2014 All Right Reserved.
                        Contact admin@newlecture.com for more information</div>
                </div>
            </div>
        </footer>
    </body>
    
    </html>

