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
import { DegreeControllerService, DegreeDTO, DegreeFilterDTO, SubjectControllerService, SubjectDTO } from 'src/app/api';
import { CreateSubjectDialogComponent } from '../mat-dialog/create-subject.component';
@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
})
export class SubjectComponent implements AfterViewInit, OnInit {
  isAdmin: boolean = false;
  role!: string;
  filter:DegreeFilterDTO = {};
  displayedColumns: string[] = ['nombre', 'carrera', 'action'];
  dataSource = new MatTableDataSource<SubjectDTO>([]);
  listDegree: DegreeDTO[] = [];
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly FULL_NAME: string = 'FULL_NAME';
  private readonly ROLE: string = 'ROLE';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(
    private localStorageService: LocalStorageService,
    private degreeService: DegreeControllerService,
    private subjectService: SubjectControllerService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    this.degreeService.findAll(this.localStorageService.retrieve(this.ACCESS_TOKEN))
    .subscribe((data) => {
      this.listDegree = data;
    });
    this.subjectService.searchByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.filter)
    .subscribe((data) => {
      this.dataSource = new MatTableDataSource<SubjectDTO>(data);
    });
  }

  openEditForm() {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {},
    });
  }

  openCreateForm() {
    const dialogRef = this.dialog.open(CreateSubjectDialogComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        this.searchSubjects();
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

  delete(element:any){
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { message: '¿Desea eliminar la materia?' },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'aceptar') {
         this.subjectService.deleteById(this.localStorageService.retrieve(this.ACCESS_TOKEN), element.degreeId, element.id).subscribe((data) => {
       this.searchSubjects();
     });
      }
    });
  }

  searchSubjects(){
    this.subjectService.searchByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.filter)
    .subscribe((data) => {
      this.dataSource = new MatTableDataSource<SubjectDTO>(data);
    });
  }
}


