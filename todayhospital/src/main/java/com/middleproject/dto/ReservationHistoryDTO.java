package com.middleproject.dto;

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
    private String reservationId; // 예약 ID (PK)
    private String patientId; // 환자 ID (FK)
    private String hospitalId; // 병원 ID (FK)
    private Date reservationDate; // 예약일
    private Time reservationTime; // 예약 시간
    private String reservationStatus; // 상태 (예: WAITING, CONFIRMED, CANCELED)
    private String reservationReason; // 증상 또는 요청사항
    private Date reservationCreatedAt; // 예약 생성 시각
    private Date reservationUpdatedAt; // 예약 변경 시각
}
