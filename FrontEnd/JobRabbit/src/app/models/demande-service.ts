import {Services} from "./services";
import {Client} from "./client";
import {Feedback} from "./feedback";
import {ValidateStatus} from "./enums/validate-status.enum";

export interface DemandeService {
  id?: number;
  dateDemmande: string;
  statut: ValidateStatus;
  client: Client;
  service: Services;
  feedbacks: Feedback[];
}
