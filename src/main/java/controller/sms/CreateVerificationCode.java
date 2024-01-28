package controller.sms;

public class CreateVerificationCode {

	public String createVerificationCode() {

		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder verificationCodeBuilder = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			int randomIndex = (int) (charSet.length * Math.random());
			verificationCodeBuilder.append(charSet[randomIndex]);
		}

		String verificationCode = verificationCodeBuilder.toString();
		System.out.println(verificationCode);

		return verificationCode;
	}

}
