package application;

public class Person {
	private int ID = 0;
	private String name=null;
	private String street=null;
	private String city=null;
	private String zip=null;
	private String gender=null;
	//ID =4 , name=32 , street=32 , city=20 , zip=5, gender = 1
	public Person(int ID,String name, String street, String city, String zip, String gender) {
		super();
		this.ID=ID;
		this.name = name;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.gender = gender;
	}
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID=ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
