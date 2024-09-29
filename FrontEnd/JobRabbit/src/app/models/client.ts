import {Personne} from "./personne";
import {DemandeService} from "./demande-service";
import {Contact} from "./contact";

export interface Client extends Personne {
  adresse: string;
  demandes: DemandeService[];
  contacts: Contact[];
}
