import { Component } from '@angular/core';
import { PeriodicElement } from '../semester/list-semester/list-semester.component';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-degree',
  templateUrl: './degree.component.html',
  styleUrls: ['./degree.component.css']
})
export class DegreeComponent {

  isAdmin: boolean = false;
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
const ELEMENT_DATA: PeriodicElement[] = [
  { materia: 'Intro', carrera: 'Tecnicatura en Programación', tipo:'Segundo Cuatri', fechaHasta:'12/07/2023' ,fechaDesde:'03/03/203'},
  
];