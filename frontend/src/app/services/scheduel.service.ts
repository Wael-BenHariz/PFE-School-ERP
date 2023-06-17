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
                    desc: string | undefined,
                    file: string | undefined,
                    teacher: number | undefined
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      desc: desc,
      file: file,
      teacher: teacher
    }, {observe: "response"})
  }
  createScheduleClasse(title: string | undefined,
                    desc: string | undefined,
                    file: string | undefined,
                    classe: number | undefined
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      desc: desc,
      file: file,
      classe: classe,
    }, {observe: "response"})
  }
  geturl(file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<string>(`${environment.apiUrl}/firebase/url`, formdata);
  }
}
