Notre projet consiste à aider les étudiants à obtenir plus d’informations sur les universités et à en savoir plus sur eux comme par exemple nous avons

Gestion Université:
Notre cible peut consulter la liste des universités.
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des universités ( + Upload image)
Modifier des Universités 
Supprimer des Université
Et il peut faire des recherches avancés pour faciliter la gestion


Gestion Foyer:
Nos utilisateurs cibles peuvent parcourir la liste des foyers et bénéficient de la fonction de filtrage par capacité pour simplifier la gestion.
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des foyers( + Upload image)
Modifier des foyers
Supprimer des foyers

Gestion Chambre:
Notre cible peut consulter la liste des chambres.
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des chambres( + Upload image)
Modifier des chambres
Supprimer des chambres
Et ilya des statistiques détaillées sont accessibles dans la section tableau de bord, offrant ainsi une meilleure compréhension du comportement des chambres.

Gestion Restaurant
Les utilisateurs ciblés ont la possibilité d'examiner la liste des restaurants. De plus, ils peuvent améliorer leur connaissance des détails de chaque restaurant en scannant un QR Code qui les redirige vers les informations spécifiques de l'établissement.
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des restaurants( + Upload image)
Modifier des restaurants
Supprimer des restaurants



Gestion spécialité:
Les utilisateurs ciblés peut voir la liste des différents spécialités et télécharger un pdf
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des spécialités( + Upload image)
Modifier des spécialités
Supprimer des spécialités


Gestion actualité:
Les utilisateurs ciblés peut voir la liste des différents actualités et il peut partager Facebook (Share Facebook)
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des actualités ( + Upload image)
Modifier des actualités 
Supprimer des actualités 

Gestion Evenement:
Les utilisateurs ciblés peuvent consulter la liste des différents événements et les trier selon leurs préférences.
En ce qui concerne la partie ADMIN (backend), les fonctionnalités incluent:
L’ajout des événements ( + Upload image)
Modifier des événements 
Supprimer des événements 


En dernier lieu, nous avons accompli l'intégration finale(Club).










Guide de configuration et de déploiement du projet
Ce manuel exhaustif vous accompagnera à travers les étapes indispensables afin de dupliquer, élaborer et mettre en service le projet à l'aide de Docker Compose. 

Veuillez suivre scrupuleusement les directives ci-dessous afin d'assurer le bon fonctionnement du projet. Prérequis Avant de débuter, assurez-vous que les éléments logiciels suivants sont installés sur votre système : 
Git
Maven
Docker 
Docker Compose
Angular

Étape 1 : Cloner le projet Begin en clonant le référentiel de projet sur votre machine locale à l’aide de Git : Dans votre terminal de commande, exécutez les commandes suivantes :
git clone https://github.com/IkramHamel/4TWIN5-Educal.git


Étape 2 : Créez le projet Utilisez Maven pour créer le projet et assurez-vous d’être dans le répertoire racine du projet :

Dans votre terminal de commande, exécutez les commandes suivantes :

mvn clean
install 
npm/yarn install

Pour chaque projet dans le référentiel, créez des images Docker. Accédez au répertoire de chaque projet et lancez la génération d’images Docker 
Dans votre terminal de commande, utilisez la commande suivante :

docker compose build




Étape 4 : Docker Compose Revenez au répertoire racine du projet et utilisez Docker Compose pour lancer les services. Le fichier docker-compose.yml fourni décrit les services et leurs configurations :

Dans votre terminal de commande, exécutez :

docker-composer up

=> Cette commande lancera tous les services décrits dans le fichier docker-compose.yml en mode détaché.

Une fois que les services sont opérationnels, vous pouvez tester votre application. Selon votre projet et ses services, ouvrez un navigateur Web ou utilisez des outils tels que Postman ou bien swagger pour interagir avec l’application.

Accédez aux services si nécessaire en utilisant l’URL appropriée, généralement http://localhost:8082, car la passerelle api Gateaway est configurée sur ce port.

Pour lancer le projet frontend, exécuter la commande suivante :

 ng serve

Étape 6 : Arrêt et Purge
Pour interrompre les services et les éliminer, utilisez la commande Docker Compose suivante :

Dans votre terminal de commande, exécutez :

docker-compose down

=>Cela mettra fin et supprimera tous les conteneurs tels que définis dans le fichier docker-compose.yml.

C'est tout ! Vous avez achevé avec succès le processus de duplication, de compilation et de déploiement du projet en utilisant Docker Compose.


