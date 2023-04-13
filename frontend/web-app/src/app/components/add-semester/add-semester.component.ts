import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

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
      useValue: {displayDefaultIndicatorType: false},
    },
  ],
})
export class AddSemesterComponent {
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  isLinear = false;
  events: string[] = [];
  selectedValue!: string;
 
  semesters: TypeSemester[] = [
    {value: '',viewValue: 'First'},
    {value: '', viewValue: 'Second'},
    
  ];
  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.events.push(`${type}: ${event.value}`);
  }
  constructor(private _formBuilder: FormBuilder) {
    
    
  }
  
}
