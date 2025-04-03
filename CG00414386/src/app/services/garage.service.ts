// import necessary packages
import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

// Get API URL from environment.ts
const API_URL = environment.garage_api_url;

@Injectable({
  providedIn: 'root'
})

export class GarageService {
  // inject HttpClient
  private httpClient = inject(HttpClient);

  constructor() { }

  // Returns all vehicles
  getAllVehicles(): Observable<any> {
    let url = `${API_URL}/vehicle/all`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<any>(url);
  }
  
  // Get a specific event by its ID
  getVehiclesByMake(make: string): Observable<any> {
    // Change to the correct Ticketmaster event details endpoint format
    const url = `${API_URL}/vehicle?make=${make}`;
    
    // debug
    console.log('Making API request to:', url);

    return this.httpClient.get<any>(url);
  }
}
