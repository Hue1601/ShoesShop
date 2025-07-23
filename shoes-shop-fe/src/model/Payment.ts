export class Payment {
  public totalAmount?: number = undefined;
  public buyerName?: string = undefined;
  public buyerEmail?: string = undefined;
  public buyerPhoneNumber?: string = undefined;
  public shippingFee?: number = undefined;
  public paymentType?: number = undefined;
  public amountPaid?: number = undefined;
  public commune?: null = null;
  public district?: null = null  ;

  public province?: null = null ;


  constructor(init?: Partial<Payment>) {
    Object.assign(this,init)
  }
}

