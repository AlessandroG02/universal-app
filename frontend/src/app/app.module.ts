import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ManagerListComponent } from './components/manager/manager-list/manager-list.component';
import { MemberListComponent } from './components/member/member-list/member-list.component';
import { EntryListComponent } from './components/entry/entry-list/entry-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ManagerListComponent,
    MemberListComponent,
    EntryListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
