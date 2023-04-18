import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';

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
export class AddSemesterComponent {
  panelOpenState = false;
  firstFormGroup = this._formBuilder.group({
  });
  
  carrera = this._formBuilder.group({
    Tecnicatura: false,
    Ingenieria: false,
    Licenciatura: false,
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  threeFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  selectedValue!: string;

  semesters: TypeSemester[] = [
    { value: '', viewValue: 'First' },
    { value: '', viewValue: 'Second' },
  ];

  constructor(private _formBuilder: FormBuilder) {}
}
