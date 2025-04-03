import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GarageService } from '../../services/garage.service';
import { Vehicle } from '../../interfaces/garageInterfaces';

@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.scss'
})
export class VehiclesComponent {

  // Garage service injection
  private garageService = inject(GarageService);

  // Array to hold vehicle data
  public vehicles: Vehicle[] = [];

  ngOnInit() {
    // Get vehicle details from service.
    this.garageService.getAllVehicles().subscribe((data: any) => {
      // Assign the data to the vehicles array.
      this.vehicles = data;

      // debug
      console.log('Vehicle Details:', this.vehicles);
    });
  }

}
