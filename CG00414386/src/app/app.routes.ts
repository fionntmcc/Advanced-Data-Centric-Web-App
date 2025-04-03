import { Routes } from '@angular/router';
import { VehiclesComponent } from './components/vehicles/vehicles.component';
import { DetailsComponent } from './components/details/details.component';

export const routes: Routes = [
  { path: 'vehicles', component: VehiclesComponent },
  { path: 'vehicleDetails/:reg', component: DetailsComponent },
  { path: '', redirectTo: '/vehicles', pathMatch: 'full' },
  { path: '**', redirectTo: '/vehicles' }
];
