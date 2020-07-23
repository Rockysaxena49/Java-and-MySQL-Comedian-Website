public class ComedianInfo {

	protected Comedian comedianData;
	protected int videoCount;
	protected String comedianName;
	
	ComedianInfo(){};
	
	ComedianInfo(Comedian comedian, int videoCount){
		this.comedianData = comedian;
		this.videoCount = videoCount;
		this.comedianName = comedianData.getFirstName() + " " + comedianData.getLastName();
	}
	
	public int getVideoCount() {
		return this.videoCount;
	}
	
	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}
	
	public Comedian getComedianData() {
		return this.comedianData;
	}
	
	public String getComedianName() {
		return this.comedianName;
	}
}
