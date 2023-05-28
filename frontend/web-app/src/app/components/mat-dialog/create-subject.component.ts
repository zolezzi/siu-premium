import {Component, Inject, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { LocalStorageService } from 'ngx-webstorage';
import { DegreeControllerService, DegreeDTO, SubjectControllerService } from 'src/app/api';

@Component({
  selector: 'app-create-subject-dialog',
  templateUrl: './create-subject.component.html',
})
export class CreateSubjectDialogComponent implements OnInit {
  token!: string;
  listDegree: DegreeDTO[] = [];
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly ROLE: string = 'ROLE';
  
  formGroupCreate = this._formBuilder.group({
    name: ['', Validators.required],
    degreeId: ['', Validators.required],
  });

  constructor(public dialogRef: MatDialogRef<CreateSubjectDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private degreeService: DegreeControllerService,
              private subjectService: SubjectControllerService,
              private localStorageService: LocalStorageService) {
  }

  closeDialog() {
    this.dialogRef.close(false);
  }

  ngOnInit() {
    this.token = this.localStorageService.retrieve(this.ACCESS_TOKEN);
    this.degreeService.findAll(this.token)
    .subscribe((data) => {
      this.listDegree = data;
    });

  }

  onSubmitForm() {
    const subjectVO: any = {
        name: this.formGroupCreate.controls['name'].value,
        degreeId: this.formGroupCreate.controls['degreeId'].value,
    }
    this.subjectService.save(this.token, subjectVO).subscribe(result => {
      this.dialogRef.close(true);
    },
    error => {
      alert('Error creando la materia. Reintente nuevamente.');
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}