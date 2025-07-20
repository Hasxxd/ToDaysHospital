
package com.todayhospital.dto;

import java.sql.Date; // DB DATE 타입과 매핑되는 java.sql.Date 사용
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalDTO {
    // -------------------------------
    // 병원 기본 정보 (HOSPITAL 테이블과 1:1 매핑)
    // -------------------------------
    private String h_id; // 병원 고유 ID (Primary Key)
    private String h_name; // 병원명
    private String road_address; // 도로명 주소
    private String tel; // 전화번호
    private String hospital_type; // 병원 유형 (예: 내과, 소아과)
    private String main_department; // 주진료과
    private String department_list; // 진료과 전체 목록
    private String night_service_yn; // 야간 진료 여부 (Y/N)
    private String wheelchair_yn; // 휠체어 지원 여부 (Y/N)
    private String hospital_desc; // 병원 설명
    private String image_path; // 병원 이미지 경로
    private Date reg_date; // 병원 등록일
}
