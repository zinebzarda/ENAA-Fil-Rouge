import {Services} from "./services";
import {Personne} from "./personne";
import {ValidateStatus} from "./enums/validate-status.enum";

export interface Prestataire extends Personne {
  domaineExpertise: string;
  disponibilites: string;
  experience: string;
  tel: number;
  status: ValidateStatus;
  services: Services[];
}
