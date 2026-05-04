import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerListComponent } from './components/manager/manager-list/manager-list.component';
import { MemberListComponent } from './components/member/member-list/member-list.component';
import { EntryListComponent } from './components/entry/entry-list/entry-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'managers', pathMatch: 'full' },
  { path: 'managers', component: ManagerListComponent },
  { path: 'members', component: MemberListComponent },
  { path: 'entries', component: EntryListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
