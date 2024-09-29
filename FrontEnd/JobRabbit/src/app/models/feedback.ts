import {DemandeService} from "./demande-service";

export interface Feedback {
  id?: number;
  note: number;
  commentaire: string;
  dateCreation: string;
  demandeService: DemandeService;
}
