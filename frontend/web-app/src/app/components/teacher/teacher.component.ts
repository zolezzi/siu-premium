import { MatTableDataSource } from '@angular/material/table';
import {
  AfterViewInit,
  Component,
  Inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { LocalStorageService } from 'ngx-webstorage';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css'],
})
export class TeacherComponent implements AfterViewInit, OnInit {
  isAdmin: boolean = false;
  role!: string;
  displayedColumns: string[] = [
    'nombre',
    'apellido',
    'dni',
    'carrera',
    'materia',
    'action',
  ];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly FULL_NAME: string = 'FULL_NAME';
  private readonly ROLE: string = 'ROLE';

  constructor(
    private localStorageService: LocalStorageService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    const token = this.localStorageService.retrieve(this.ACCESS_TOKEN);
  }

  openEditForm() {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {},
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  carreras: Carrera[] = [
    { viewValue: 'Ingenieria informática' },
    { viewValue: 'Biotecnología' },
    { viewValue: 'Enfermería' },
  ];
}
export interface Carrera {
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
