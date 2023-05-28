import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SemesterVO } from 'src/app/api/model/semesterVO';
import { SemesterControllerService } from 'src/app/api/service/semesterController.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';
interface TypeSemester {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-update-semester',
  templateUrl: './update-semester.component.html',
  styleUrls: ['./update-semester.component.css']
})
export class UpdateSemesterComponent implements OnInit {
  semesterForm!: FormGroup;
  semester: SemesterVO = {};
  id!:any;
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';

  semesterFormGroup = this.formBuilder.group({
    toDateSemester: [new Date, Validators.required],
    fromDateSemester: [ new Date, Validators.required],
    semesterTypeForm: ['', Validators.required],
  });
 
  semesters: TypeSemester[] = [
    { value: 'FIRST', viewValue: 'Primer Cuatrimestre' },
    { value: 'SECOND', viewValue: 'Segundo Cuatrimestre' },
  ];
  constructor(
    private semesterControllerService: SemesterControllerService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar,
    private localStorageService: LocalStorageService
  ) {} 

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = params.get('id');
      this.localStorageService.retrieve(this.ACCESS_TOKEN),
      this.semesterControllerService.findSemesterById(this.localStorageService.retrieve(this.ACCESS_TOKEN),this.id)
      .subscribe((data)=>{
        this.semesterFormGroup.patchValue({
          toDateSemester: data.toDate,
          fromDateSemester:data.fromDate,
          semesterTypeForm:data.semesterType
        })
    })
  });
  }

  update() {
    const semesterVOSave: any = {
      fromDate: this.semesterFormGroup.controls['fromDateSemester'].value,
      toDate: this.semesterFormGroup.controls['toDateSemester'].value,
      semesterType: this.semesterFormGroup.controls['semesterTypeForm'].value,
    }
    this.semesterControllerService
      .updateSemester(
        this.localStorageService.retrieve(this.ACCESS_TOKEN),
        this.id, semesterVOSave
      )
      .subscribe((data) => {
        this.snackBar.open('Actualizado con éxito', '', {
          duration: 10000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
        });
        this.router.navigate(['/semester']);
      });
  }
}
