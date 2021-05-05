package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User implements Comparable<User> {
		private String firstName, lastName,  email, phoneNumber;
	/**
	 * get user's last name
	 * @return last name
	 * 
	 */
	
	public User(String firstName, String lastName) {
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return firstName+" "+lastName;
	}
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phoneNumber;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email=email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phoneNumber=phone;
	}
	@Override
	public int compareTo(User o) {
		
		return 0;
	}
	
}
