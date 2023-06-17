import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  constructor(private http: HttpClient) {
  }

  createDocument(title: string | undefined,
                 description: string | undefined,
                 file: string | undefined,
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      description: description,
      file: file
    }, {observe: "response"})
  }

  geturl(file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<string>(`${environment.apiUrl}/firebase/url`, formdata);
  }
}
