package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class MailBox{



	private int id;


	private String Name;

	
	private Collection <Message> messages;


	@Id	
	@Column(name = "id")	 		
	public long getId(){
		return this.id;
	} 	

	
	public void setId(long id){
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


	

	









}