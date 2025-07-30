package pwdconv;

import java.security.MessageDigest;

public class PwdChange {

    /************ MD5 암호화 *******************/
    public static String getPassWordToXEMD5String(String password) {
        MessageDigest md_Md5 = null;
        try {
            md_Md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultString;
        byte[] byteArr;
        String temp = "";

        byteArr = md_Md5.digest(password.getBytes());

        for (int i = 0; i < byteArr.length; i++) {
            resultString = "" + Integer.toHexString((int) byteArr[i] & 0x000000ff);
            if (resultString.length() < 2) {
                resultString = "0" + resultString;
            }
            temp = temp + resultString;
        }

        return temp;
    }

    /************ SHA-512 암호화 *******************/
    public static String getSha512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("SHA-512 암호화 실패", e);
        }
    }
}