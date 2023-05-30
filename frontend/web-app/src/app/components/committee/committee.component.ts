import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-committee',
  templateUrl: './committee.component.html',
  styleUrls: ['./committee.component.css']
})
export class CommitteeComponent implements OnInit{
  role!: string;
  isAdmin: boolean = false;
  isTeacher: boolean = false;
  private readonly ACCESS_TOKEN: string = 'ACCESS_TOKEN';
  private readonly ROLE: string = 'ROLE';

  isReload: boolean = true;
  constructor(
    private router: Router,
    private localStorageService: LocalStorageService,
  ){}
  ngOnInit(): void {
    var isValid = this.localStorageService.retrieve('RELOAD');
    if (isValid == 'reload') {
      this.localStorageService.store('RELOAD', 'login');
      window.location.reload();
    }
    this.role = this.localStorageService.retrieve(this.ROLE);
    this.isAdmin = 'ADMIN' == this.role;
    this.isTeacher = 'PROFESSOR' == this.role;
    
  }
}
