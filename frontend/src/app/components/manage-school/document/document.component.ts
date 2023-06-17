import { Component } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html'
})
export class DocumentComponent {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination
  constructor() { }
  //Modals
  deleteModalOpen: boolean = false
  createDocumentModalOpen: boolean = false
}
