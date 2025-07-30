package net.daum.controller;

import java.io.PrintWriter;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.daum.dto.MemberDTO;
import net.daum.service.MemberService;
import net.daum.service.MemberServiceImpl;
import pwdconv.PwdChange;

public class PwdFindOKController implements Action {

    /*
     * 비밀번호 찾기 결과 컨트롤러
     * - 입력받은 ID, 이름으로 사용자 조회
     * - 존재할 경우: 임시 비밀번호 생성 및 DB 반영
     * - 실패할 경우: 이전 페이지로 되돌아감
     */

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("pwd_id");
        String userName = request.getParameter("pwd_name");

        MemberDTO input = new MemberDTO();
        input.setPatientLoginId(userId);
        input.setPatientName(userName);

        MemberService memberService = new MemberServiceImpl();
        MemberDTO foundMember = memberService.pwdMember(input);  // ID + 이름 기준 사용자 조회

        ActionForward forward = new ActionForward();

        if (foundMember == null) {
            out.println("<script>");
            out.println("alert('회원정보를 찾을 수 없습니다!');");
            out.println("history.back();");
            out.println("</script>");
            return null;
        }

        // 임시 비밀번호 생성 및 암호화
        String tempPassword = Integer.toString(new Random().nextInt(100000));
        input.setPatientPw(PwdChange.getPassWordToXEMD5String(tempPassword));  // MD5 암호화

        // DB 비밀번호 업데이트
        memberService.updatePwd(input);

        // 결과 전달 및 페이지 이동
        request.setAttribute("ran_pwd", tempPassword);
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/member/pwd_find_ok.jsp");
        return forward;
    }
}