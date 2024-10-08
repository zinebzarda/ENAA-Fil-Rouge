import {Client} from "./client";

export interface Contact {
  id?: number;
  name: string;
  email: string;
  message: string;
  createdAt?: Date;
}
