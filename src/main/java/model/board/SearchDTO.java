package model.board;

import java.util.ArrayList;

public class SearchDTO {
//input 태그의 checkbox를 사용해서 중복 선택을 할 수 있음으로 리스트 형태로 저장
	private ArrayList<String> companyList = new ArrayList<>();
	private ArrayList<String> productcategoryList = new ArrayList<>();
	private ArrayList<String> stateList = new ArrayList<>();
//게시판 이동시 검색할 가격 초기값을 설정할 변수
	private int price_min;
	private int price_max;
//정렬 데이터가 저장될 변수
	private String checksort;
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
	public int getPrice_min() {
		return price_min;
	}
	public void setPrice_min(int price_min) {
		this.price_min = price_min;
	}
	public int getPrice_max() {
		return price_max;
	}
	public void setPrice_max(int price_max) {
		this.price_max = price_max;
	}
	public String getChecksort() {
		return checksort;
	}
	public void setChecksort(String checksort) {
		this.checksort = checksort;
	}

}
