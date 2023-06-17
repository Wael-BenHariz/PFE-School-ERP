import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {Class} from "../model/class";


@Injectable({
  providedIn: 'root'
})
export class ScheduelService {

  constructor(private http: HttpClient) {
  }
  createScheduleTeacher(title: string | undefined,
                        description: string | undefined,
                    file: string | undefined,
                        teacher_id: number | undefined
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      description: description,
      file: file,
      teacher_id: teacher_id
    }, {observe: "response"})
  }
  createScheduleClasse(title: string | undefined,
                       description: string | undefined,
                    file: string | undefined,
                       class_id: number | undefined
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      description: description,
      file: file,
      class_id: class_id,
    }, {observe: "response"})
  }
  geturl(file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<string>(`${environment.apiUrl}/firebase/url`, formdata);
  }
}
