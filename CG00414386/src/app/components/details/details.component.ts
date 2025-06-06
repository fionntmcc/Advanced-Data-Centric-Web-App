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
  
  // Store error state and message.
  public error: boolean = false;
  public errorMessage: string = '';

  // Store vehicle details.
  vehicle: Vehicle | null = null;
  vehicleForm!: FormGroup;
  isSubmitting = false;
  submitMessage = '';
  currentMechanicId: string = '';
  
  // Init on view enter.
  ngOnInit() {
    // Initialise form.
    this.initForm();
    
    // Get the registration parameter from the route.
    this.route.paramMap.subscribe(params => {
      const reg = params.get('reg');
      if (reg) {
        // Use the registration to fetch vehicle details.
        this.garageService.getVehicleByReg(reg).subscribe(
          (data: Vehicle) => {
            this.vehicle = data;
            console.log('Retrieved vehicle details:', this.vehicle);
            this.populateForm(data);
            this.isLoading = false;
            
            // Initialize the current mechanic ID.
            this.currentMechanicId = data.mechanic?.mid || '';
          },
          // Error loading vehicle details.
          error => {
            // Set error and message.
            console.error('Error fetching vehicle details:', error);
            this.error = true;
            this.errorMessage = 'Unable to load vehicle details. Please try again later. ' + error.message;
            this.isLoading = false;
          }
        );
      }
    });
  }

  // Initialize the form with empty strings, disable all fields except mid.
  initForm() {
    this.vehicleForm = this.fb.group({
      reg: [{value: '', disabled: true}],
      make: [{value: '', disabled: true}],
      model: [{value: '', disabled: true}],
      mechanicId: ['', Validators.required],
      mechanicName: [{value: '', disabled: true}],
      garageLocation: [{value: '', disabled: true}],
    });
    
    // Subscribe to changes in the mid field.
    this.vehicleForm.get('mechanicId')?.valueChanges.subscribe(value => {
      this.currentMechanicId = value;
    });
  }

  // Populate the form with vehicle details once fetched.
  populateForm(vehicle: Vehicle) {
    this.vehicleForm.patchValue({
      reg: vehicle.reg,
      make: vehicle.make,
      model: vehicle.model,
      // May be undefined,
      // if so, set to 'Not assigned'.
      mechanicId: vehicle.mechanic?.mid || 'Not assigned',
      mechanicName: vehicle.mechanic?.name|| 'Not assigned',
      garageLocation: vehicle.mechanic?.garage?.location || 'Not assigned',
    });
  }

  updateMechanic() {
    // Use the currentMechanicId directly
    console.log('Current Mechanic ID:', this.currentMechanicId);
    if (!this.currentMechanicId || !this.vehicle) {
      this.submitMessage = 'Error: Mechanic ID is required';
      return;
    }
    
    this.isSubmitting = true;
    this.submitMessage = '';
    
    // Call service to update vehicle mechanic.
    this.garageService.updateVehicleMechanic(this.vehicle.reg, this.currentMechanicId)
      .subscribe(
        response => {
          // Successful response.
          this.isSubmitting = false;
          this.submitMessage = 'Vehicle mechanic updated successfully';
          console.log('Update successful:', response);
          
          // Update mid to match API response. 
          if (this.vehicle?.mechanic?.mid) {
            this.vehicle.mechanic.mid = this.currentMechanicId;
          }
        },
        // Handle error.
        error => {
          this.isSubmitting = false;
          this.submitMessage = error.message + ` (Mechanic ${this.currentMechanicId} not found)`;
          console.error('Error updating vehicle:', error);
        }
      );
  }

  // Back button handler.
  goBack() {
    this.router.navigate(['/vehicles']);
  }
}
