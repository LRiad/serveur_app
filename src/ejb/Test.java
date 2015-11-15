package ejb;

import javax.ejb.Remote;
import entity.*;
import javax.persistence.*;
import java.util.List;

@Remote
public interface Test{

	public String addUser(String Name);
	public String verifyInsert(String name); 
	public FinalUser findUser(String name);
	public String removeUser(String name);


	public String addBox(String boxName,String name);
	public String removeMailbox(String name);
	public String removeMailbox(int id);
	public MailBox findMailbox(String name);
	public MailBox findMailbox(int id);
	public String affichMailbox(String usr);
	public List<MailBox> selectMailbox(String usr);


	public String sendAMessageToBox ( String senderName,String receiverName, String subject, String body,String mailbox);
	public Message findMessage(int id);
	public String formatMessage(Message m);
	public String readMessage(int id);
	public String removeMessage(int id);
	public String affichMessages(String usr);
	public List <Message> selectMessages(String usr);

}
