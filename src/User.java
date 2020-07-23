
public class User {
	
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected char gender;
	protected int age;

	public User() {
		this.username = "null";
		this.password = "null";
		this.firstName = "null";
		this.lastName = "null";
		this.gender = 'n';
		this.age = 0;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstName, String lastName, int age) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getAge() {
		return Integer.toString(this.age);
	}
}
