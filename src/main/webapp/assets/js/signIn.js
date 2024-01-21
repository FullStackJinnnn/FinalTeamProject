
//생년월일
document.addEventListener("DOMContentLoaded", function() {
	var yearDropdown = document.getElementById("year");
	var monthDropdown = document.getElementById("month");
	var dayDropdown = document.getElementById("day");

	// yearDropdown, monthDropdown, dayDropdown이 모두 정의되어 있는지 확인
	if (yearDropdown && monthDropdown && dayDropdown) {
		// 연도 (예: 1900부터 현재년도까지)

		var currentYear = new Date().getFullYear();
		var yearSelect = document.getElementById("year");
		for (var i = currentYear; i >= currentYear - 100; i--) {
			var option = document.createElement("option");
			option.value = i;
			option.text = i;
			yearSelect.add(option);
		}
		  yearSelect.value = 1990;


		// 월 (1부터 12까지)
		for (let month = 1; month <= 12; month++) {
			var option = document.createElement("option");
			option.value = month;
			option.text = month;
			monthDropdown.add(option);
		}

		// 일 (1부터 31까지)
		for (let day = 1; day <= 31; day++) {
			var option = document.createElement("option");
			option.value = day;
			option.text = day;
			dayDropdown.add(option);
		}
	} else {
		console.error("Year, month, or day dropdown element not found.");
	}
});

// 비밀번호 체크와 이용약관 체크박스
function validateForm() {
	var agreeTermsCheckbox = document.getElementById("agreeTerms");
	var personalCheckbox = document.getElementById("personalInformation");
	var locationCheckbox = document.getElementById("locationBasedService");
	var pwInput = document.getElementById("pw");
	var pwCheckInput = document.getElementById("pwCheck");
	var pwError = document.getElementById("pwError");

	// Password confirmation check
	var password = pwInput.value;
	var confirmPassword = pwCheckInput.value;

	if (password !== confirmPassword) {
		pwError.textContent = "비밀번호가 일치하지 않습니다.";
		pwCheckInput.focus();
		return false;
	} else {
		pwError.textContent = "";
	}

	// Check other checkboxes
	if (!agreeTermsCheckbox.checked) {
		alert("이용약관의 동의해주세요.");
		return false;
	} else if (!personalCheckbox.checked) {
		alert("이용약관의 동의해주세요.");
		return false;
	} else if (!locationCheckbox.checked) {
		alert("이용약관의 동의해주세요.");
		return false;
	}

	// Additional form validation logic can be added here if needed

	return true;
};
