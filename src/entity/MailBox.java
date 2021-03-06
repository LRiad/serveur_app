package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.io.Serializable;
import static javax.persistence.CascadeType.*;

@Entity
public class MailBox implements Serializable{



	private int id;


	private String Name;

	
	private Collection <Message> messages;
	private FinalUser usr;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )	
	@Column(name = "id")	 		
	public int getId(){
		return this.id;
	} 	

	
	public void setId(int id){
		this.id=id;
	}
	
	

 	@Column(name = "Name")
	public String getName(){
	 	return this.Name;
	} 	


	 public void setName(String Name){
	 	this.Name=Name;

	 }

	@OneToMany(cascade=ALL, mappedBy="mailBox")
	public Collection <Message> getMessages(){
		return this.messages;
	}



	public void setMessages(Collection <Message> messages){
		this.messages = messages;
	}


	@OneToOne()
	@JoinColumn(name="finalUserId",referencedColumnName="id")
	public FinalUser getUsr()
	{
		return this.usr;
	}

	public void setUsr(FinalUser usr)
	{
		this.usr=usr;
	}
	









}
