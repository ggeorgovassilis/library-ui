package library.service.impl.googlebooks;

import java.util.List;

public class SearchResults {

	private String kind;
	private int totalItems;
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	private List<BookItem> items;

	public List<BookItem> getItems() {
		return items;
	}

	public void setItems(List<BookItem> items) {
		this.items = items;
	}
}
