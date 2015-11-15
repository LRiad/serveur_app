package entity;

import javax.persistence.*;
import static javax.persistence.CascadeType.*;

@Entity
public class FinalUser {
	

	private int id;
	private String Name;
	private MailBox box;

	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )	
	@Column(name = "id")	 
	public int getId(){
		return this.id;
	}


	public void setId(int id){
		this.id=id;
	}
	

	@OneToOne()
	@JoinColumn(name="MailBoxId",referencedColumnName="id")
	public MailBox getBox()
	{
		return this.box;
	}
	public void setBox(MailBox box)
	{
		this.box=box;
	} 
	
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	
	
	
	

}
