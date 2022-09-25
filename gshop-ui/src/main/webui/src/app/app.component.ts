import { Component, OnInit } from '@angular/core';
import { GshopResource } from './business/gshop/boundary/gshop.resource';
import { CartItem } from './business/gshop/entity/cart-item';
import { Game } from './business/gshop/entity/game';
import { Order } from './business/gshop/entity/order';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	title = 'gshop';
	games: Game[] = [];
	cartItems: CartItem[] = [];

	constructor(private gshopResource: GshopResource) {

	}

	ngOnInit() {
		this.gshopResource.games().subscribe((data: Game[]) => {
			this.games = data;
		});
		
		this.gshopResource.cart().subscribe((data: CartItem[]) => {
			this.cartItems = data;
		});
	}
	
	onAddToCart(id: number) {
		let order: Order = new Order();
		order.gameId = id;
		order.quantity = 1;
		
		this.gshopResource.addToCart(order).subscribe(() => {
			this.gshopResource.cart().subscribe((data: CartItem[]) => {
				this.cartItems = data;
			});
		});
	}
}
