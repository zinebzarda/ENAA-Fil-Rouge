import {Client} from "./client";

export interface Contact {
  id?: number;
  name: string;
  email: string;
  message: string;
  phone: string
  createdAt?: Date;
}
