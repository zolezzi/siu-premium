import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Configuration } from './configuration';

import { CommitteeControllerService } from './service/committeeController.service';
import { DegreeControllerService } from './service/degreeController.service';
import { SemesterControllerService } from './service/semesterController.service';
import { SemesterDegreeSubjectControllerService } from './service/semesterDegreeSubjectController.service';
import { SubjectControllerService } from './service/subjectController.service';
import { UserControllerService } from './service/userController.service';

@NgModule({
  imports:      [ CommonModule, HttpClientModule ],
  declarations: [],
  exports:      [],
  providers: [
    CommitteeControllerService,
    DegreeControllerService,
    SemesterControllerService,
    SemesterDegreeSubjectControllerService,
    SubjectControllerService,
    UserControllerService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        }
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import your base AppModule only.');
        }
    }
}
