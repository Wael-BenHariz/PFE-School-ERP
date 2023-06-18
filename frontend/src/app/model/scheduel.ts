export interface SchedeulPage {
  content: Schedeul[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Schedeul {
  id:number;
  title: string;
  description: string;
  file_url: string;
  archived:boolean;
}
