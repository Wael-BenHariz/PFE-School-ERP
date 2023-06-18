export interface DocumentPage {
  content: Document[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Document {
  id:number;
  title: string;
  description: string;
  file_url: string;
  archived:boolean;
}
