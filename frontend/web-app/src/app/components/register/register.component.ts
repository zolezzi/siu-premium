import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
interface Roles {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm!: FormGroup;

  onSubmit() {}
}
