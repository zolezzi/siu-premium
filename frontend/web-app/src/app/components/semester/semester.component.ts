import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';
import { SemesterFilterDTO } from 'src/app/api';
import { SemesterVO } from 'src/app/api/model/semesterVO';
import { SemesterControllerService } from 'src/app/api/service/semesterController.service';

@Component({
  selector: 'app-semester',
  templateUrl: './semester.component.html',
  styleUrls: ['./semester.component.css'],
})
export class SemesterComponent implements OnInit {
  filter: SemesterFilterDTO = {};
  semesterForm!: FormGroup;
  selectedYear: any;
  listSemester: any[] = [];
  role!: string;
  isAdmin: boolean = false;
  selectedType!: string;
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly ROLE: string = 'ROLE';

  isReload: boolean = true;

  constructor(
    private semesterControllerService: SemesterControllerService,
    private formBuilder: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit(): void {
    var isValid = this.localStorageService.retrieve('RELOAD');
    if (isValid == 'reload') {
      this.localStorageService.store('RELOAD', 'login');
      window.location.reload();
    }
    this.selectedYear = new FormControl('2023', [Validators.required]);
    this.filter.semesterType = this.selectedType;
    this.filter.year = this.selectedYear.value;
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    this.semesterForm = this.formBuilder.group({
      year: [null, Validators.required],
    });
    this.search(this.filter);
  }

  delete(id: number) {
    this.semesterControllerService
      .deleteSemesterById(
        this.localStorageService.retrieve(this.ACCESS_TOKEN),
        id
      )
      .subscribe((data) => {
        this.snackBar.open('Borrado con éxito', '', {
          duration: 10000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
        });
        this.search(this.filter);
      });
  }

  onSubmit(value: any) {
    const semesterRequest: SemesterVO = {
      degreeIds: value.degreeIds,
      fromDate: value.fromDate,
      semesterType: value.semesterType,
      subjects: value.subjects,
      toDate: value.toDate,
    };
    this.search(this.filter);
  }

  search(filter: SemesterFilterDTO) {
    this.semesterControllerService
      .searchSemestersByFilter(
        this.localStorageService.retrieve(this.ACCESS_TOKEN),
        filter
      )
      .subscribe((data) => {
        this.listSemester = data;
      });
  }
}
