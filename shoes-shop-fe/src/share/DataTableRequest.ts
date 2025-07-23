export class DataTableRequest{
  public currentPage = 1;
  public perPage = 100;
  public filter = '';
  public sortBy = '';
  public sortDesc = false;

  [key: string]: any;
  constructor(init?: Partial<DataTableRequest>) {
    Object.assign(this,init)
  }
}
