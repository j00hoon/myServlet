<!DOCTYPE html>
<html>

<head>
    <title>myServlet Hello</title>
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
                        <li><a href="/guide">Guide Line</a></li>

                        <li><a href="/course">Select Course</a></li>
                        <li><a href="/answeris/index">AnswerIs</a></li>
                    </ul>
                </nav>

                <div class="sub-menu">

                    <section id="search-form">
                        <h1>Search Course</h1>
                        <form action="/course">
                            <fieldset>
                                <legend>과정검색필드</legend>
                                <label>Search Course</label>
                                <input type="text" name="q" value="" />
                                <input type="submit" value="Search" />
                            </fieldset>
                        </form>
                    </section>

                    <nav id="acount-menu">
                        <h1 class="hidden">Member Menu</h1>
                        <ul>
                            <li><a href="/index">HOME</a></li>



                            <li>
                                <form action="/logout" method="post">
                                    <input type="hidden" name="" value="" />
                                    <input type="submit" value="Log Out"
                                        style="border:none;background: none;vertical-align: middle;font-size: 10px;color:#979797;font-weight: bold;" />

                                </form>
                            </li>

                            <li><a href="/member/agree">Register</a></li>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">Customer Menu</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="My Page" /></a></li>
                            <li><a href="/admin/board/notice/list"><img src="/images/txt-customer.png" alt="Help" /></a></li>
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
                        <li><a href="/teacher/index.html">Teacher Page</a></li>
                        <li><a href="/index">Student Page</a></li>
                    </ul>
                </nav>

                <nav class="menu text-menu">
                    <h1>Management</h1>
                    <ul>
                        <li><a href="/admin/board/notice/list">Announcement</a></li>
                    </ul>
                </nav>

            </aside>
            <!-- --------------------------- main --------------------------------------- -->




            <main>
                <h2 class="main title">Announcement Register</h2>

                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>Management</li>
                        <li>Announcement</li>
                    </ul>
                </div>

                <form method="post" action="reg" enctype="multipart/form-data">
                    <div class="margin-top first">
                        <h3 class="hidden">Create Announcement</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>Title</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="title" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>File</th>
                                    <td colspan="3" class="text-align-left text-indent"><input type="file"
                                           name="file" /> </td>
                                </tr>
                                <tr>
                                    <th>File</th>
                                    <td colspan="3" class="text-align-left text-indent"><input type="file"
                                                                                               name="file" /> </td>
                                </tr>
                                <tr class="content">
                                    <td colspan="4"><textarea class="content" name="content"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-align-right"><input class="vertical-align" type="checkbox" id="open" name="open" value="true"><label for="open" class="margin-left">Open</label> </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="Register" />
                        <a class="btn-text btn-cancel" href="/admin/board/notice/list">Cancel</a>
                    </div>
                </form>

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