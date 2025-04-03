import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Vehicle } from '../../interfaces/garageInterfaces';
import { GarageService } from '../../services/garage.service';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export class DetailsComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private garageService = inject(GarageService);
  private fb = inject(FormBuilder);

  public isLoading: boolean = true;
  
  // Property to store error messages
  public error: boolean = false;
  public errorMessage: string = '';

  vehicle: Vehicle | null = null;
  vehicleForm!: FormGroup;
  isSubmitting = false;
  submitMessage = '';
  currentMechanicId: string = '';

  ngOnInit() {
    // Initialize form
    this.initForm();
    
    // Get the registration parameter from the route
    this.route.paramMap.subscribe(params => {
      const reg = params.get('reg');
      if (reg) {
        // Use the registration to fetch vehicle details
        this.garageService.getVehicleByReg(reg).subscribe(
          (data: Vehicle) => {
            this.vehicle = data;
            console.log('Retrieved vehicle details:', this.vehicle);
            this.populateForm(data);
            this.isLoading = false;
            
            // Initialize the current mechanic ID
            this.currentMechanicId = data.mechanic.mid;
          },
          error => {
            console.error('Error fetching vehicle details:', error);
            this.error = true;
            this.errorMessage = 'Unable to load vehicle details. Please try again later. ' + error.message;
            this.isLoading = false;
          }
        );
      }
    });
  }

  initForm() {
    this.vehicleForm = this.fb.group({
      reg: [{value: '', disabled: true}],
      make: [{value: '', disabled: true}],
      model: [{value: '', disabled: true}],
      mechanicId: ['', Validators.required],
      mechanicName: [{value: '', disabled: true}],
      garageLocation: [{value: '', disabled: true}],
    });
    
    // Subscribe to changes in the mechanicId field
    this.vehicleForm.get('mechanicId')?.valueChanges.subscribe(value => {
      this.currentMechanicId = value;
    });
  }

  populateForm(vehicle: Vehicle) {
    this.vehicleForm.patchValue({
      reg: vehicle.reg,
      make: vehicle.make,
      model: vehicle.model,
      mechanicId: vehicle.mechanic.mid,
      mechanicName: vehicle.mechanic.name,
      garageLocation: vehicle.mechanic.garage.location,
    });
  }

  onSubmit() {
    if (this.vehicleForm.valid && this.vehicle) {
      this.isSubmitting = true;
      const updatedVehicle = {
        ...this.vehicle,
        mechanic: {
          ...this.vehicle.mechanic,
          mid: this.vehicleForm.get('mechanicId')?.value
        }
      };

      // Call service to update vehicle (assumes you have an update method)
      this.garageService.updateVehicleMechanic(this.vehicle.reg, this.vehicleForm.get('mechanicId')?.value)
        .subscribe(
          response => {
            this.isSubmitting = false;
            this.submitMessage = 'Vehicle mechanic updated successfully';
            console.log('Update successful:', response);
            
            // Update the local vehicle object to reflect changes
            if (this.vehicle) {
              this.vehicle.mechanic.mid = this.vehicleForm.get('mechanicId')?.value;
            }
          },
          error => {
            this.isSubmitting = false;
            this.submitMessage = 'Error updating vehicle mechanic';
            console.error('Error updating vehicle:', error);
            this.error = true;
            this.isLoading = false;
            this.errorMessage = 'Unable to update vehicle details. ' + error.message;
          }
        );
    }
  }

  updateMechanic() {
    // Use the currentMechanicId directly
    console.log('Current Mechanic ID:', this.currentMechanicId);
    if (!this.currentMechanicId || !this.vehicle) {
      this.submitMessage = 'Error: Mechanic ID and vehicle registration are required';
      return;
    }
    
    this.isSubmitting = true;
    this.submitMessage = '';
    
    // Call service to update vehicle mechanic
    this.garageService.updateVehicleMechanic(this.vehicle.reg, this.currentMechanicId)
      .subscribe(
        response => {
          this.isSubmitting = false;
          this.submitMessage = 'Vehicle mechanic updated successfully';
          console.log('Update successful:', response);
          
          // Update the local vehicle object to reflect changes
          if (this.vehicle) {
            this.vehicle.mechanic.mid = this.currentMechanicId;
          }
        },
        error => {
          this.isSubmitting = false;
          this.submitMessage = 'Error updating vehicle mechanic';
          console.error('Error updating vehicle:', error);
        }
      );
  }

  goBack() {
    this.router.navigate(['/vehicles']);
  }
}
