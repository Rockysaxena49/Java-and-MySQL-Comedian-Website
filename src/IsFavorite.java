public class IsFavorite {
	
	protected String username;
	protected int comid;
	
	IsFavorite(){
		this.username = "null";
		this.comid = 0;
	}
	
	IsFavorite(String username, int comid){
		this.username = username;
		this.comid = comid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}
	
}
