import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { DocumentService } from 'src/app/services/document.service';
import { Document, DocumentPage } from 'src/app/model/document';

@Component({
  selector: 'app-view-document',
  templateUrl: './view-document.component.html'
})
export class ViewDocumentComponent implements OnInit {
  document$: Observable<DocumentPage> | undefined

  constructor(public authService: AuthService,
    public documentService: DocumentService) { }

  ngOnInit(): void {
    if(this.authService.getRole() == "STUDENT" ){
      this.document$ = this.documentService.getAllDocument();
  }
  }
  openlink(link:string){
    window.open(link,'_blank')
  }

}
