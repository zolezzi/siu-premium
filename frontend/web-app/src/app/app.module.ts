import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AngularMaterialModule } from './shrared/angular-material.module';
import { FormsModule } from '@angular/forms';
import { SemesterComponent } from './components/semester/semester.component';
import { RegisterComponent } from './components/register/register.component';
import { SemesterControllerService, UserControllerService } from './api';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { AddSemesterComponent } from './components/semester/add-semester/add-semester.component';
import { ListSemesterComponent } from './components/semester/list-semester/list-semester.component';
import { CommitteeComponent } from './components/committee/committee.component';


@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    LoginComponent,
    NavbarComponent,
    RegisterComponent,
    SemesterComponent,
    AddSemesterComponent,
    ListSemesterComponent,
    CommitteeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule, 
    FormsModule,
    NgxWebstorageModule.forRoot()
    
  ],
  providers: [UserControllerService, SemesterControllerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
