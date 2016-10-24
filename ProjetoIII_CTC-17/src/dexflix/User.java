package dexflix;

public class User {
	
	private int id;
	private String gender;
	private int age;
	private int occupation;
	private String zipCode;
	
	public User (int id, String gender, int age, int occupation, String zipCode) {
		this.id = id;
		this.gender = gender;
		this.age = age;
		this.occupation = occupation;
		this.zipCode = zipCode;
	}
	
	public int getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getOccupation() {
		return occupation;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
}
