package com.todayhospital.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class ReservationHistoryDTO {
    // -------------------------------
    // 예약 이력 (RESERVATION_HISTORY 테이블)
    // -------------------------------
    private String reservation_id; // 예약 ID (PK)
    private String patient_id; // 환자 ID (FK)
    private String hospital_id; // 병원 ID (FK)
    private Date reservation_date; // 예약일
    private Time reservation_time; // 예약 시간
    private String reservation_status; // 상태 (예: WAITING, CONFIRMED, CANCELED)
    private String reservation_reason; // 증상 또는 요청사항
    private Date reservation_created_at; // 예약 생성 시각
    private Date reservation_updated_at; // 예약 변경 시각
}
