package net.daum.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String patientLoginId;
    private String patientPw;
}