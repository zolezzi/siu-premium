
import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-list-semester',
  templateUrl: './list-semester.component.html',
  styleUrls: ['./list-semester.component.css']
})
export class ListSemesterComponent implements AfterViewInit {
  displayedColumns: string[] = [ 'materia','tipo', 'carrera', 'fechaDesde', 'fechaHasta', 'action'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
   applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}

export interface PeriodicElement {
  materia: string;
  carrera: string;
  tipo: string;
  fechaHasta: string;
  fechaDesde:string
}

const ELEMENT_DATA: PeriodicElement[] = [
  { materia: 'Intro', carrera: 'Tecnicatura en Programación', tipo:'Segundo Cuatri', fechaHasta:'12/07/2023' ,fechaDesde:'03/03/203'},
  
];

