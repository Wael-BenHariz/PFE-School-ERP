import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {DocumentPage} from "../model/document";


@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  constructor(private http: HttpClient) {
  }

  geturl(file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<string>(`${environment.apiUrl}/firebase/url`, formdata);
  }
  createDocument(title: string | undefined,
                 description: string | undefined,
                 file: string | undefined,
  ) {
    return this.http.post(`${environment.apiUrl}/documents`, {
      title: title,
      description: description,
      file_url: file
    }, {observe: "response"})
  }
deleteDocument(documentId:number){
  console.log(documentId)
  return this.http.delete(`${environment.apiUrl}/documents/${documentId}`)
}

  getAllDocument(){
    return this.http.get<DocumentPage>(`${environment.apiUrl}/documents`)
  }
}
