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
