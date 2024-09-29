import {Client} from "./client";

export interface Contact {
  id?: number;
  message: string;
  client: Client;
}
