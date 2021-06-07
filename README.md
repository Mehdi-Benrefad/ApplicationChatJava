# ApplicationChatJava
<h2>L’ÉQUIPE<h2>

<h3>Notre équipe de projet est composée de : <h3>
  
    # MEHDI BENREFAD, Élève Ingénieur en Génie Informatique 
    # ISMAIL LARHCHIM Élève Ingénieur en Génie Informatique 
    # YAHYA CHIBANE Élève Ingénieur en Génie Informatique
    # BADER-EDDINE QODIA Élève Ingénieur en Génie Informatique 
 

  <h3>DESCRIPTION DU PROJET DU POINT DE VUE EXTERNE<h3>
	Notre projet se subdivise en deux grandes partie une partie serveur et une partie client :
 
<h4>La partie client :</h4>
<p> Après avoir ouvert l'application le client a deux choix, il peut envoyer un message textuel ou bien envoyer une image aux autres utilisateurs, cet envoi de message se fait à travers 2 serveurs différents, un serveur de messages textuels, et un serveur d’images.</p>

![1](https://user-images.githubusercontent.com/62174583/121094430-8c965500-c7e6-11eb-8414-887447969c72.PNG)
	  
<p>L’utilisateur écrit un message dans le champ d'entrée et l’envoie aux autres utilisateurs:</p>
	 
![2](https://user-images.githubusercontent.com/62174583/121094590-ca937900-c7e6-11eb-85c0-1e020519ea9c.PNG)

<p>Les autres utilisateurs de l’application reçoivent un message d'auprès l'émetteur.</p>
	  
![3](https://user-images.githubusercontent.com/62174583/121094703-ee56bf00-c7e6-11eb-950d-aad20e6c21f2.PNG)


L’utilisateur peut aussi envoyer une image en cliquant sur le bouton “select image”

![4](https://user-images.githubusercontent.com/62174583/121094750-0595ac80-c7e7-11eb-989d-a6012afe63e3.PNG)

![5](https://user-images.githubusercontent.com/62174583/121094778-10e8d800-c7e7-11eb-80a1-2693005b9020.PNG)

Les autres utilisateurs de l’application reçoivent cette image.

![6](https://user-images.githubusercontent.com/62174583/121094829-252cd500-c7e7-11eb-8a7b-8250b1b21dca.PNG)

Un récepteur peut télécharger l’image en cliquant sur le bouton “Save Image”

![7](https://user-images.githubusercontent.com/62174583/121094880-3675e180-c7e7-11eb-80c9-fb183757fd3f.PNG)
	  
Le récepteur doit insérer le nom de l’image qu'il veut télécharger.

![8](https://user-images.githubusercontent.com/62174583/121095046-7dfc6d80-c7e7-11eb-9726-61c00ac698a3.PNG)

Le récepteur doit confirmer le téléchargement de l’image.

![9](https://user-images.githubusercontent.com/62174583/121095084-910f3d80-c7e7-11eb-8b3d-225d7790c505.PNG)


	  
<h4>La partie serveur</h4>
La partie serveur c’est elle qui s'intéresse à l’envoi des messages aux destinataires, elle se base sur 2 serveurs en parallèles basés sur le multithreading:
<b>Le serveur de messages textuels:</b> il reçoit les messages d'auprès l'utilisateur et puis renvoie ce même message aux autres utilisateurs de l’application, et ceci en suivant les étapes suivantes:
1-création d’un socket serveur
2-création d’une conversation pour chaque utilisateur de l’application.
2-et puis démarrer la conversation (mettre le serveur en écoute de chaque message), le serveur récupère les messages à l'aide d’un objet BufferedReader, et écrit les messages à l'aide d’un objet PrintWriter.

<b>Le serveur d'images:</b> Il suit le même processus que le serveur de messages textuels suit mais cette fois-ci, il utilise un objet BufferedImage et aussi les méthodes ImageIO.read() et ImageIO.write().


