import {DemandeService} from "./demande-service";
import {Prestataire} from "./prestataire";

export interface Services {
  id?: number;
  titre: string;
  description: string;
  prix: number;
  image: string;
  prestataire: Prestataire;
  demandes: DemandeService[];
}
