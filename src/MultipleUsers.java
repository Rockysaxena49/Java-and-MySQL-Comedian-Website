public class MultipleUsers {

	protected String userOne;
	protected String userTwo;
	
	public MultipleUsers() {};
	
	public MultipleUsers(String userOne, String userTwo) {
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

	public String getUserOne() {
		return userOne;
	}

	public void setUserOne(String userOne) {
		this.userOne = userOne;
	}

	public String getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(String userTwo) {
		this.userTwo = userTwo;
	}
	
}
