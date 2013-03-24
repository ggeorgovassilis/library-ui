package library.service.impl.googlebooks;

import java.util.List;

public class VolumeInfo {
	private String title;
	private String subTitle;
	private List<String> authors;
	private String publisher;
	private List<IndustryIdentifier> industryIdentifiers;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<IndustryIdentifier> getIndustryIdentifiers() {
		return industryIdentifiers;
	}

	public void setIndustryIdentifiers(
			List<IndustryIdentifier> industryIdentifiers) {
		this.industryIdentifiers = industryIdentifiers;
	}
}
