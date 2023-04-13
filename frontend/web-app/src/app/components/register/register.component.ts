import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';
import { UserControllerService, UserVO } from 'src/app/api';
interface Roles {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  submitted: boolean = false;
  private mapRoles = new Map<string, string>();

  constructor(
    private userservice: UserControllerService,
    private router: Router,
    private localStorageService: LocalStorageService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
      role: ['', Validators.required],
      dni: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      degreeId: [[]],
    });
    this.mapRoles.set('Estudiante', 'STUDENT');
    this.mapRoles.set('Profesor', 'PROFESSOR');
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    const user: UserVO = {
      email: this.registerForm.controls['email'].value,
      password: this.registerForm.controls['password'].value,
      repeatPassword: this.registerForm.controls['repeatPassword'].value,
      account: {
        role: this.mapRoles.get(this.registerForm.controls['role'].value),
        dni: this.registerForm.controls['dni'].value,
        firstname: this.registerForm.controls['firstname'].value,
        lastname: this.registerForm.controls['lastname'].value,
        degreeId: this.registerForm.controls['degreeId'].value,
      },
    };
    this.userservice.create(user).subscribe({
      next: (result) => {
        this.goToLogin();
      },
      error: (error) =>
        console.error('Error creando la cuenta. Reintente nuevamente.'),
    });
  }
  private goToLogin() {
    this.router.navigate(['/login']);
  }
}
