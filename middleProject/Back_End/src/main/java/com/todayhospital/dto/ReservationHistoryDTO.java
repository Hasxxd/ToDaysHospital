
package com.todayhospital.dto;

import java.sql.Date; // DB DATE 타입과 매핑되는 java.sql.Date 사용
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationHistoryDTO {
    // -------------------------------
    // 예약 기본 정보 (Reservation_History 테이블과 1:1 매핑)
    // -------------------------------
    private String reserv_id; // 예약 고유 번호 (Primary Key)
    private Date reserv_date; // 예약일 (YYYY-MM-DD)
    private String reserv_time; // 예약 시간 (HH:mm 또는 문자열로 표현)
    private String reserv_status; // 예약 상태 (예: 대기, 확정, 취소 등)
    private Date reserv_process_date; // 예약 처리 일자 (승인/거절 시점)
    private Date reserv_cancel_date; // 예약 취소된 일자
    private String reserv_cancel_reason; // 예약 취소 사유
    private String mem_id; // 예약자 ID (회원 테이블 참조)
}
