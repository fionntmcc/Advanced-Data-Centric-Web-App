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
  public isLoading: boolean = true;
  
  // Property to store error messages
  public error: boolean = false;
  public errorMessage: string = '';

  ngOnInit() {
    // Get vehicle details from service.
    this.garageService.getAllVehicles().subscribe({
      next: (data: any) => {
        // Assign the data to the vehicles array.
        this.vehicles = data;
        this.error = false;
        this.errorMessage = '';
        this.isLoading = false;

        // debug
        // console.log('Vehicle Details:', this.vehicles);
      },
      error: (error) => {
        // console.error('Error fetching vehicle details:', error);
        this.error = true;
        this.isLoading = false;
        this.errorMessage = error.message;
        console.log(this.error);
      }
    });
  }

  updateVehicle(reg: String) {
    window.location.href = `/vehicleDetails/${reg}`;
  }

  createVehicle() {
    window.location.href = '/addVehicle';
  }

}
