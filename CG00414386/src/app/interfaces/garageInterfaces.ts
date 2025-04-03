
/* 
    Interfaces generated using JSON to TS VSCode extension.
    Exported for use in componenents and services.
*/

export interface Vehicle {
  reg: string;
  make: string;
  model: string;
  owner: Owner;
  mechanic: Mechanic;
}

export interface Mechanic {
  mid: string;
  name: string;
  salary: number;
  garage: Garage;
}

interface Garage {
  gid: string;
  location: string;
  budget: number;
}

interface Owner {
  cid: string;
  name: string;
}