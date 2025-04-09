import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { VehiclePost } from '../../interfaces/garageInterfaces';
import { GarageService } from '../../services/garage.service';

@Component({
  selector: 'app-create-vehicle',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './create-vehicle.component.html',
  styleUrl: './create-vehicle.component.scss'
})
export class CreateVehicleComponent {

  // No ngOnInit necessary, no data fetched on init.

  // Instantiate a vehicle object with default values.
  // This is bound to form inputs.
  vehicle: VehiclePost = {
    reg: '',
    make: '',
    model: ''
  };
  
  public submitting = false;
  public submitError: string | null = null;
  public submitSuccess = false;

  // Inject the GarageService for API calls.
  private garageService = inject(GarageService);
  private router = inject(Router);

  // Form submission handler.
  createVehicle(form: any) {
    if (form.invalid) {
      return;
    }
    
    this.submitting = true;
    this.submitError = null;
    
    // Prepare the vehicle data for submission.
    const vehicleData: VehiclePost = {
      reg: this.vehicle.reg,
      make: this.vehicle.make,
      model: this.vehicle.model
    };

    // Call the service POST method.
    this.garageService.postVehicle(vehicleData).subscribe(
      response => {
        console.log('Vehicle created successfully:', response);
        this.submitting = false;
        this.submitSuccess = true;
        // Navigate to the vehicles list after a delay.
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

  // Back button handler.
  goBack() {
    this.router.navigate(['/vehicles']);
  }
}
