package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.*;
import java.util.Date;
import java.util.List;


@Stateless(name="ejb/Test")
public class TestBean implements Test{
	@PersistenceContext(unitName="pu1")
	private EntityManager em;


/************************************************************************************************/
/*				Fonctions liées aux utilisateurs				*/
/************************************************************************************************/

	
	/* Ajoût d'un utilisateur */
	public String addUser(String userName)
	{
		FinalUser finalUser = new FinalUser();
		finalUser.setName(userName);
		em.persist(finalUser);

		return "Utilisateur "+userName+" a été ajouté avec succès!!\n";
	}
	

	/* Recherche d'un utilisateur */
	public FinalUser findUser(String name)
	{
		Query q = em.createQuery("select u from FinalUser u where u.name= :name");
		q.setParameter("name",name);
		
		return (FinalUser)q.getSingleResult();	
	}


	/* Vérification de l'insertion d'un utilisateur */
	public String verifyInsert(String name)
	{
		FinalUser fu=findUser(name);	
		return fu.getName() + " a bien été ajouté \n";

	};


	/* Suppression d'un utilisateur */	
	public String removeUser(String name)
	{

		FinalUser fu=findUser(name);
		removeMailbox(fu.getBox().getId());		
		FinalUser fu0=em.merge(fu);
		em.remove (fu0);
		return "L'utilisateur "+fu.getName()+" a bien été supprimé!!\n";
	};

	/*Selection de tout les utilisateurs*/
	public List<FinalUser> selectFinalUser()
	{
		Query q = em.createQuery("select m from FinalUser m");
        	return q.getResultList();

	}


	/*Affichage des utilisateurs*/
	public String affichFinalUser()
	{
		String result="Liste des utilisateur :\nNumero\tNom\n";
		List<FinalUser> finalusers=selectFinalUser();
		for(FinalUser usr:finalusers)
		{	

			result+=usr.getId()+"\t"+usr.getName()+"\n";
		} 
		return result;
	}

/************************************************************************************************/
/*				Fonctions liées aux Messages					*/
/************************************************************************************************/


	/* Recherche d'un message */
	public Message findMessage(int id)
	{
		Query q = em.createQuery("select m from Message m where m.id= :id");
		q.setParameter("id",id);
		return (Message)q.getSingleResult();
	}


	/* Formatage d'un message */	
	public String formatMessage(Message m)
	{
		Query q = em.createQuery("update Message m set m.alreadyRead = :alreadyRead where m.id = :id");
		q.setParameter("alreadyRead",true);
		q.setParameter("id",m.getId());
		q.executeUpdate();

		String message="**************************************************************************\n";
		message+="From : " + m.getSenderName()+"\n"+"To : "+m.getReceiverName()+"\n";
		message+="**************************************************************************\n";
		message+="Subject : "+m.getSubject()+"\t\t Date : "+m.getSendingDate()+"\n";
		message+="**************************************************************************\n";
		message+=m.getBody()+"\n";
		message+="**************************************************************************\n";
		return message;

	}

	/* Lecture d'un message */
	public String readMessage(int id)
	{
		return formatMessage(findMessage(id));
	}


	/* Suppression d'un message */
	public String removeMessage(int id)
	{
		Message m=findMessage(id);		
		Message m0=em.merge(m);
		em.remove (m);
		return "Le message a bien été supprimé!!\n";
	}

	/* Envoi d'un message */
	public String sendAMessageToBox ( String senderName,String receiverName, String subject, String body, String mailbox)
	{

		Message message = new Message();
		message.setMailBox(findMailbox(mailbox));
		message.setSenderName(senderName);
		message.setReceiverName(receiverName);
		message.setSendingDate(new Date() );
		message.setSubject(subject);
		message.setBody(body);
		message.setAlreadyRead(false);
		em.persist(message);
		return "Le message a été envoyer avec succès!!";
	}

	/*Selection de tout les messages*/
	public List<Message> selectMessages(String usr)
	{
		Query q = em.createQuery("select m from Message m where m.receiverName = :name");
		q.setParameter("name",usr);
        	return q.getResultList();

	}	

	/*Affichage des messages*/
	public String affichMessages(String usr)
	{
		String result="Liste des messages reçus :\nNumero\tExpediteur\tObjet\tNon lu\n";
		List<Message> messages=selectMessages(usr);
		for(Message msg:messages)
		{	
			String lu="";
			if (msg.getAlreadyRead()){lu="Non";}else{lu="Oui";}
			result+=msg.getId()+"\t"+msg.getSenderName()+"\t"+msg.getSubject()+"\t"+lu+"\n";
		} 
		return result;
	}

	/* Suppression de tout les messages */	
	public String deleteAUserMessages(String receiverName){
		Query q = em.createQuery("delete from Message m where m.receiverName = :receiverName");
		q.setParameter("receiverName",receiverName);
		q.executeUpdate();
		return "Les messages ont été supprimé avec succès";
	}


	/* Suppression des messages lus */
	public String deleteAUserReadMessages(String receiverName){
		Query q = em.createQuery("delete from Message m where m.receiverName = :receiverName and m.alreadyRead = :alreadyRead");
		q.setParameter("receiverName",receiverName);
		q.setParameter("alreadyRead",true);
		q.executeUpdate();
		return "Les messages ont été supprimé avec succès";
	}



	/* Suppression de tout les messages par id box */	
	public String deleteAUserMessages(int mailbox){
		Query q = em.createQuery("delete from Message m where m.mailBox = :mailBox");
		q.setParameter("mailBox",findMailbox(mailbox));
		q.executeUpdate();
		return "Les messages ont été supprimé avec succès";
	}








/************************************************************************************************/
/*				Fonctions liées aux MailBox					*/
/************************************************************************************************/


	/* Ajoût d'une mailbox*/

	public String addBox(String boxName,String name){
		MailBox box = new MailBox();
		box.setName(boxName);
		FinalUser fu=findUser(name);
		box.setUsr(fu);
		fu.setBox(box);
		em.persist(box);	
		return "La box "+box.getName()+" a été ajouter avec succès!!\n";
	}
	
	/* Supression d'une mailbox*/
	public String removeMailbox(String name)
	{
		MailBox m=findMailbox(name);		
		MailBox m0=em.merge(m);
		em.remove (m);
		return "La mailbox "+ name +" a bien été supprimé!!\n";
	}

	/* Supression d'une mailbox par l'Id */
	public String removeMailbox(int id)
	{
		MailBox m=findMailbox(id);

		Query q2 = em.createQuery("select u from FinalUser u where u.box = :box");
		q2.setParameter("box",m);
		FinalUser fu=(FinalUser)q2.getSingleResult();
		fu.setBox(null);

		deleteAUserMessages(id);		
		
		String mm=m.getName();		
		removeMailbox(m.getName());
		return "La mailbox "+mm+" a bien été supprimé!!\n";
	}

	/* Recherche d'une mailbox */
	public MailBox findMailbox(String name)
	{
		Query q = em.createQuery("select m from MailBox m where m.name= :name");
		q.setParameter("name",name);
		return (MailBox)q.getSingleResult();
	}


	/* Recherche d'une mailbox (par l'ID)*/
	public MailBox findMailbox(int id)
	{
		Query q = em.createQuery("select m from MailBox m where m.id= :id");
		q.setParameter("id",id);
		return (MailBox)q.getSingleResult();
	}

	/*Selection de toutes les mailboxs*/
	public List<MailBox> selectMailbox(String usr)
	{
		FinalUser fu=findUser(usr);
		Query q = em.createQuery("select m from MailBox m where m.usr = :usr");
		q.setParameter("usr",fu);
        	return q.getResultList();

	}	

	/*Affichage des mailboxs*/
	public String affichMailbox(String usr)
	{
		String result="Liste des mailboxs :\nNumero\tNom\n";
		List<MailBox> mailboxs=selectMailbox(usr);
		for(MailBox mbx:mailboxs)
		{	

			result+=mbx.getId()+"\t"+mbx.getName()+"\n";
		} 
		return result;
	}


}
