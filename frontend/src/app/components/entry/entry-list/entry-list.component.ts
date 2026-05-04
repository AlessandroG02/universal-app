import { Component, OnInit } from '@angular/core';
import { Entry } from '../../../models/entry.model';
import { Member } from '../../../models/member.model';
import { EntryService } from '../../../services/entry.service';
import { MemberService } from '../../../services/member.service';

@Component({
  selector: 'app-entry-list',
  templateUrl: './entry-list.component.html'
})
export class EntryListComponent implements OnInit {

  entries: Entry[] = [];
  members: Member[] = [];
  showForm = false;
  editMode = false;
  formData: Entry = { date: new Date().toISOString().slice(0, 10), status: 'PENDING', memberId: 0 };

  constructor(
    private entryService: EntryService,
    private memberService: MemberService
  ) {}

  ngOnInit(): void {
    this.load();
    this.memberService.getAll().subscribe(d => this.members = d);
  }

  load(): void {
    this.entryService.getAll().subscribe(d => this.entries = d);
  }

  openCreate(): void {
    this.formData = { date: new Date().toISOString().slice(0, 10), status: 'PENDING', memberId: 0 };
    this.editMode = false;
    this.showForm = true;
  }

  openEdit(e: Entry): void {
    this.formData = { ...e };
    this.editMode = true;
    this.showForm = true;
  }

  save(): void {
    if (this.editMode && this.formData.id) {
      this.entryService.update(this.formData.id, this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    } else {
      this.entryService.create(this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    }
  }

  delete(id: number): void {
    if (confirm('Confermi?')) {
      this.entryService.delete(id).subscribe(() => this.load());
    }
  }
}
