package de.htw_berlin.f4.ai.kbe.kurznachrichten;

public class User {

	private String name;
	private String city;
  private Long id;
	
  public User(Long newID, String newName, String newCity) {
    name = newName;
    city = newCity;
    id = newID;
  }
  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
  public Long getID() {
    return id;
  }
}
