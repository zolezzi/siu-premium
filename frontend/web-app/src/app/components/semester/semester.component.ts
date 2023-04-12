import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  listSemester: any[] = [];
  private readonly ACCESS_TOKEN:string = "ACCESS_TOKEN";

  constructor(
    private semesterControllerService: SemesterControllerService,
    private formBuilder: FormBuilder,
    private router: Router,
    private localStorageService: LocalStorageService
  ) {}
  ngOnInit(): void {
    this.filter.year = 2023;
    
    this.semesterForm = 
      this.formBuilder.group({
       
        year: [null, Validators.required],
      });
    this.search(this.filter);
    console.log('holis')
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
  
  search(filter:SemesterFilterDTO){
    this.semesterControllerService.searchSemestersByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), filter)
    .subscribe((data) => {
        this.listSemester = data;
      });
  }
}
