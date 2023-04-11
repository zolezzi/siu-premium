import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalStorageService } from "ngx-webstorage";
import { UserLoginVO } from 'src/app/api';
import { UserControllerService } from 'src/app/api/service/userController.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted:boolean = false;
  private readonly ACCESS_TOKEN:string = "ACCESS_TOKEN";

  constructor(private userservice: UserControllerService,  private router: Router, 
    private localStorageService: LocalStorageService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSubmit(value:any) {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    const authRequest: UserLoginVO = {
      email: value.email,
      password: value.password
    };
    this.userservice.login(authRequest).subscribe({
      next:result =>  { 
        this.localStorageService.store(this.ACCESS_TOKEN, result.token);
        this.goToWelcome();
      },
      error:error => console.error('Error iniciando sesión. Reintente nuevamente.')
    });
  }

  private goToWelcome() {
    this.router.navigate(['/home']);
  }
}
