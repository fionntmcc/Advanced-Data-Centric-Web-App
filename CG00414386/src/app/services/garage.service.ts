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
  
  // Get a specific event by its ID
  getVehiclesByMake(make: string): Observable<any> {
    // Change to the correct Ticketmaster event details endpoint format
    const url = `${GARAGE_API_BASE}/vehicle?make=${make}`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<any>(url);
  }
}
