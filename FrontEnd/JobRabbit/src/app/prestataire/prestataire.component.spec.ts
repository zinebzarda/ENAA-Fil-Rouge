import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestataireComponent } from './prestataire.component';

describe('PrestataireComponent', () => {
  let component: PrestataireComponent;
  let fixture: ComponentFixture<PrestataireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrestataireComponent]
    });
    fixture = TestBed.createComponent(PrestataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
