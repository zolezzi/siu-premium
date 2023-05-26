import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  listDegree: DegreeDTO[] = [];
  listDegreeWithSubjects: DegreeDTO[] = [];
  listResult: any[] = [];
  listResult2: any[] = [];
  isValid:boolean = false;
  degreeFilter: DegreeFilterDTO = {};
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';

  carrera = this._formBuilder.group({
    listResult: new FormControl(),
  });

  degreeMultiSelectForm = new FormControl();

  subjectsBuilder = this._formBuilder.group({
    subjectsListSelect: this._formBuilder.array(
      [this.createItem()
      ]
    ),
  });

 createItem() {
    return this._formBuilder.group({
      subjectsList: []
    });
  }

  firstFormGroup = this._formBuilder.group({
    toDateSemester: [Date, Validators.required],
    fromDateSemester: [Date, Validators.required],
    semesterTypeForm: ['', Validators.required],
  });

  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  threeFormGroup = this._formBuilder.group({
    threeCtrl: ['', Validators.required],
  });

  semesters: TypeSemester[] = [
    { value: 'FIRST', viewValue: 'Primer Cuatrimestre' },
    { value: 'SECOND', viewValue: 'Segundo Cuatrimestre' },
  ];

  constructor(private semesterService: SemesterControllerService, 
    private degreeService: DegreeControllerService, private _formBuilder: FormBuilder,
    private router: Router, private localStorageService: LocalStorageService) {

  }
 
  ngOnInit(): void {
    this.degreeService.findAll(this.localStorageService.retrieve(this.ACCESS_TOKEN))
    .subscribe((data) => {
      this.listDegree = data;
      this.listDegreeWithSubjects = data;
    });
  }

  loadSubjects(carrera:any){
    this.degreeFilter.degreeIds = carrera.controls.listResult.value;
    this.degreeService.findAllDegreeByFilter(this.localStorageService.retrieve(this.ACCESS_TOKEN), this.degreeFilter)
    .subscribe((data) => {
      this.isValid = true;
      this.listDegreeWithSubjects = data;
    });
  }

  saveSemester(subjectsBuilder:any){
    const semesterVOSave: any = {
      degreeIds: this.degreeFilter.degreeIds,
      subjects: subjectsBuilder.value,
      fromDate: this.firstFormGroup.controls['fromDateSemester'].value,
      toDate: this.firstFormGroup.controls['toDateSemester'].value,
      semesterType: this.firstFormGroup.controls['semesterTypeForm'].value,
    }
    this.semesterService.saveSemester(this.localStorageService.retrieve(this.ACCESS_TOKEN), semesterVOSave)
    .subscribe((data) => {
      this.router.navigate(['/semester']);
    });
  }
}
