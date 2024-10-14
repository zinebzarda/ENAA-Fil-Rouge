import {Services} from "./services";
import {Client} from "./client";
import {Feedback} from "./feedback";
import {ValidateStatus} from "./enums/validate-status.enum";

export interface DemandeService {
  id?: number;
  dateDemmande: Date;
  client?: any;
  service?: any;
  feedbacks?: any[];
}
