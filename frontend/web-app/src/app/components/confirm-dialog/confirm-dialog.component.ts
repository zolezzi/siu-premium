import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css'],
})
export class ConfirmDialogComponent implements OnInit {
  form: FormGroup;

  constructor(
    private _fb: FormBuilder,

    private _dialogRef: MatDialogRef<ConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.form = this._fb.group({});
  }

  ngOnInit(): void {
    this.form.patchValue(this.data);
  }

  onFormSubmit() {
    if (this.form.valid) {
      if (this.data) {
      }
    }
  }
}
