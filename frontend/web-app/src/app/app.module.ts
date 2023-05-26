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
import { DegreeControllerService, SemesterControllerService, SubjectControllerService, UserControllerService } from './api';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { AddSemesterComponent } from './components/semester/add-semester/add-semester.component';
import { ListSemesterComponent } from './components/semester/list-semester/list-semester.component';
import { MatSelectModule } from '@angular/material/select';
import { DegreeComponent } from './components/degree/degree.component';
import { SubjectComponent } from './components/subject/subject.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { StudentComponent } from './components/student/student.component';
import { UpdateSemesterComponent } from './components/semester/update-semester/update-semester.component';

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
    DegreeComponent,
    SubjectComponent,
    ConfirmDialogComponent,
    TeacherComponent,
    StudentComponent,
    UpdateSemesterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule, 
    FormsModule,
    MatSelectModule,
    NgxWebstorageModule.forRoot()
    
  ],
  providers: [UserControllerService, SemesterControllerService, DegreeControllerService, SubjectControllerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
