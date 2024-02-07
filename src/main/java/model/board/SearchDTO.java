package model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchDTO {
//input 태그의 checkbox를 사용해서 중복 선택을 할 수 있음으로 리스트 형태로 저장
	private ArrayList<String> companyList = new ArrayList<>();
	private ArrayList<String> productcategoryList = new ArrayList<>();
	private ArrayList<String> stateList = new ArrayList<>();
//게시판 이동시 검색할 가격 초기값을 설정할 변수
	private int minPrice;
	private int maxPrice;

	private String searchField;
	private String searchInput;

	// 정렬 데이터가 저장될 변수
	Map<String, String> orderColumnDirection = new HashMap<>();





	public Map<String, String> getOrderColumnDirection() {
		return orderColumnDirection;
	}

	public void setOrderColumnDirection(Map<String, String> orderColumnDirection) {
		this.orderColumnDirection = orderColumnDirection;
	}


	// 어떤 카테고리에 있는지 저장하기 위한 변수
	private String category;

	
	public String getSearchField() {
		return searchField;
	}
	
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<String> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(ArrayList<String> companyList) {
		this.companyList = companyList;
	}

	public ArrayList<String> getProductcategoryList() {
		return productcategoryList;
	}

	public void setProductcategoryList(ArrayList<String> productcategoryList) {
		this.productcategoryList = productcategoryList;
	}

	public ArrayList<String> getStateList() {
		return stateList;
	}


	public void setStateList(ArrayList<String> stateList) {
		this.stateList = stateList;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}





}
