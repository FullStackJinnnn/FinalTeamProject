package model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchDTO {
	
    // Input 태그의 checkbox를 사용해서 중복 선택을 할 수 있음으로 리스트 형태로 저장
    private ArrayList<String> companyList = new ArrayList<>();
    private ArrayList<String> productcategoryList = new ArrayList<>();
    private ArrayList<String> stateList = new ArrayList<>();

    // 가격 조건 검색을 저장할 변수
    private int minPrice;
    private int maxPrice;
    
    // 일반 검색을 저장할 변수
    private String searchField;
    private String searchInput;

    // 정렬기준과 방향이 저장될 변수
    private Map<String, String> orderColumnDirection = new HashMap<>();
    
    // 가격정렬 방향을 저장할 변수
    private String priceSort;
    
    // 어떤 카테고리에 있는지 저장하기 위한 변수
    private String category;
    
    // 유저가 작성한 게시글, 내가 작성한 게시글일 경우 카테고리와 상관없이 전부 출력해주기 위한 변수
    private String id;
    
    
    public Map<String, String> getOrderColumnDirection() {
        return orderColumnDirection;
    }

    public void setOrderColumnDirection(Map<String, String> orderColumnDirection) {
        this.orderColumnDirection = orderColumnDirection;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
    	return "SearchDTO [companyList=" + companyList + ", productcategoryList=" + productcategoryList + ", stateList="
    			+ stateList + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", priceSort=" + priceSort
    			+ ", searchField=" + searchField + ", searchInput=" + searchInput + ", id=" + id
    			+ ", orderColumnDirection=" + orderColumnDirection + ", category=" + category + "]";
    }
}
