// import necessary packages
import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Vehicle } from '../interfaces/garageInterfaces'

// Get API URL from environment.ts
const GARAGE_API_BASE = environment.garage_api_base;

@Injectable({
  providedIn: 'root'
})

export class GarageService {
  // inject HttpClient
  private httpClient = inject(HttpClient);

  constructor() { }

  // Returns all vehicles
  getAllVehicles(): Observable<Vehicle[]> {
    let url = `${GARAGE_API_BASE}/vehicle/all`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<Vehicle[]>(url);
  }
  
  // Get all vehicles of a specific make.
  getVehiclesByMake(make: string): Observable<any> {
    // URL with the make parameter.
    const url = `${GARAGE_API_BASE}/vehicle?make=${make}`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<any>(url);
  }

  // Get vehicle by reg.
  getVehicleByReg(reg: string): Observable<Vehicle> {
    // URL with the reg parameter.
    const url = `${GARAGE_API_BASE}/vehicle/one?reg=${reg}`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<Vehicle>(url);
  }
}
