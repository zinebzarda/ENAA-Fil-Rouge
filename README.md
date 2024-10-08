# Application de Réservation de Services

Bienvenue dans l'Application de Réservation de Services ! Cette plateforme permet aux utilisateurs de se connecter avec des prestataires de services tels que des plombiers et des mécaniciens, facilitant ainsi la réservation et la gestion de services. L'application prend en charge trois rôles d'utilisateur : **Prestataire de Services**, **Client**, et **Administrateur**.

## Table des Matières

- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)
- [Licence](#licence)
- [Contact](#contact)

## Fonctionnalités

- **Rôles Utilisateurs :**
  - **Prestataire de Services :** Peut lister ses services, gérer les réservations et mettre à jour son profil.
  - **Client :** Peut parcourir les services, réserver des rendez-vous et gérer ses réservations.
  - **Administrateur :** Peut gérer les utilisateurs, superviser les listings de services et gérer les configurations du système.

- **Authentification & Sécurité :** 
  - Implémentée avec Spring Boot Security et JWT pour une authentification sécurisée des utilisateurs.

- **Gestion des Services :** 
  - Les clients peuvent rechercher et filtrer les services disponibles, et les prestataires de services peuvent gérer leurs listings.

- **Design Réactif :**
  - Développé avec Angular et Bootstrap, ainsi qu'Angular Material pour une interface conviviale.

## Technologies Utilisées

- **Backend :**
  - Spring Boot
  - Spring Security (JWT)
  - Maven
  - MySQL
  - Docker
  - Jenkins
  - SonarQube
  - JUnit
  - Mockito

- **Frontend :**
  - Angular
  - Angular CLI
  - Bootstrap
  - CSS
  - Angular Material

- **Outils de Design :**
  - Figma pour la conception UI/UX
  - UML pour la modélisation de l'application

## Installation

Pour exécuter l'application localement, suivez ces étapes :

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/yourusername/service-booking-app.git
   cd service-booking-app
