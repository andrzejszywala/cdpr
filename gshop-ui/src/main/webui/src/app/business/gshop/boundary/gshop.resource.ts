import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { CartItem } from '../entity/cart-item';
import { Game } from '../entity/game';
import { Order } from '../entity/order';


@Injectable()
export class GshopResource {

	private jwt: string = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dzaG9wLnBsL2lzc3VlciIsInVwbiI6ImFuZHJ6ZWoiLCJpYXQiOjE2NjQwOTQ3MDM0MDcsImV4cCI6MTY2NTA5NDcwMzQwOCwiZ3JvdXBzIjpbIlVTRVIiXSwianRpIjoiMDc2MzRmOGMtZDU1OC00ZmQ4LWFiMjMtNmIyZDc3MDU1MDVkIn0.JqBr5HXdCEyHGEpzlLgVvsxLCcQDyN8YuE1XKl32jqe0C0qYk4O0dgqeedvslwKfPTfUCAyCMUItDsWNDddFlnk23CcVst52o8bvDTNhMPE-d91rgb1GF7K4CJxOlfPagQRl1-wfRnO6uZ--T867wCo1jQmu6KjoCiRsIBCemj0VI6sNfBsJIsu6KHw6kuv16tYuMoJpFKN_vHZ2CskYJaize2WuV3jeEFshtU6SWqfMzYC8uC9iKQNnf5MrtDc2oeS5eITCzNG6GeQZACL0yyQ17UAeM9uNRXg4IObCrVZm5r-X1aKjhjqcVPXn1OGW86PXn5ML1wq-2vzj0xAPaQ';

    constructor(private http: HttpClient) { }

    games(): Observable<Game[]> {
	    const headers = new HttpHeaders().set('Authorization', this.jwt);
        return this.http.get<Game[]>('gshop/games', { headers, responseType: 'json'});
    }

    addToCart(order: Order): Observable<any> {
	    const headers = new HttpHeaders().set('Authorization', this.jwt);
        return this.http.post('gshop/add_to_cart', order, { headers, responseType: 'json'});
    }
    
    cart(): Observable<CartItem[]> {
		const headers = new HttpHeaders().set('Authorization', this.jwt);
        return this.http.get<CartItem[]>('gshop/cart', { headers, responseType: 'json'});
	}
}