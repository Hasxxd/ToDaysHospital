package com.todayhospital.utils.api.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Client_ID : 0ClPxJs0dn9JusY0rKFn
// Client_Secret : BcC23Sl_JR

public class NaverLoginServiceImpl implements NaverLoginService {
    private final String clientId = "0ClPxJs0dn9JusY0rKFn";
    private final String clientSecret = "BcC23Sl_JR";
    private final String redirectUri = "http://localhost:8080/api/member/naver-callback";

    @Override
    public String getAuthorizationUrl(String state) {
        try {
            return "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                    + "&client_id=" + clientId
                    + "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8")
                    + "&state=" + state;
        } catch (Exception e) {
            throw new RuntimeException("Authorization URL 생성 실패", e);
        }
    }

    @Override
    public String getAccessToken(String code, String state) {
        try {
            String url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                    + "&client_id=" + clientId
                    + "&client_secret=" + clientSecret
                    + "&code=" + code
                    + "&state=" + state;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            JSONObject json = (JSONObject) new JSONParser().parse(response.toString());
            return (String) json.get("access_token");

        } catch (Exception e) {
            throw new RuntimeException("AccessToken 요청 실패", e);
        }
    }

    @Override
    public String getUserProfile(String accessToken) {
        try {
            String header = "Bearer " + accessToken;
            URL url = new URL("https://openapi.naver.com/v1/nid/me");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", header);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            return response.toString();

        } catch (Exception e) {
            throw new RuntimeException("사용자 프로필 요청 실패", e);
        }
    }
}
