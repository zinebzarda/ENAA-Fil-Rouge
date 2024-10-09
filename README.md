# Application de Réservation de Services 🚀

Bienvenue dans l'**Application de Réservation de Services** ! 🌟 Cette plateforme connecte les utilisateurs avec des prestataires de services comme des plombiers et des mécaniciens, facilitant ainsi la réservation et la gestion de services. Nous avons trois rôles d'utilisateur pour rendre les choses intéressantes : Prestataires de Services, Clients, et Administrateurs. 💼

## Table des Matières 📚
- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)
- [Licence](#licence)
- [Contact](#contact)

## Fonctionnalités 🌈

### Client 👩‍💻:
- Créer un compte et se connecter (vous êtes le patron ici !)
- Rechercher des prestataires de services (plombiers, électriciens, mécaniciens—vous choisissez !)
- Réserver des services selon vos besoins
- Gérer vos réservations (modifier ou annuler à tout moment)

### Prestataire de Services 👨‍🔧:
- Créer un compte et se connecter (parce que vous êtes l'expert !)
- Publier des services et gérer les détails (disponibilité, tarifs, descriptions)
- Consulter et gérer les réservations faites par les clients

### Administrateur 🛡️:
- Gérer les utilisateurs (clients et prestataires)
- Superviser les services proposés par les prestataires
- Gérer les configurations globales de l'application (impressionnant, n'est-ce pas ?)

## Technologies Utilisées 🛠️

Voici ce qui fait fonctionner notre application :

### Backend:
- **Spring Boot** : Pour développer l'API et gérer la logique métier comme un pro
- **Spring Security avec JWT** : Pour garder vos données en sécurité 🔒
- **Maven** : Pour gérer les dépendances
- **MySQL** : Pour stocker toutes les données importantes—utilisateurs, services, réservations
- **Docker** : Pour conteneuriser l'application et faciliter le déploiement 🚢
- **Jenkins** : Pour l'intégration continue (CI) et la livraison continue (CD) ⚙️
- **SonarQube** : Pour analyser la qualité du code et détecter les bugs 🧹
- **JUnit et Mockito** : Pour écrire et exécuter des tests unitaires 🤖

### Frontend:
- **Angular** : Pour créer une interface utilisateur dynamique et réactive
- **Angular Material** : Pour utiliser des composants UI pré-conçus
- **Bootstrap** : Pour concevoir des interfaces utilisateurs responsive 📱
- **CSS** : Pour personnaliser le style et l'apparence de l'application ✨

### Outils de Conception:
- **Figma** : Pour la conception de l'interface utilisateur (UI/UX) 🎨
- **UML** : Pour modéliser et structurer le projet

## Installation ⚙️

### 1. Clonez le dépôt
Commencez par cloner le dépôt Git de l'application sur votre machine locale :

```bash
git clone https://github.com/zinebzarda/ENAA-Fil-Rouge.git
```

### 2. Configurez le backend
Assurez-vous que Docker est installé sur votre machine. Ensuite, naviguez dans le répertoire du backend et lancez les services en utilisant Docker Compose :

```bash
cd backend
docker-compose up --build
```

Cela va démarrer le backend de l'application, incluant la base de données MySQL et l'API Spring Boot.

### 3. Configurez le frontend
Ensuite, configurez et démarrez le frontend. Naviguez dans le répertoire frontend, installez les dépendances et démarrez l'application Angular :

```bash
cd frontend
npm install
ng serve
```

L'application sera accessible à l'adresse suivante : [http://localhost:4200](http://localhost:4200) 💥

## Utilisation 🧑‍🏫

Voici comment utiliser l'application selon les rôles des utilisateurs :

- **Client** : Créez un compte ou connectez-vous, recherchez des services disponibles, faites une réservation, et gérez vos réservations depuis votre tableau de bord.
- **Prestataire de Services** : Connectez-vous pour publier des services, gérez vos services et vos réservations via votre tableau de bord.
- **Administrateur** : Connectez-vous pour gérer les utilisateurs et les services via un tableau de bord d'administration.

## Contribuer 🤝

Les contributions à ce projet sont les bienvenues ! Si vous souhaitez contribuer :

1. Forkez le projet.
2. Créez une nouvelle branche pour vos modifications :
   ```bash
   git checkout -b feature/nom_de_votre_fonctionnalité
   ```
3. Apportez vos modifications et effectuez un commit :
   ```bash
   git commit -m "Description des modifications"
   ```
4. Poussez la branche vers votre dépôt :
   ```bash
   git push origin feature/nom_de_votre_fonctionnalité
   ```
5. Ouvrez une pull request pour que vos modifications soient examinées !

## Licence 📜

Ce projet est sous licence **MIT**. Vous pouvez l'utiliser, le modifier et le distribuer librement. 🦅

## Contact 📧

Si vous avez des questions ou des suggestions concernant l'application, n'hésitez pas à me contacter ! 😊

- **Nom** : Zineb Zarda
- **Email** : [zinabzarda1@gmail.com](mailto:zinabzarda1@gmail.com)

---

Merci d'avoir consulté l'**Application de Réservation de Services** ! Faisons de la réservation de services un jeu d'enfant, un clic à la fois. 😎
