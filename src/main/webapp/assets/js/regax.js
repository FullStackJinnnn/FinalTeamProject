function validatePhoneNumber() {
      // 휴대폰 번호에서 하이픈(-)을 제외하고 입력받는 정규식
      const phoneNumberRegex = /^\d{3}\d{3,4}\d{4}$/;

      // 입력된 전화번호 가져오기
      const phoneNumberInput = document.getElementById("phoneNumber").value;

      // 정규식을 사용하여 유효성 검사
      const isValidPhoneNumber = phoneNumberRegex.test(phoneNumberInput);

      // 검증 결과 출력
      if (isValidPhoneNumber) {
        alert("올바른 휴대폰 번호입니다.");
      } else {
        alert("올바르지 않은 휴대폰 번호입니다.");
      }
    }