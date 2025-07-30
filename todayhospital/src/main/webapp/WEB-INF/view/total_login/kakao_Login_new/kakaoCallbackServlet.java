// package 패키지명변경.package;

// import java.io.*;
// import java.net.*;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import javax.servlet.*;
// import javax.servlet.http.*;
// import org.json.JSONObject;

// public class KakaoCallbackServlet extends HttpServlet {
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         String code = req.getParameter("code");
//         String clientId = "카카오_REST_API_KEY";
//         String redirectUri = "http://localhost:8080/프로젝트명/kakaoCallback";

//         // 1. 토큰 요청
//         URL url = new URL("https://kauth.kakao.com/oauth/token");
//         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//         conn.setRequestMethod("POST");
//         conn.setDoOutput(true);

//         String params = "grant_type=authorization_code"
//             + "&client_id=" + clientId
//             + "&redirect_uri=" + redirectUri
//             + "&code=" + code;

//         OutputStream os = conn.getOutputStream();
//         os.write(params.getBytes());
//         os.flush();
//         os.close();

//         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//         StringBuilder responseStr = new StringBuilder();
//         String line;
//         while ((line = br.readLine()) != null) {
//             responseStr.append(line);
//         }
//         br.close();

//         JSONObject tokenJson = new JSONObject(responseStr.toString());
//         String accessToken = tokenJson.getString("access_token");

//         // 2. 사용자 정보 요청
//         URL userUrl = new URL("https://kapi.kakao.com/v2/user/me");
//         HttpURLConnection userConn = (HttpURLConnection)userUrl.openConnection();
//         userConn.setRequestMethod("GET");
//         userConn.setRequestProperty("Authorization", "Bearer " + accessToken);

//         BufferedReader userBr = new BufferedReader(new InputStreamReader(userConn.getInputStream()));
//         StringBuilder userStr = new StringBuilder();
//         while ((line = userBr.readLine()) != null) {
//             userStr.append(line);
//         }
//         userBr.close();

//         JSONObject userJson = new JSONObject(userStr.toString());
//         JSONObject kakaoAccount = userJson.getJSONObject("kakao_account");

//         // SOCIAL_LOGIN 테이블 컬럼에 맞게 변수 매핑
//         String socialProvider = "KAKAO";
//         String socialId = userJson.get("id").toString(); //  PK
//         String socialEmail = kakaoAccount.optString("email", "");
//         String socialProfileJson = userJson.toString();
//         String socialCreatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); //로그인시간이 넘어와서 넣었음. 이거 빼고 무관해보임

//         // (선택) PATIENT_ID 매핑은 별도의 회원테이블/연동에서 처리
//         String patientId = null; // 연동된 기존 회원이 있다면 해당 값으로 세팅

//         // JSP로 전달
//         req.setAttribute("socialId", socialId);
//         req.setAttribute("socialProvider", socialProvider);
//         req.setAttribute("socialEmail", socialEmail);
//         req.setAttribute("socialProfileJson", socialProfileJson);
//         req.setAttribute("socialCreatedAt", socialCreatedAt);
//         req.setAttribute("patientId", patientId);

//         RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/kakao_callback_result.jsp");
//         rd.forward(req, resp);
//     }
// }

// //json 필요, 에러처리주의해야해요.
