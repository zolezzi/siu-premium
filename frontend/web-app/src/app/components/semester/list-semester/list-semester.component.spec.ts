import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSemesterComponent } from './list-semester.component';

describe('ListSemesterComponent', () => {
  let component: ListSemesterComponent;
  let fixture: ComponentFixture<ListSemesterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListSemesterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListSemesterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
