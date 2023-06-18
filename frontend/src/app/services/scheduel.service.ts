import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import { Scheduel } from "../model/scheduel";


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
  )
  {
    return this.http.post(`${environment.apiUrl}/scheduel`, {
      title: title,
      description: description,
      file_url: file,
      teacher_id: teacher_id,
      class_id:null
    }, {observe: "response"})
  }
  createScheduleClasse(title: string | undefined,
                       description: string | undefined,
                    file: string | undefined,
                       class_id: number | undefined
  )
  {
    return this.http.post(`${environment.apiUrl}/scheduel`, {
      title: title,
      description: description,
      file_url: file,
      teacher_id:null,
      class_id: class_id,
    }, {observe: "response"})
  }



  getTeacherScheduel(id: number) {
    return this.http.get<Scheduel>(`${environment.apiUrl}/scheduel/TeacherScheduel/${id}`)
  }

  getClassScheduel(id: number) {
    return this.http.get<Scheduel>(`${environment.apiUrl}/scheduel/ClassScheduel/${id}`)
  }


  geturl(file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<string>(`${environment.apiUrl}/firebase/url`, formdata);
  }
}
