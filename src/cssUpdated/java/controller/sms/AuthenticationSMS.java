package controller.sms;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class AuthenticationSMS {
// api를 받아와 문자를 보내는 클래스
    private final String api_key;
    private final String api_secret;

    public AuthenticationSMS(String api_key, String api_secret) {
        this.api_key = "NCS8QSCV2PUOXICE";
        this.api_secret = "0HWUXNZ9BQV5I602DXDST5FYO6WAOZRW";
    }
    								// 수신자 				// 메시지 내용(인증번호)
    public boolean sendMsg(String recipientPhoneNumber, String verificationCode) {
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", recipientPhoneNumber); // 수신자 전화번호
        params.put("from", "01066221689"); // 발신자 전화번호
        params.put("text", "인증 코드는: " + verificationCode); // 메시지 내용
        params.put("type", "sms"); // 메시지 타입

        try {
            JSONObject JO = (JSONObject) coolsms.send(params);
            // 선택적으로 디버깅을 위해 결과 JSON을 로그로 출력할 수 있습니다.
            System.out.println("SMS 전송 결과: " + JO.toJSONString());
            return true; // 메시지 전송 성공
        } catch (CoolsmsException e) {
            System.out.println("SMS 전송 오류: " + e.getMessage());
            System.out.println("오류 코드: " + e.getCode());
            return false; // 메시지 전송 실패
        }
    }
}
