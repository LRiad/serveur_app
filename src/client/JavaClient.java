package client;
import javax.naming.InitialContext;
import javax.ejb.*;
import java.io.*;
import java.util.*;
//import java.util.Collection;
//import java.util.List;
import ejb.Test;
import entity.*;

public class JavaClient{
	public static void clearScreen() 
	{  
 		System.out.print("\033[H\033[2J");  
    		System.out.flush();  
	}  
	public static void main(String args[]){
		Test sb;
		FinalUser fu;
		java.util.Scanner entree =   new java.util.Scanner(System.in);
		BufferedReader entree2 = new BufferedReader(new InputStreamReader(System.in));
		try{

			InitialContext ic= new InitialContext();
			sb = (Test) ic.lookup("ejb.Test");
			boolean app=true;
		System.out.println(sb.addUser("user1"));
		System.out.println(sb.addUser("user2"));
		System.out.println(sb.addBox("user1Box","user1"));
		System.out.println(sb.addBox("user2Box","user2"));
		sb.sendAMessageToBox ("user1","user2","Cours serveur d'application","Tu peux m'envoyer le lien du cours stp.","user2Box");
		sb.sendAMessageToBox ("user2","user1","subject","Oui biensûr, tiens : lien ","user1Box");
		sb.sendAMessageToBox ("user1","user2","subject","Merci","user2Box");
		sb.sendAMessageToBox ("user2","user1","subject","Derien","user1Box");
			while (app)
			{
				clearScreen();
				System.out.println("Choisissez votre catégorie (Tappez 1,2 ou 3):\n1-Administrateur\n2-Simple Utilisateur\n3-Quitter l'application");
				System.out.print("Votre choix : ");
				int usr = entree.nextInt();
				clearScreen();
				if (usr==1)
				{

					boolean b=true;
					while (b)
					{
						clearScreen();
						System.out.println("Que voulez vous faire ? ");
						System.out.println("1-Ajouter un utilisateur ");
						System.out.println("2-Supprimer un utilisateur ");
						System.out.println("3-Retourner au menu principale ");
						System.out.print("Votre choix : ");
						int action = entree.nextInt();
						clearScreen();
						if (action==1)
						{
							System.out.print("Entrez le nom de l'utilisateur : ");
							String util = entree.next();
							System.out.print(sb.addUser(util));
							System.out.println(sb.addBox(util+"Box",util));
							Thread.sleep(2222);

						}else if(action==2)
						{
							System.out.println(sb.affichFinalUser());
							System.out.print("Entrez le nom de l'utilisateur : ");
							String util2 = entree.next();
							System.out.println(sb.removeUser(util2));
							Thread.sleep(2222);
						}else
						{
							b=false;
						
						}
					}


				}else if (usr==2)
				{
					System.out.println(sb.affichFinalUser());
					System.out.print("Quel est votre nom : ");
					String owner = entree.next();
					boolean c=true;
					while (c)
					{
						clearScreen();
						System.out.println("Que voulez vous faire ? ");
						System.out.println("1-Envoyer un message ");
						System.out.println("2-Lire un message ");
						System.out.println("3-Supprimer un message ");
						System.out.println("4-Supprimer tout les messages ");
						System.out.println("5-Supprimer tout les messages lus ");
						System.out.println("6-Ajouter une mailbox ");
						System.out.println("7-Supprimer une mailbox ");
						System.out.println("8-Retourner au menu principale ");
						System.out.print("Votre choix : ");
						int action = entree.nextInt();
						if (action==1)
						{
							clearScreen();
							System.out.print("Entrez le nom du destinataire : ");
							String receiverName = entree2.readLine();
							clearScreen();
							System.out.print("Entrez l'objet du mail : ");
							String subject = entree2.readLine();
							clearScreen();
							System.out.print("Entrez le corps du mail : ");
							String body = entree2.readLine();
							clearScreen();
							System.out.println(sb.affichMailbox(receiverName));
							System.out.print("Entrez le nom de la mailbox : ");
							String mail = entree2.readLine();
							clearScreen();
							System.out.println(sb.sendAMessageToBox (owner,receiverName,subject,body,mail));
							Thread.sleep(2222);
						}else if(action==3)
						{
							clearScreen();
							System.out.println(sb.affichMessages(owner));
							System.out.print("Entrez le numéro du mail : \n");
							int id = entree.nextInt();
							clearScreen();
							System.out.println(sb.removeMessage(id));
							Thread.sleep(2222);						
						}else if (action==2)
						{
							clearScreen();
							System.out.println(sb.affichMessages(owner));
							System.out.print("Entrez le numéro du mail : ");
							int id = entree.nextInt();
							System.out.println(sb.readMessage(id));
							String nu=null;
							while(nu==null)
							{
								System.out.print("Appuyez sur entrer pour continuer !!");
								nu=entree2.readLine();
							}

						}else if (action==4)
						{
							clearScreen();
							System.out.println(sb.deleteAUserMessages(owner));

							Thread.sleep(2222);

						}else if (action==5)
						{
							clearScreen();
							System.out.println(sb.deleteAUserReadMessages(owner));

							Thread.sleep(2222);

						}else if (action==6)
						{
							clearScreen();
							System.out.print("Entrez le nom de la mailbox : ");
							String box = entree.next();
							clearScreen();
							System.out.print(sb.addBox(box,owner));
							Thread.sleep(2222);

						}else if(action==7)
						{
							clearScreen();
							System.out.println(sb.affichMailbox(owner));
							System.out.print("Entrez le numero de la mailbox : \n");
							int nm = entree.nextInt();
							clearScreen();
							System.out.println(sb.deleteAUserMessages(nm));
							System.out.println(sb.removeMailbox(nm));
							Thread.sleep(2222);						
						}else 
						{
							c=false;
						
						}

					}



				}else{System.out.println("Au revoir!!");Thread.sleep(2222);clearScreen();app=false;}


			}


		}catch(Exception e){e.printStackTrace();}

	}




}
