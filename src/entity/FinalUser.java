package entity;

import javax.persistence.*;

@Entity(name = "FinalUser")
public class FinalUser {
	

	private int id;
	private String Name;

	
	@Id
	@Column(name = "id")	 
	public long getId(){
		return this.id;
	}


	public void setId(long id){
		this.id=id;
	}
	
	 	
	
	@Column(name = "Name")
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	
	
	
	

}
