package preticketmanager.model;

public class User {
	private int idNumber;
	private String ID;
	private String password;
	private String name;
	private int age;
	private String cellPhone;
	private String email;
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	public int getIdNumber() {
		return idNumber;
	}
}