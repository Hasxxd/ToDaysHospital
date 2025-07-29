package pwdconv;

import java.security.MessageDigest;

public class PwdChange
{
	/************ 비밀번호 암호화 *******************/
   public static String getPassWordToXEMD5String(String password) { 
       
    MessageDigest md_Md5 = null;  // MD5 해시 알고리즘을 사용하는 해시 객체
      
    try { 
          
    md_Md5 = MessageDigest.getInstance("MD5"); 
      
      } catch (Exception e) { 
          
      e.printStackTrace(); 
       
    }         
 
      
    String resultString; 
      
    byte[] byteArr; 
       
   String temp = ""; 
         
       
    byteArr = md_Md5.digest(password.getBytes());        
    /* password.getBytes() : 문자열 password를 바이트 배열(byte[])로 변환. → 해시 함수는 문자열이 아닌 바이트 단위로 처리.
     * md_Md5.digest(...)	바이트 배열을 입력으로 받아 MD5 해시를 계산하고,그 결과를 바이트 배열로 반환. 
     *  */
 
       
   for (int i = 0; i < byteArr.length; i++) {             
   resultString = "" + Integer.toHexString((int)byteArr[i] & 0x000000ff); 
   /*  (int) byteArr[i]	바이트 값을 정수로 확장 (음수 포함 가능)
    *  Integer.toHexString(...)	16진수 문자열로 변환
    *  바이트 값을 2자리 16진수 문자열로 변환
    */
 
         
  if( resultString.length() < 2 ) {  
               
   resultString = "0" + resultString; 
           
   }                 
    temp = temp + resultString; 
   }  
       
   return temp;   
  }
}
