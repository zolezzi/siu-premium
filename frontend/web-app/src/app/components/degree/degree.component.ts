import { MatTableDataSource } from '@angular/material/table';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { LocalStorageService } from 'ngx-webstorage';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { DegreeControllerService, DegreeDTO } from 'src/app/api';
import { CreateDegreeDialogComponent } from '../mat-dialog/create-degree.component';

@Component({
  selector: 'app-degree',
  templateUrl: './degree.component.html',
  styleUrls: ['./degree.component.css'],
})
export class DegreeComponent implements AfterViewInit, OnInit {
  isAdmin: boolean = false;
  role!: string;
  displayedColumns: string[] = ['nombre', 'action'];
  dataSource = new MatTableDataSource<DegreeDTO>([]);
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly FULL_NAME: string = 'FULL_NAME';
  private readonly ROLE: string = 'ROLE';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private localStorageService: LocalStorageService,
    private dialog: MatDialog,
    private degreeService: DegreeControllerService
  ) {}

  ngOnInit(): void {
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    this.degreeService
      .findAll(this.localStorageService.retrieve(this.ACCESS_TOKEN))
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource<DegreeDTO>(data);
      });
  }  
  
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  
  openEditForm() {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {},
    });
  }

  openCreateForm() {
    const dialogRef = this.dialog.open(CreateDegreeDialogComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        this.searchDegree();
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  delete(element: any) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { message: '¿Desea eliminar la carrera?' },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'aceptar') {
        this.degreeService
          .deleteById(
            this.localStorageService.retrieve(this.ACCESS_TOKEN),
            element.id
          )
          .subscribe((data) => {
            this.searchDegree();
          });
      }
    });
  }

  searchDegree() {
    this.degreeService
      .findAll(this.localStorageService.retrieve(this.ACCESS_TOKEN))
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource<DegreeDTO>(data);
      });
  }
}
