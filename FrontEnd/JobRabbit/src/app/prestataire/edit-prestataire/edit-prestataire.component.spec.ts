import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPrestataireComponent } from './edit-prestataire.component';

describe('EditComponent', () => {
  let component: EditPrestataireComponent;
  let fixture: ComponentFixture<EditPrestataireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditPrestataireComponent]
    });
    fixture = TestBed.createComponent(EditPrestataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
