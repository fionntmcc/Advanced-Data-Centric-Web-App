/* 
    Interfaces generated using JSON to TS VSCode extension.
    Exported for use in componenents and services.
*/

export interface Vehicle {
  reg: string;
  make: string;
  model: string;
  owner?: Owner;
  mechanic?: Mechanic;
}

export interface VehiclePost {
  reg: string;
  make: string;
  model: string;
}

export interface Mechanic {
  mid: string;
  name: string;
  salary: number; 
  garage?: Garage;
}

export interface Garage {
  gid: string;
  location: string;
  budget: number;
}

export interface Owner {
  cid: string;
  name: string;
}