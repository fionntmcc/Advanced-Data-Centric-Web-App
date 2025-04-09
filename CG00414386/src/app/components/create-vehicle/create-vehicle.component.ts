import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VehiclePost } from '../../interfaces/garageInterfaces';
import { GarageService } from '../../services/garage.service';

@Component({
  selector: 'app-create-vehicle',
  imports: [FormsModule, CommonModule],
  templateUrl: './create-vehicle.component.html',
  styleUrl: './create-vehicle.component.scss'
})
export class CreateVehicleComponent {
  vehicle: VehiclePost = {
    reg: '',
    make: '',
    model: ''
  };
  
  submitting = false;
  submitError: string | null = null;
  submitSuccess = false;

  private garageService = inject(GarageService);
  private router = inject(Router);

  ngOnInit(): void {
  }

  createVehicle(form: any) {
    if (form.invalid) {
      return;
    }
    
    this.submitting = true;
    this.submitError = null;
    
    const vehicleData: VehiclePost = {
      reg: this.vehicle.reg,
      make: this.vehicle.make,
      model: this.vehicle.model
    };

    // Call the service to create the vehicle
    this.garageService.postVehicle(vehicleData).subscribe(
      response => {
        console.log('Vehicle created successfully:', response);
        this.submitting = false;
        this.submitSuccess = true;
        // Navigate to the vehicles list after a short delay
        setTimeout(() => {
          this.router.navigate(['/vehicles']);
        }, 1500);
      },
      error => {
        console.error('Error creating vehicle:', error);
        this.submitting = false;
        this.submitError = 'Failed to create vehicle. Please try again.';
      }
    );
  }
}
