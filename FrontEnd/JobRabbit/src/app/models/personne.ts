import {Role} from "./enums/role.enum";

export interface Personne {
  id?: number;
  username: string;
  email: string;
  password: string;
  role: Role;
}
