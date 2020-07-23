public class Comedian {

	protected int comid;
	protected String firstName;
	protected String lastName;
	protected String birthday;
	protected String birthPlace;

	public Comedian() {
		this.comid = 0;
		this.firstName = "null";
		this.lastName = "null";
		this.birthday = "null";
		this.birthPlace = "null";
	}
	
	public Comedian(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Comedian(int comid, String firstName, String lastName, String birthday, String birthPlace) {
		this.comid = comid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.birthPlace = birthPlace;
	}
	
	public Comedian(String firstName, String lastName, String birthday, String birthPlace) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.birthPlace = birthPlace;
	}

	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
}
