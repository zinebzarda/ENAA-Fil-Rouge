import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPrestataireComponent } from './list-prestataire.component';

describe('ListComponent', () => {
  let component: ListPrestataireComponent;
  let fixture: ComponentFixture<ListPrestataireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListPrestataireComponent]
    });
    fixture = TestBed.createComponent(ListPrestataireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
