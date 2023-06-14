import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class ScheduelService {

  constructor(private http: HttpClient) {
  }
  createRealisation(classId: number | undefined, subjectId: number | undefined, teacherId: number | undefined, year: number | undefined) {
    return this.http.post(`${environment.apiUrl}/realisations`, {
      year: year,
      classId: classId,
      subjectId: subjectId,
      teacherId: teacherId
    }, {observe: "response"})
  }
}
