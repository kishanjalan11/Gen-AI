import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-catalog',
  templateUrl: './product-catalog.component.html',
  styleUrls: ['./product-catalog.component.css']
})
export class ProductCatalogComponent implements OnInit{
  products: Product[] = [];
  cart: Product[] = [];
  constructor(private productService: ProductService){  }

  ngOnInit() {
    this.products=this.productService.getProducts();
  }

  showDetails(product: Product) {
    alert('Details for ' + product.title);
  }

  addToCart(product: Product) {
    this.cart.push(product);
    alert('Added ' + product.title + ' to the cart');
    console.log('Cart Contents:', this.cart);
  }

  toggleFavorite(product: any) {
    product.isFavorite = !product.isFavorite;
    if(product.isFavorite){
      alert(`${product.title} added to Favorites`)
    }
    else{
      alert(`${product.title} removed from Favorites`)
    }
    
  }
}

interface Product {
  title: string;
  description: string;
  price: number;
  image: string;
}