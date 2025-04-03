import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GarageService } from '../../services/garage.service';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})



export class DetailsComponent {

  // Ticketmaster service
  private garageService = inject(GarageService);

  ngOnInit() {
    // Get vehicle details from service
    this.garageService.getAllVehicles().subscribe((data: any) => {
      console.log('Vehicle Details:', data);
    });
  }

}
