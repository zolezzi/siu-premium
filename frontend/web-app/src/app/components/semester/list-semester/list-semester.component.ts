
import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';
import { SemesterDegreeSubjectControllerService, SemesterDegreeSubjectDTO, SemesterDegreeSubjectFilterDTO } from 'src/app/api';

@Component({
  selector: 'app-list-semester',
  templateUrl: './list-semester.component.html',
  styleUrls: ['./list-semester.component.css']
})
export class ListSemesterComponent implements AfterViewInit {
  isAdmin: boolean = false;
  role!: string;
  filter:SemesterDegreeSubjectFilterDTO = {};
  displayedColumns: string[] = [ 'materia','tipo', 'carrera', 'fechaDesde', 'fechaHasta', 'action'];
  dataSource = new MatTableDataSource<SemesterDegreeSubjectDTO>([]);
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly FULL_NAME: string = 'FULL_NAME';
  private readonly ROLE: string = 'ROLE';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private semesterDegreeSubjectService: SemesterDegreeSubjectControllerService,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorageService: LocalStorageService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    this.semesterDegreeSubjectService.searchByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.filter)
    .subscribe((data) => {
      this.dataSource = new MatTableDataSource<SemesterDegreeSubjectDTO>(data);
    });
  }

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
  
  delete(element:any){
    this.semesterDegreeSubjectService.deleteById(this.localStorageService.retrieve(this.ACCESS_TOKEN), element.id, element.semesterId, element.degreeId, element.subjectId).subscribe((data) => {
      this.search();
    });
  }

  search(){
    this.semesterDegreeSubjectService.searchByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.filter)
    .subscribe((data) => {
      this.dataSource = new MatTableDataSource<SemesterDegreeSubjectDTO>(data);
    });
  }
}