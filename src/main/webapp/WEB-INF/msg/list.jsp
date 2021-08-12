<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/11
  Time: 4:29 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Page Content-->
<div class="container-fluid p-0">
    <!-- About-->
    <section class="resume-section" id="about">
        <div class="resume-section-content">
            <h1 class="mb-0">
                Clarence ${Receive}
                <span class="text-primary">Taylor</span>
            </h1>
            <div class="subheading mb-5">
                ${Send} <!-- user5로 나오는지만 일단 출력확인 -->
                3542 Berry Street · Cheyenne Wells, CO 80810 · (317) 585-8468 ·
                <a href="mailto:name@email.com">name@email.com</a>
            </div>
            <p class="lead mb-5">I am experienced in leveraging agile frameworks to provide a robust synopsis for high level overviews. Iterative approaches to corporate strategy foster collaborative thinking to further the overall value proposition.</p>
            <div class="social-icons">
                <a class="social-icon" href="#!"><i class="fab fa-linkedin-in"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-github"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-twitter"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-facebook-f"></i></a>
            </div>
        </div>
    </section>
    <hr class="m-0" />
    <!-- Experience-->
    <section class="resume-section" id="experience">
        <div class="resume-section-content">
            <h2 class="mb-5">RECEIVE MESSAGE</h2>

            <c:forEach items="${Receive}" var="dto"> <!-- recieve루프돌려보기 : 이 구역이 여러번 반복되어서 나와야 함 -->
            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0"><a href="/msg/read?mno=${dto.mno}">${dto.mno}</a> - ${dto.whom}</h3>
                    <div class="subheading mb-3">Intelitec Solutions</div>
                    <p>${dto.content}</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">${dto.regdate}</span></div>
            </div>
            </c:forEach>

            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0">Web Developer</h3>
                    <div class="subheading mb-3">Intelitec Solutions</div>
                    <p>Capitalize on low hanging fruit to identify a ballpark value added activity to beta test. Override the digital divide with additional clickthroughs from DevOps. Nanotechnology immersion along the information highway will close the loop on focusing solely on the bottom line.</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">December 2011 - March 2013</span></div>
            </div>
            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0">Junior Web Designer</h3>
                    <div class="subheading mb-3">Shout! Media Productions</div>
                    <p>Podcasting operational change management inside of workflows to establish a framework. Taking seamless key performance indicators offline to maximise the long tail. Keeping your eye on the ball while performing a deep dive on the start-up mentality to derive convergence on cross-platform integration.</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">July 2010 - December 2011</span></div>
            </div>
            <div class="d-flex flex-column flex-md-row justify-content-between">
                <div class="flex-grow-1">
                    <h3 class="mb-0">Web Design Intern</h3>
                    <div class="subheading mb-3">Shout! Media Productions</div>
                    <p>Collaboratively administrate empowered markets via plug-and-play networks. Dynamically procrastinate B2C users after installed base benefits. Dramatically visualize customer directed convergence without revolutionary ROI.</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">September 2008 - June 2010</span></div>
            </div>
        </div>
    </section>

    <!-- Experience-->
    <section class="resume-section" id="education">
        <div class="resume-section-content">
            <h2 class="mb-5">SEND MESSAGE</h2>

            <c:forEach items="${Send}" var="dto"> <!-- recieve루프돌려보기 : 이 구역이 여러번 반복되어서 나와야 함 -->
                <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                    <div class="flex-grow-1">
                        <h3 class="mb-0"><a href="/msg/read?mno=${dto.mno}">${dto.mno}</a> - ${dto.who}</h3>
                        <div class="subheading mb-3">Intelitec Solutions</div>
                        <p>${dto.content}</p>
                    </div>
                    <div class="flex-shrink-0"><span class="text-primary">${dto.opendate}</span></div>
                </div>
            </c:forEach>

        </div>
    </section>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/js/scripts.js"></script>
<%@include file="../includes/footer.jsp"%>
