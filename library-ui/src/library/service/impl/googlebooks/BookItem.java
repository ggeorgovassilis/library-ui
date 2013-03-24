package library.service.impl.googlebooks;

public class BookItem {

	private String id;
	private String selfLink;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	private VolumeInfo volumeInfo;

	public VolumeInfo getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(VolumeInfo volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
}
