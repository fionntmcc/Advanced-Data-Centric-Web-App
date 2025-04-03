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

  ngOnInit() {
    // Get vehicle details from service
    this.garageService.getAllVehicles().subscribe((data: any) => {
      console.log('Vehicle Details:', data);
    });
  }

}
