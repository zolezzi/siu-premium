import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';

export interface PeriodicElement {
  nombre: string;
  apellido: string;
  dni: string;
  carrera: string;
  materia: string;
}
export interface Comision {
  committeeType: string;
  viewValue: string;
}
const ELEMENT_DATA: PeriodicElement[] = [
  {
    nombre: 'Juan',
    apellido: 'Perez',
    dni: '123456789',
    carrera: 'Biotecnologia',
    materia: 'Química',
  },
  {
    nombre: 'Pedro',
    apellido: 'Zarsa',
    dni: '123456785',
    carrera: 'Ingeniería Informática',
    materia: 'Base de datos 1',
  },
];
@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent {

role!: string;
isAdmin: boolean = false;
isTeacher: boolean = false;
selectedType!: string;
displayedColumns: string[] = [
  'nombre',
  'apellido',
  'dni',
  'carrera',
  'materia',
  'action',
];
comisiones: Comision[] = [
  {
    committeeType: 'C1',
    viewValue: 'Comision 1',
  },
  {
    committeeType: 'C2',
    viewValue: 'Comision 2',
  },
  {
    committeeType: 'C3',
    viewValue: 'Comision 3',
  },
  {
    committeeType: 'C4',
    viewValue: 'Comision 4',
  }
];
dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
private readonly ROLE: string = 'ROLE';

isReload: boolean = true;
@ViewChild(MatPaginator) paginator!: MatPaginator;
constructor(
  private router: Router,
  private dialog: MatDialog,
  private localStorageService: LocalStorageService
) {}

ngOnInit(): void {
  var isValid = this.localStorageService.retrieve('RELOAD');
  if (isValid == 'reload') {
    this.localStorageService.store('RELOAD', 'login');
    window.location.reload();
  }
  this.role = this.localStorageService.retrieve(this.ROLE);
  this.isAdmin = 'ADMIN' == this.role;
  this.isTeacher = 'PROFESSOR' == this.role;
}
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();

  if (this.dataSource.paginator) {
    this.dataSource.paginator.firstPage();
  }
}
}