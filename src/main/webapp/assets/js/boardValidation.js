window.onload = function() {
    var form = document.querySelector('form');
    form.addEventListener('submit', function(event) {
//	input 에 required 가 들어가 있는 태그면 공백만 입력하는 값을 안 받음		.노승현
        var requiredInputs = document.querySelectorAll('input[required], textarea[required]');
        var isEmpty = false;
        requiredInputs.forEach(function(input) {
            if (input.value.trim() === '') {
                isEmpty = true;
            }
        });
        if (isEmpty) {
            event.preventDefault(); // 폼 제출 막기
            alert('필수 입력 필드를 모두 작성해주세요.');
        }
    });
};
