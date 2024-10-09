import {Client} from "./client";
import {Services} from "./services";

export interface Review {
  id?: number;
  contentText: string;
  ratingValue: number;
  client: Client;
  services: Services;
  createdAt: string;
}
