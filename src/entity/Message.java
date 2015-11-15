package entity;
import javax.persistence.*;
import java.util.Date;
import entity.*;
import javax.ejb.*;

@Entity
//@Table(name="Message_Table")
public class Message{
	private int id;
	private String senderName;
	private String receiverName;
	private String subject;
	private String body;
	private boolean alreadyRead;
	private Date sendingDate; 
	private MailBox mailBox;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column(name="Message_Id")
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id=id;
	}
	
	@Column(name = "senderName")
	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	@Column(name = "receiverName")
	public String getReceiverName() {
		return receiverName;
	}


	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}


	@Column(name = "sendingDate")
	public Date getSendingDate() {
		return sendingDate;
	}


	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}


	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	@Column(name = "body")
	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	@Column(name = "alreadyRead")
	public boolean getAlreadyRead(){
		return alreadyRead;
	}
	
	public void setAlreadyRead(boolean a){
		this.alreadyRead=a;
	} 


	@ManyToOne()
	@JoinColumn(referencedColumnName="id")
	public MailBox getMailBox(){
		return mailBox;
	}

	public void setMailBox(MailBox box){
		this.mailBox=box;
	}
	



	public boolean isRead(){
		return this.alreadyRead;
	}
}
