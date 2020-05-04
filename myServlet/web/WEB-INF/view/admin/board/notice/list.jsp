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
		#visual .content-container {
			height: inherit;
			display: flex;
			align-items: center;

			background: url("/images/mypage/visual.png") no-repeat center;
		}
	</style>
</head>

<body>
	<!-- header 부분 -->

	<header id="header">

		<div class="content-container">
			<!-- ---------------------------<header>--------------------------------------- -->

			<h1 id="logo">
				<a href="/index">
					<img src="/images/logo.png" alt="뉴렉처 온라인" />

				</a>
			</h1>

			<section>
				<h1 class="hidden">Header</h1>

				<nav id="main-menu">
					<h1>Main Menu</h1>
					<ul>
						<li><a href="/guide">학습가이드</a></li>

						<li><a href="/course">Select Course</a></li>
						<li><a href="/answeris/index">AnswerIs</a></li>
					</ul>
				</nav>

				<div class="sub-menu">

					<section id="search-form">
						<h1>강좌검색 폼</h1>
						<form action="/course">
							<fieldset>
								<legend>과정검색필드</legend>
								<label>Course Search</label>
								<input type="text" name="q" value="" />
								<input type="submit" value="Search" />
							</fieldset>
						</form>
					</section>

					<nav id="acount-menu">
						<h1 class="hidden">Member Menu</h1>
						<ul>
							<li><a href="/index">HOME</a></li>
							<li><a href="/WEB-INF/view/member/login.html">Login</a></li>
							<li><a href="/WEB-INF/view/member/agree.html">Register</a></li>
						</ul>
					</nav>

					<nav id="member-menu" class="linear-layout">
						<h1 class="hidden">Customer Menu</h1>
						<ul class="linear-layout">
							<li><a href="/member/home"><img src="/images/txt-mypage.png" alt="My Page" /></a></li>
							<li><a href="/notice/list"><img src="/images/txt-customer.png" alt="Help" /></a></li>
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
				<h1>ADMIN PAGE</h1>

				<nav class="menu text-menu first margin-top">
					<h1>My Page</h1>
					<ul>
						<li><a href="/admin/index">Admin Home</a></li>
						<li><a href="/teacher/index.html">Teacher</a></li>
						<li><a href="/index">Student</a></li>
					</ul>
				</nav>

				<nav class="menu text-menu">
					<h1>알림관리</h1>
					<ul>
						<li><a href="/admin/board/notice/list">Announcement</a></li>
					</ul>
				</nav>

			</aside>
			<!-- --------------------------- main --------------------------------------- -->



			<main class="main">
				<h2 class="main title">Announcement</h2>

				<div class="breadcrumb">
					<h3 class="hidden">경로</h3>
					<ul>
						<li>Home</li>
						<li>Help</li>
						<li>Announcement</li>
					</ul>
				</div>

				<div class="search-form margin-top first align-right">
					<h3 class="hidden">공지사항 검색폼</h3>
					<form class="table-form">
						<fieldset>
							<legend class="hidden">공지사항 검색 필드</legend>
							<label class="hidden">검색분류</label>
							<select name="f">
								<option value="title">Title</option>
								<option value="writerId">User</option>
							</select>
							<label class="hidden">Serach</label>
							<input type="text" name="q" value="" />
							<input class="btn btn-search" type="submit" value="Search" />
						</fieldset>
					</form>
				</div>

				<form action="list" method="post">
					<div class="notice margin-top">
						<h3 class="hidden">Announcement List</h3>
						<table class="table">
							<thead>
								<tr>
									<th class="w60">Number</th>
									<th class="expand">Title</th>
									<th class="w100">User</th>
									<th class="w100">Date</th>
									<th class="w60">Hit</th>
									<th class="w40">Open</th>
									<th class="w40">Delete</th>
								</tr>
							</thead>
							<tbody>

							<c:set var="page" value="${(empty param.p) ? 1 : param.p}" />
							<%-- Print the 5 lists --%>
							<%-- <c:set var="startNum" value="${page - (page - 1) % 5}" />--%>
							<c:set var="startNum" value="${page}" />
							<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count / 5), '.')}" />

								<c:forEach var="n" items="${list}" varStatus="st">

									<c:set var="open" value="" />
									<c:if test="${n.pub}">
										<c:set var="open" value="checked" />
									</c:if>

									<tr>
										<td>${n.id}</td>
										<td class="title indent text-align-left"><a href="detail?id=${n.id}&p=${page}">${n.title}</a><span style="font-weight: bold;color: deeppink">[${n.hitCount}]</span></td>
										<td>${n.userid}</td>
										<td><fmt:formatDate value="${n.regdate}" pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatNumber value="${n.hit}" /></td>
										<td><input type="checkbox" name="open-id" value="${n.id}" ${open} ></td>
										<td><input type="checkbox" name="del-id" value="${n.id}" ></td>
									</tr>

								</c:forEach>

							</tbody>
						</table>
					</div>

					<div class="indexer margin-top align-right">
						<h3 class="hidden">Current Page</h3>
						<div><span class="text-orange text-strong">${(empty param.p) ? 1 : param.p}</span> / ${lastNum} pages</div>
					</div>

					<div class="text-align-right margin-top">

						<%-- OpenAll을 위해 어떤 글이 체크가 되어있는지 확인을 해야한다. 체크된 글들의 id들을 hidden으로 넘긴다다 --%>						<c:set var="ids" value="" />
						<c:forEach var="n" items="${list}">
							<c:set var="ids" value="${ids} ${n.id}"/>
						</c:forEach>
						<input type="hidden" name="ids" value="${ids}">



						<input type="submit" class="btn-text btn-default" name="cmd" value="OpenAll">
						<input type="submit" class="btn-text btn-default" name="cmd" value="DelAll">
						<a class="btn-text btn-default" href="/admin/board/notice/reg">Write</a>
					</div>
				</form>

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