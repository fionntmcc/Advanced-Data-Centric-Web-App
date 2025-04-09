import { Routes } from '@angular/router';
import { VehiclesComponent } from './components/vehicles/vehicles.component';
import { DetailsComponent } from './components/details/details.component';
import { CreateVehicleComponent } from './components/create-vehicle/create-vehicle.component';

export const routes: Routes = [
  { path: 'vehicles', component: VehiclesComponent },
  { path: 'vehicleDetails/:reg', component: DetailsComponent },
  { path: 'addVehicle', component: CreateVehicleComponent },
  { path: '', redirectTo: '/vehicles', pathMatch: 'full' },
  { path: '**', redirectTo: '/vehicles' }
];
