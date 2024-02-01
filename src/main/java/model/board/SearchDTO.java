package model.board;

import java.util.ArrayList;

public class SearchDTO {

private ArrayList<String> searchList = new ArrayList<>();
	 private int price_min;
	 private int price_max;
	public ArrayList<String> getSearchList() {
		return searchList;
	}
	public void setSearchList(ArrayList<String> searchList) {
		this.searchList = searchList;
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
}
