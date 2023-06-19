import {Component} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {DocumentService} from "../../../services/document.service";
import {DocumentPage} from "../../../model/document";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html'
})
export class DocumentComponent {

  //Filter
  showArchived: boolean = false
  //end filter

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination
  documents$: Observable<DocumentPage> = this.documentService.getAllDocument()

  constructor(private documentService: DocumentService) {
  }

  //Modals
  deleteModalOpen: boolean = false
  createDocumentModalOpen: boolean = false

  openlink(link: string) {
    window.open(link, '_blank')
  }

  getFilteredDocuments() {
    if (!this.showArchived) {
      this.documents$ = this.documentService.getAllDocument()
  }
  }


  deleteDocument(id:number) {
      this.documentService.deleteDocument(id).subscribe((result) => {
        this.getFilteredDocuments()
      }, error => {
      })

    }
  }
