<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OO병원 정보</title>
    <!-- base 태그로 프로젝트 컨텍스트 루트 지정 -->
    <base href="${pageContext.request.contextPath}/" />
    
    <link href="resources/css/pretendard.css" rel="stylesheet" />
    <link rel="stylesheet" href="resources/css/hospital_Page_Info.css" />
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpKeyId=oqxhiinnqp&submodules=geocoder"></script>
</head>
<body>
    <div class="container">
        <header>
            <a href="home/home.html">
                <img src="resources/images/logo_img.png" alt="로고 이미지" style="width: 30px" />
            </a>
        </header>
        <h1>OO병원</h1>
        <div class="image-slider" id="hospitalImageSlider" style="position: relative;">
            <img src="resources/images/logo_img.png" alt="병원 사진 1" class="active" />
            <img src="resources/images/logo_img.png" alt="병원 사진 2" />
            <img src="resources/images/logo_img.png" alt="병원 사진 3" />
            <div style="position: absolute; bottom: 10px; right: 10px; color: white; background: rgba(0,0,0,0.5); padding: 5px 10px; border-radius: 5px; font-size: 0.8em;" id="sliderCounter">1 / 3</div>
        </div>

        <section class="info-group">
            <h2>병원 정보</h2>
            <p><strong>주소:</strong> <span>${hospital.address}</span></p>

            <p><strong>전화:</strong> 
                <a href="tel:${hospital.phoneNumber}">${hospital.phoneNumber}</a>
            </p>

            <p><strong>운영 시간:</strong></p>
            <ul>
                <li>${hospital.weekdayOpen} ~ ${hospital.weekdayClose} (점심시간 12:30 - 13:30)</li>
                
                <c:if test="${hospital.isWeekend}">
                    <li>${hospital.weekendOpen} ~ ${hospital.weekendClose}</li>
                </c:if>

                <c:if test="${hospital.isHoliday}">
                    <li>휴무일</li>
                </c:if>
            </ul>

            <p><strong>주차:</strong> 건물 뒤 주차장은 직원 전용 주차장으로<br/>
                환자는 건강을 위해 도보 혹은 대중교통 이용 권장
            </p>
        </section>

        <section class="info-group">
            <h2>진료 과목</h2>
            <ul>
                <c:forEach var="dept" items="${departments}">
                    <li>${dept.name}</li>
                </c:forEach>
            </ul>
        </section>

        <section class="info-group">
            <h2>병원 소개 및 인사말</h2>
            <p>${hospital.greeting}</p>
            <p>${hospital.introduction}</p>
        </section>

        <section class="info-group">
            <h2>의료진 소개</h2>
            <p><strong>원장: <span>${hospital.directorName}</span></strong> (<span>${hospital.directorSpecialty}</span>)</p>
            <p>학력: <span>${hospital.directorEducation}</span></p>
            <p>경력: <span>${hospital.directorExperience}</span></p>
            <p>전문 분야: <span>${hospital.directorField}</span></p>
        </section>

        <section class="info-group">
            <h2>오시는 길</h2>
            <div id="map"></div>

            <div class="category-buttons" style="display: none;">
                <button onclick="filterCategory('정형외과', this)">정형외과</button>
                <button onclick="filterCategory('내과', this)">내과</button>
                <button onclick="filterCategory('이비인후과', this)">이비인후과</button>
            </div>

            <button onclick="moveToMyLocation()" class="location-button" style="display: none;">내 위치</button>

            <p><strong>끝 인사말</strong></p>
            <ul>
                <li>오실때 조심히 오세요</li>
                <li>죽기전에 빨리오세요</li>
            </ul>
        </section>

        <div style="text-align: center; margin-top: 40px;">
            <a href="예약하러가기페이지 링크" class="button">예약 약속하기</a>
        </div>
    </div>

    <script type="text/javascript" src="resources/js/hospital_Page_Info.js"></script>
</body>
</html>
