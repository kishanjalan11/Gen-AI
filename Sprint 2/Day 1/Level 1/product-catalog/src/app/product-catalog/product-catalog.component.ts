import { Component } from '@angular/core';

@Component({
  selector: 'app-product-catalog',
  templateUrl: './product-catalog.component.html',
  styleUrls: ['./product-catalog.component.css']
})
export class ProductCatalogComponent {
  products: Product[] = [
    {
      name: 'Product 1',
      description: 'Description',
      price: 10,
      image: 'product1.jpg',
    },
    {
      name: 'Product 2',
      description: 'Description',
      price: 10,
      image: 'product2.jpg',
    },
    {
      name: 'Product 3',
      description: 'Description',
      price: 30,
      image: 'product3.jpg',
    },
    {
      name: 'Product 4',
      description: 'Description',
      price: 40,
      image: 'product4.jpg',
    }
  ];

}

interface Product {
  name: string;
  description: string;
  price: number;
  image: string;
}