export interface ScheduelPage {
  content: Scheduel[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Scheduel {
  ScheduelId: number;
  title: string;
  file_url: string;
  description:string;
}
