import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPraistataireComponent } from './register-praistataire.component';

describe('RegisterPraistataireComponent', () => {
  let component: RegisterPraistataireComponent;
  let fixture: ComponentFixture<RegisterPraistataireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterPraistataireComponent]
    });
    fixture = TestBed.createComponent(RegisterPraistataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
