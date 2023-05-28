export * from './service/committeeController.service';
import { CommitteeControllerService } from './service/committeeController.service';
export * from './service/degreeController.service';
import { DegreeControllerService } from './service/degreeController.service';
export * from './service/semesterController.service';
import { SemesterControllerService } from './service/semesterController.service';
export * from './service/semesterDegreeSubjectController.service';
import { SemesterDegreeSubjectControllerService } from './service/semesterDegreeSubjectController.service';
export * from './service/subjectController.service';
import { SubjectControllerService } from './service/subjectController.service';
export * from './service/userController.service';
import { UserControllerService } from './service/userController.service';
export const APIS = [CommitteeControllerService, DegreeControllerService, SemesterControllerService, SemesterDegreeSubjectControllerService, SubjectControllerService, UserControllerService];
