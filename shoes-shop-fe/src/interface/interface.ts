export interface Brand {
  id: number;
  brandName: string;
  logo: string;
}
export interface LatestCollection {
  id: number;
  productName: string;
  brandName: string;
  price: number;
  imageUrl: string;
}
export interface Product {
  id: number
  productName: string
  brandName: string
  price: number
  imageUrl: string
  discountPercentage: number
}
export interface Collection {
  id: number
  collectionName: string
}
export interface Color {
  id: number
  colorName: string
}
export interface ProductDetail {
  id: number
  brandName: string
  colorName: string
  discountPercentage:number
  imageUrl: string
  price: number
  productName: string
  sizeValue: number
  stockValue: number,
  description:string,
  isThumbnail: boolean,
  discountPrice: number,
  categoryId: number,
}
export interface SizeByColor{
  stock: number
  colorName: string
  size: number,
  price: number
}
export interface Cart{
  productDetailId:number
  productName: string
  brandName: string,
  imageUrl: string,
  quantity: number,
  colorName: string,
  sizeValue: number
  price: number,
  discountPercentage: number
}

export interface ShippingFee{
  from_district_id: number, // Mã cố định kho xuất phát của bạn
  to_district_id:number,
  to_commune_code: number,
  height: number,
  length: number,
  weight: number,
  width: number,
  insurance_value:number
}

export interface Payment{
  totalAmount:number,
  buyerName:string,
  buyerEmail:string,
  buyerPhoneNumber:string,
  shippingFee: number,
  paymentType: number,
  amountPaid: number,
  communeValue:undefined,
  districtValue:undefined,
  provinceValue:undefined
}


