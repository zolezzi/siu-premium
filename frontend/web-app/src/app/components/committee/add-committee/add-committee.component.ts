import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';

export interface Comision {
  committeeType: string;
  viewValue: string;
}
export interface PeriodicElement {
  nombre: string;
  apellido: string;
  dni: string;
  carrera: string;
  materia: string;
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
  selector: 'app-add-committee',
  templateUrl: './add-committee.component.html',
  styleUrls: ['./add-committee.component.css'],
})
export class AddCommitteeComponent {
  commiteeFormGroup!: FormGroup;
  docente = new FormControl('');
  docentesList: string[] = ['Fidel', 'Gustavo', 'Cesar', 'Fernando'];
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
  alumno = new FormControl('');
  alumnosList: string[] = ['Juan', 'Gustavo', 'Cesar', 'Fernando'];
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
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly ROLE: string = 'ROLE';

  isReload: boolean = true;

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
}
