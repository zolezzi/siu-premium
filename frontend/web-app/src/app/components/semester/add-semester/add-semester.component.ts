import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { DegreeControllerService, DegreeDTO, DegreeFilterDTO, SemesterControllerService, SemesterVO } from 'src/app/api';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';

interface TypeSemester {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-add-semester',
  templateUrl: './add-semester.component.html',
  styleUrls: ['./add-semester.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { displayDefaultIndicatorType: false },
    },
  ],
})
export class AddSemesterComponent implements OnInit{
  panelOpenState = false;
  semester: SemesterVO = {};
  firstFormGroup = this._formBuilder.group({});
  listDegree: DegreeDTO[] = [];
  listDegreeWithSubjects: DegreeDTO[] = [];
  listResult: any[] = [];
  degreeFilter: DegreeFilterDTO = {};
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  carrera = this._formBuilder.group({
    listResult: new FormControl(),
  });

  subjectsBuilder = this._formBuilder.group({
    subjectsListSelect: this._formBuilder.array(this.listDegreeWithSubjects.map(subjects => this._formBuilder.group({
      subjects: []
    }))),
  });

  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  threeFormGroup = this._formBuilder.group({
    threeCtrl: ['', Validators.required],
  });

  semesters: TypeSemester[] = [
    { value: 'First', viewValue: 'Primer Cuatrimestre' },
    { value: 'Second', viewValue: 'Segundo Cuatrimestre' },
  ];

  constructor(private semesterControllerService: SemesterControllerService, 
    private degreeService: DegreeControllerService, private _formBuilder: FormBuilder,
    private router: Router, private localStorageService: LocalStorageService) {

  }
 
  ngOnInit(): void {
    this.degreeService.findAll(this.localStorageService.retrieve(this.ACCESS_TOKEN))
    .subscribe((data) => {
      this.listDegree = data;
    });
  }

  loadSubjects(carrera:any){
    debugger;
    this.degreeFilter.degreeIds = carrera.controls.listResult.value;
    this.degreeService.findAllDegreeByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.degreeFilter)
    .subscribe((data) => {
      this.listDegreeWithSubjects = data;
    });
  }
}
