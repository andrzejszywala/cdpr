import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { GshopResource } from './business/gshop/boundary/gshop.resource';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
	HttpClientModule,
    BrowserModule
  ],
  providers: [GshopResource],
  bootstrap: [AppComponent]
})
export class AppModule { }
