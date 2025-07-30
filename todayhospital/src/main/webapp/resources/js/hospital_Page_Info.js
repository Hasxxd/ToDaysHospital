// 이미지 슬라이더 (JavaScript)
let slideIndex = 0;
const slides = document.querySelectorAll('#hospitalImageSlider img');
const sliderCounter = document.getElementById('sliderCounter');

function showSlides() {
    for (let i = 0; i < slides.length; i++) {
        slides[i].classList.remove('active');
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    slides[slideIndex - 1].classList.add('active');
    sliderCounter.textContent = `${slideIndex} / ${slides.length}`;
    setTimeout(showSlides, 3000); // 3초마다 슬라이드 변경
}
showSlides(); // 슬라이드쇼 시작

// --- 네이버 지도 API 관련 JavaScript (여기서부터 시작) ---
let map; // 전역 변수로 지도 객체 선언
let markers = []; // 마커들을 저장할 배열

// 병원 데이터 (OO병원을 포함)
// 참고: 이 데이터는 궁극적으로 서버(Controller)에서 동적으로 주입받아야 합니다.
// 현재는 예시를 위해 하드코딩되어 있습니다.
const hospitalData = [
    { name: "OO병원", category: "종합", lat: 36.3501, lng: 127.3850, info: "대전광역시 오류동" }, // 현재 페이지의 병원 정보
    { name: "튼튼 정형외과", category: "정형외과", lat: 36.3501, lng: 127.3850, info: "대전 중구 중앙로 123" },
    { name: "튼튼 내과", category: "내과", lat: 36.3522, lng: 127.3865, info: "대전 서구 둔산로 45" },
    { name: "귀코목 이비인후과", category: "이비인후과", lat: 36.3490, lng: 127.3820, info: "대전 유성구 문화로 67" }
];

window.onload = function () {
    // 이미지 슬라이더는 이미 window.onload 밖에서 시작하도록 변경되었습니다.
    // showSlides(); // 이 줄은 제거하거나 주석 처리해도 됩니다.

    // OO병원의 위도, 경도 가져오기
    const ooHospital = hospitalData.find(h => h.name === "OO병원");
    const initialLat = ooHospital ? ooHospital.lat : 36.3501; // 기본값: OO병원 위치 또는 대전 중심
    const initialLng = ooHospital ? ooHospital.lng : 127.3850;

    map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(initialLat, initialLng), // OO병원 위치로 설정
        zoom: 15 // 초기 줌 레벨 조정
    });

    // OO병원 마커 추가
    if (ooHospital) {
        const position = new naver.maps.LatLng(ooHospital.lat, ooHospital.lng);
        const marker = new naver.maps.Marker({
            position,
            map,
            icon: {
                content: `<div style="font-size:24px; transform: rotate(180deg); color:#ff4757;">⬇️</div>`,
                size: new naver.maps.Size(24, 24),
                anchor: new naver.maps.Point(12, 12)
            },
            title: ooHospital.name
        });

        const infoWindow = new naver.maps.InfoWindow({
            content: `<div style="padding:10px;"><b>${ooHospital.name}</b><br>${ooHospital.info}</div>`
        });

        // 마커 이벤트 리스너
        naver.maps.Event.addListener(marker, 'mouseover', () => infoWindow.open(map, marker));
        naver.maps.Event.addListener(marker, 'mouseout', () => infoWindow.close());
        naver.maps.Event.addListener(marker, 'click', () => {
            alert(`🏥 ${ooHospital.name}\n📍 ${ooHospital.info}`);
        });
        markers.push(marker); // 마커 배열에 추가
    }
};

// 카테고리 필터링 함수 (현재 페이지에서는 사용되지 않을 수 있지만, 확장성을 위해 유지)
function filterCategory(category, button) {
    clearMarkers(); // 기존 마커 제거
    setActiveButton(button); // 버튼 활성화

    const filtered = hospitalData.filter(h => h.category === category);

    filtered.forEach(hospital => {
        const position = new naver.maps.LatLng(hospital.lat, hospital.lng);

        const marker = new naver.maps.Marker({
            position,
            map,
            icon: {
                content: `<div style="font-size:24px; transform: rotate(180deg); color:#ff4757;">⬇️</div>`,
                size: new naver.maps.Size(24, 24),
                anchor: new naver.maps.Point(12, 12)
            },
            title: hospital.name
        });

        const infoWindow = new naver.maps.InfoWindow({
            content: `<div style="padding:10px;"><b>${hospital.name}</b><br>${hospital.info}</div>`
        });

        naver.maps.Event.addListener(marker, 'mouseover', () => infoWindow.open(map, marker));
        naver.maps.Event.addListener(marker, 'mouseout', () => infoWindow.close());
        naver.maps.Event.addListener(marker, 'click', () => {
            alert(`🏥 ${hospital.name}\n📍 ${hospital.info}`);
        });

        markers.push(marker);
    });
}

// 모든 마커 제거 함수
function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
}

// 카테고리 버튼 활성화/비활성화 함수
function setActiveButton(button) {
    document.querySelectorAll('.category-buttons button').forEach(btn => btn.classList.remove('active'));
    if (button) button.classList.add('active');
}

// 내 위치로 이동하는 함수
function moveToMyLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lng = position.coords.longitude;
            const myLocation = new naver.maps.LatLng(lat, lng);

            map.setCenter(myLocation);
            map.setZoom(15);

            // 내 위치 마커 (필요하다면 추가)
            new naver.maps.Marker({
                position: myLocation,
                map: map,
                icon: {
                    content: `<div style="font-size:24px; color:#28a745;">🧍</div>`,
                    size: new naver.maps.Size(24, 24),
                    anchor: new naver.maps.Point(12, 12)
                },
                title: "내 위치"
            });
        }, function() {
            alert("위치 정보를 가져오지 못했습니다. 위치 접근을 허용해주세요.");
        });
    } else {
        alert("이 브라우저에서는 위치 정보 사용이 지원되지 않습니다.");
    }
}