package client;
import javax.naming.InitialContext;
import javax.ejb.*;
import java.util.Collection;
import java.util.List;
import ejb.Test;
import entity.*;

public class JavaClient{
	public static void main(String args[]){
		Test sb;
		FinalUser fu;
	
		try{
		InitialContext ic= new InitialContext();
		sb = (Test) ic.lookup("ejb.Test");
		System.out.println(sb.addUser("Riad"));
		System.out.println(sb.addUser("Nouha"));
		System.out.println(sb.addUser("Anika"));
		System.out.println(sb.addUser("sissou"));
		System.out.println(sb.addBox("TestBox","Riad"));
		System.out.println(sb.addBox("T22Box","Riad"));
		System.out.println(sb.affichMailbox("Riad"));
		System.out.println(sb.removeMailbox("TestBox"));
		System.out.println(sb.affichMailbox("Riad"));
		System.out.println(sb.addBox("TestBox","Anika"));
		System.out.println(sb.verifyInsert("Riad"));
		System.out.println(sb.verifyInsert("Nouha"));
		System.out.println(sb.verifyInsert("Anika"));
		System.out.println(sb.sendAMessageToBox ("Riad","Anika","Missing","Where are you !!","TestBox"));
		System.out.println(sb.sendAMessageToBox ("Riad","Anika","Projet","As tu fini le projet !!","TestBox"));
		System.out.println(sb.readMessage(1));
		System.out.println(sb.affichMessages("Anika"));
		System.out.println(sb.removeMessage(1));

		}catch(Exception e){e.printStackTrace();}

	}




}
