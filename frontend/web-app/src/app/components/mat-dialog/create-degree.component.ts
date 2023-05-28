import {Component, Inject, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { LocalStorageService } from 'ngx-webstorage';
import { DegreeControllerService } from 'src/app/api';

@Component({
  selector: 'app-create-degree-dialog',
  templateUrl: './create-degree.component.html',
  styleUrls: ['./create-degree.component.css'],
})
export class CreateDegreeDialogComponent implements OnInit {
  token!: string;
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly ROLE: string = 'ROLE';
  
  formGroupCreate = this._formBuilder.group({
    name: ['', Validators.required],
  });

  constructor(public dialogRef: MatDialogRef<CreateDegreeDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private degreeService: DegreeControllerService,
              private localStorageService: LocalStorageService) {
  }

  closeDialog() {
    this.dialogRef.close(false);
  }

  ngOnInit() {
    this.token = this.localStorageService.retrieve(this.ACCESS_TOKEN);
  }

  onSubmitForm() {
    const degreeVO: any = {
        name: this.formGroupCreate.controls['name'].value,
    }
    this.degreeService.save(this.token, degreeVO).subscribe(result => {
      this.dialogRef.close(true);
    },
    error => {
      alert('Error creando la carrera. Reintente nuevamente.');
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}