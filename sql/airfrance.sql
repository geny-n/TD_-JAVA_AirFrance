/*DM : réalisation d un logiciel de gestion des vols 
pour la compagnie air France. 
voici la base de données : Pilotes, Avion, Vol, Aeroports 
*/

drop database if exists AirFrance_SIOA ; 
create database AirFrance_SIOA; 
use AirFrance_SIOA; 

create table pilote ( /*salarier*/
	idpilote int(3) not null auto_increment, 
	nom varchar(50),
	prenom varchar(50),
	email varchar(50),
	mdp varchar(50),
	tel varchar(20),
	nationalite varchar(50),
	dateentree date, 
	nbheuresvol int(5),
	primary key (idpilote)
); 	

create table avion ( /*salarier*/
	idavion int(3) not null auto_increment, 
	designation varchar(50),
	typeavion varchar(50),
	constructeur varchar(50),
	datemisecirculation date, 
	capacite int(3),
	nbheuresvol int(5),
	primary key (idavion)
); 	

create table vol ( /*salarier*/
	idvol int(3) not null auto_increment, 
	designation varchar(50),
	origine varchar(50),
	destination varchar(50),
	datevol date, 
	heurevol int,
	idpilote int(3) not null,
	idavion int(3) not null,
	primary key (idvol),
	foreign key (idpilote) references pilote(idpilote), 
	foreign key (idavion) references avion(idavion)
); 	
create table aeroport ( /*evenement*/
	idaeroport int(3) not null auto_increment, 
	designation varchar(50),
	adresse varchar(50),
	ville varchar(50),
	codepostal varchar(5),
	pays  varchar(50), 
	categorie enum ("national", "international", "fret", "autre"),
	idavion int(3) not null,
	primary key (idaeroport),
	foreign key (idavion) references avion(idavion)
); 	

insert into pilote values (null,"Bournonville","Loic","aze","123","06456123","France","2020/06/15","78"); 
insert into avion values(null, "paris", "boying", "papa", "2020-05-16", "5", "8" );
insert into vol values (null, 'rtu','rty','rty','2020-05-16','45','1','1');
insert into aeroport values (null, "sfsf", "17 rue de paris", "france", "75104", "belge", "national","1");