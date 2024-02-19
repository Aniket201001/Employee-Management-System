import { TestBed } from '@angular/core/testing';

import { AddempService } from './addemp.service';

describe('AddempService', () => {
  let service: AddempService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddempService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
