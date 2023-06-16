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
                    teacher: User[] ,
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
                    classe: Class[]
  ) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      title: title,
      desc: desc,
      file: file,
      classe: classe,
    }, {observe: "response"})
  }
}
