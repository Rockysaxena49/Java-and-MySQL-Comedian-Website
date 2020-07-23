import java.util.Date;
import java.util.Calendar;

public class YoutubeVideo {

	protected String url;
	protected String title;
	protected String videoDescription;
	protected int comid;
	protected String postUser;
	protected Date postDate;
	
	YoutubeVideo() {
		this.url = "null";
		this.title = "null";
		this.videoDescription = "null";
		this.comid = 0;
		this.postUser = "null";
	}
	
	//Constructor is used when creating an object with basic info such as a search result object
	YoutubeVideo(String url, String title, String postUser){
		this.url = url;
		this.title = title;
		this.postUser = postUser;
	}
	
	YoutubeVideo(String url, String title, String videoDescription, String postUser, Date postDate){
		this.url = url;
		this.title = title;
		this.videoDescription = videoDescription;
		this.postUser = postUser;
		this.postDate = postDate;
	}
	
	//Constructor used for adding new videos to database
	YoutubeVideo(String url, String title, String videoDescription, int comid, String postUser, Date postDate){
		this.url = url;
		this.title = title;
		this.videoDescription = videoDescription;
		this.comid = comid;
		this.postUser = postUser;
		this.postDate = postDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}

	public String getPostUser() {
		return postUser;
	}

	public void setPostUser(String postUser) {
		this.postUser = postUser;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
