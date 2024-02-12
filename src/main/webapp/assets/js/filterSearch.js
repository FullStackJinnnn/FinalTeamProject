/**
 * 페이징처리 JS 파일
 */

// 최소 가격 range input 값 표시를 담당하는 변수 및 이벤트 핸들러 등록
const minPriceInput = document.getElementById('minPrice');
const minPriceOutput = document.getElementById('minPriceOutput');

// 최대 가격 range input 값 표시를 담당하는 변수 및 이벤트 핸들러 등록
const maxPriceInput = document.getElementById('maxPrice');
const maxPriceOutput = document.getElementById('maxPriceOutput');

var isFiltered = false;
const category = dataContainer.getAttribute('data-category');

// 필터 및 정렬에 사용되는 변수들 초기화
var minPrice;
var maxPrice;
var selectedCompanies, selectedProductCategories, selectedstates;
var searchField;
var searchInput;
var column;
var priceSort;

// 초기 상태 정렬 방향
const defaultOrderColumnDirection = {
    boardNum: 'DESC',
    title: 'ASC',
    writer: 'ASC',
    boardDate: 'DESC',
    recommendCNT: 'DESC',
    views: 'DESC'
};

// 현재 정렬 방향
var orderColumnDirection = $.extend(true, {}, defaultOrderColumnDirection);

// 선택된 헤더 1개와 방향을 저장하는 객체
var selectedOrderDirection;

// 최소 가격과 최대 가격 초기화 및 이벤트 핸들러 등록
$("#minPrice").val($("#minPrice").attr("min"));
$("#maxPrice").val($("#maxPrice").attr("min"));

if (minPriceInput) {
    minPriceInput.addEventListener('input', function() {
        // 최소 가격이 최대 가격을 초과하면 최대 가격으로 설정
        if (parseInt(minPriceInput.value) > parseInt(maxPriceInput.value)) {
            minPriceInput.value = maxPriceInput.value;
        }
        minPriceOutput.textContent = minPriceInput.value;
    });
}

if (maxPriceInput) {
    maxPriceInput.addEventListener('input', function() {
        // 최대 가격이 최소 가격보다 작으면 최소 가격으로 설정
        if (parseInt(maxPriceInput.value) < parseInt(minPriceInput.value)) {
            maxPriceInput.value = minPriceInput.value;
        }
        maxPriceOutput.textContent = maxPriceInput.value;
    });
}

// 필터링 및 정렬에 사용되는 변수들 업데이트
function updateVariables() {
    minPrice = $("#minPrice").val();
    maxPrice = $("#maxPrice").val();
    searchField = $("#searchField").val();
    searchInput = $("#searchInput").val();

    // 선택된 체크박스들의 값을 배열로 저장
    selectedCompanies = $('input[type=checkbox][name=company]:checked').map(function() {
        return this.value;
    }).get();

    selectedProductCategories = $('input[type=checkbox][name=productcategory]:checked').map(function() {
        return this.value;
    }).get();

    selectedstates = $('input[type=checkbox][name=state]:checked').map(function() {
        return this.value;
    }).get();
}

// 검색 버튼 클릭 이벤트
$("#searchButton").on("click", function() {
    updateVariables();
    filterSearch();
});

// 가격 입력값 변경 이벤트
$("#minPrice, #maxPrice").on("mouseup", function() {
    updateVariables();
    filterSearch();
});

// 체크박스 변경 이벤트
$('input[type=checkbox]').change(function() {
    updateVariables();
    filterSearch();
});

$("#priceAscButton, #priceDescButton").on("click", function() {
    column = null;
    var id = $(this).attr('id');
    priceSort = id === 'priceAscButton' ? 'asc' : 'desc';

    // 테이블 헤더 정렬의 하이라이트 상태 초기화
    $('th.sortable').removeClass('highlight');

    // 가격 정렬 버튼의 하이라이트 상태 초기화 및 적용
    $("#priceAscButton, #priceDescButton").removeClass('highlight');
    $(this).addClass('highlight');

    updateVariables();
    filterSearch();
});

// 테이블 헤더 정렬 클릭 이벤트
$('th.sortable').on('click', function() {
    priceSort = null;
    column = $(this).data('column');

    // 동일 헤더를 클릭하면 정렬 방향을 토글
    if (column === orderColumnDirection.column) {
        orderColumnDirection[column] = orderColumnDirection[column] === 'ASC' ? 'DESC' : 'ASC';
    } else { // 다른 헤더를 클릭하면 초기 상태의 정렬 방향을 사용
        orderColumnDirection = $.extend(true, {}, defaultOrderColumnDirection);
        orderColumnDirection[column] = orderColumnDirection[column];
    }
    orderColumnDirection.column = column;
    // 선택된 컬럼과 그 방향만 보내는 객체 생성
    selectedOrderDirection = {};
    selectedOrderDirection[column] = orderColumnDirection[column];

    // 가격 정렬 버튼의 하이라이트 상태 초기화
    $("#priceAscButton, #priceDescButton").removeClass('highlight');

    // 테이블 헤더 정렬의 하이라이트 상태 초기화 및 적용
    $('th.sortable').removeClass('highlight');
    $(this).addClass('highlight');

    updateVariables();
    filterSearch();
});

// Ajax 요청을 수행하는 함수
function filterSearch() {
    $.ajax({
        type: "GET",
        url: "filterSearch.do",
        data: {
            'minPrice': minPrice,
            'maxPrice': maxPrice,
            'company': selectedCompanies,
            'productcategory': selectedProductCategories,
            'state': selectedstates,
            'category': category, //게시판에 맞는 게시글 출력을 위한 변수
            'searchField': searchField,
            'searchInput': searchInput,
            'id': id, //
            'priceSort': priceSort,
            'jsonOrderColumnDirection': JSON.stringify(selectedOrderDirection)
        },
        traditional: true,
        dataType: 'json',
        success: function(jsonFilteredBoardDatas) {
            if (jsonFilteredBoardDatas != null) { // filterDatas가 존재하는 경우
                window.jsonFilteredBoardDatas = jsonFilteredBoardDatas; // 서버에서 받은 데이터를 변수에 할당
                console.log(jsonFilteredBoardDatas); // 데이터 확인
                isFiltered = true; // 데이터가 존재하므로 isFiltered를 true로 설정
                loadReviewData(1);
            }
        },
        error: function(error) {
            console.log('에러');
            console.log('에러종류: ' + JSON.stringify(error));
            alert("fail");
        }
    });
}

window.onload = function() {
    var headers = document.querySelectorAll("th.sortable");
    headers.forEach(function(header) {
        header.addEventListener("click", function() {
            headers.forEach(function(header) {
                header.classList.remove("highlight");
            });
            this.classList.add("highlight");
        });
    });
};
