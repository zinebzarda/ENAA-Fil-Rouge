# TaskRabbit - Plateforme de Demande de Travailleurs en Ligne 🚀

Bienvenue sur **TaskRabbit** ! Ce projet vise à connecter les utilisateurs à des prestataires de services dans divers domaines, allant du bricolage à la consultation professionnelle. 🛠️💼

## Table des Matières 📚
- [Cahier des Charges](#cahier-des-charges)
- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)
- [Licence](#licence)
- [Contact](#contact)

## Cahier des Charges 📄

### 1. Introduction
Ce document décrit les spécifications pour le développement d'une plateforme en ligne permettant de demander des travailleurs pour divers services. L'objectif est de créer une plateforme robuste et conviviale qui facilite la mise en relation entre les demandeurs de services et les prestataires.

### 2. Problématique
Dans un monde de plus en plus connecté, il existe un besoin croissant de plateformes qui facilitent la recherche et la demande de services à la demande. Les principaux défis à relever sont :
- **Interface Utilisateur** : Offrir une interface intuitive qui simplifie la recherche et la réservation de services.
- **Gestion des Utilisateurs** : Assurer une authentification sécurisée et une gestion efficace des profils utilisateurs.
- **Variété de Services** : Supporter une large gamme de services, allant du bricolage à la consultation professionnelle.
- **Sécurité et Fiabilité** : Garantir la sécurité des transactions et la fiabilité des prestataires.
- **Feedback et Évaluation** : Intégrer un système de feedback pour améliorer la qualité du service et évaluer les prestataires.

### 3. Solutions Proposées

#### 3.1 Authentification et Gestion des Utilisateurs
**Fonctionnalités** :
- Inscription et connexion avec vérification par email ou téléphone.
- Profils utilisateurs détaillés incluant compétences, évaluations, et historique des services.
- Système de gestion des préférences et des notifications.

#### 3.2 Navigation et Recherche de Services
**Fonctionnalités** :
- Recherche avancée avec filtrage par catégorie, localisation, et disponibilité.
- Pages détaillées pour chaque service avec description, tarification, et évaluations.
- Fonctionnalité de favoris et comparaison des services.

#### 3.3 Gestion des Demandes et des Transactions
**Fonctionnalités** :
- Processus de demande de service simple et sécurisé.
- Gestion des calendriers et des disponibilités des prestataires.
- Paiement sécurisé avec intégration de méthodes de paiement variées (Carte bancaire, PayPal, etc.).

#### 3.4 Sécurité et Fiabilité des Prestataires
**Fonctionnalités** :
- Processus de vérification des antécédents et qualifications des prestataires.
- Système de notation et commentaires pour évaluer la qualité des services.
- Assurance et garanties en cas de litiges.

#### 3.5 Support Client et Gestion des Litiges
**Fonctionnalités** :
- Support client multicanal (chat en direct, email, téléphone).
- Mécanismes de résolution de conflits et gestion des litiges.
- Formation et ressources pour aider les utilisateurs à mieux utiliser la plateforme.

### 4. Conclusion
La création de **TaskRabbit** nécessite une planification minutieuse et l'usage de technologies modernes pour offrir une expérience utilisateur optimale. En surmontant les défis évoqués, cette plateforme pourrait devenir un outil indispensable pour la mise en relation rapide et sécurisée entre prestataires et demandeurs de services.

## Technologies Utilisées 🛠️

Voici les technologies que nous utilisons pour rendre cette plateforme fluide et sécurisée :

### Backend
- **Spring Boot** : Pour gérer la logique métier et l'API.
- **Spring Security avec JWT** : Pour assurer une authentification sécurisée.
- **Hibernate** : Pour la gestion des données avec JPA.
- **MySQL/PostgreSQL** : Pour le stockage des données.
- **Docker** : Pour le déploiement avec des conteneurs.
- **JUnit & Mockito** : Pour les tests unitaires.

### Frontend
- **Angular** : Pour construire une interface utilisateur dynamique et réactive.
- **Bootstrap** : Pour le design responsive.
- **CSS** : Pour styliser l'application selon une charte graphique moderne.

### Outils de Conception
- **Figma** : Pour concevoir des interfaces utilisateur conviviales.
- **UML** : Pour modéliser les entités et la structure du projet.

## Installation ⚙️

### 1. Cloner le Dépôt
Cloner le dépôt Git du projet :

```bash
git clone https://github.com/yourusername/taskrabbit.git
cd taskrabbit
```

### 2. Installation du Backend
Assurez-vous d'avoir Docker installé, puis naviguez dans le répertoire backend pour démarrer les services :

```bash
cd backend
docker-compose up --build
```

Cela lancera l'API Spring Boot et la base de données.

### 3. Installation du Frontend
Naviguez dans le répertoire du frontend, installez les dépendances, puis lancez le serveur Angular :

```bash
cd frontend
npm install
ng serve
```

L'application sera disponible à l'adresse [http://localhost:4200](http://localhost:4200).

## Utilisation 🧑‍🏫

- **Clients** peuvent s'inscrire, rechercher des services, faire des demandes et gérer leurs réservations.
- **Prestataires** peuvent publier des services, gérer les demandes et consulter les commentaires des utilisateurs.
- **Administrateurs** peuvent superviser les utilisateurs et gérer les conflits via un tableau de bord d'administration.

## Contribution 🤝

Nous encourageons les contributions à ce projet ! Pour contribuer, suivez ces étapes :
1. Forkez le dépôt.
2. Créez une branche pour vos modifications :
   ```bash
   git checkout -b feature/nom-de-la-fonctionnalite
   ```
3. Effectuez vos modifications, puis effectuez un commit :
   ```bash
   git commit -m "Ajout d'une nouvelle fonctionnalité"
   ```
4. Poussez votre branche :
   ```bash
   git push origin feature/nom-de-la-fonctionnalite
   ```
5. Ouvrez une pull request pour que vos modifications soient examinées.

## Licence 📜

Ce projet est sous licence **MIT**. Vous êtes libre de l'utiliser et de le modifier.

## Contact 📧

Si vous avez des questions ou des suggestions, n'hésitez pas à nous contacter :

- **Nom** : Zineb Zarda
- **Email** : [zinab.zarda@gmail.com](mailto:zinab.zarda@gmail.com)

Merci d'avoir consulté **TaskRabbit** ! Nous espérons que cette plateforme facilitera la mise en relation rapide et sécurisée entre prestataires et clients. 😊
