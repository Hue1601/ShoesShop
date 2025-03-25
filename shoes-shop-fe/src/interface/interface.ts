export interface Brand {
  id: number;
  brandName: string;
  logo: string;
}
export interface Product {
  id: number
  productName: string
  brandName: string
  price: number
  imageUrl: string
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
  isThumbnail: boolean
}
