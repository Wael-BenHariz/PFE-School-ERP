export interface SchedeulPage {
  content: Schedeul[],
  totalPages: number,
  last: boolean,
  number: number
}


export interface Scheduel {
  ScheduelId: number;
  title: string;
  file_url: string;
  description:string;
    archived:boolean;
}
