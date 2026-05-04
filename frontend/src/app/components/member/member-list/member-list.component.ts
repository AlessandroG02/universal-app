import { Component, OnInit } from '@angular/core';
import { Member } from '../../../models/member.model';
import { Manager } from '../../../models/manager.model';
import { MemberService } from '../../../services/member.service';
import { ManagerService } from '../../../services/manager.service';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html'
})
export class MemberListComponent implements OnInit {

  members: Member[] = [];
  managers: Manager[] = [];
  showForm = false;
  editMode = false;
  formData: Member = { name: '', managerId: 0 };

  constructor(
    private memberService: MemberService,
    private managerService: ManagerService
  ) {}

  ngOnInit(): void {
    this.load();
    this.managerService.getAll().subscribe(d => this.managers = d);
  }

  load(): void {
    this.memberService.getAll().subscribe(d => this.members = d);
  }

  openCreate(): void {
    this.formData = { name: '', managerId: 0 };
    this.editMode = false;
    this.showForm = true;
  }

  openEdit(m: Member): void {
    this.formData = { ...m };
    this.editMode = true;
    this.showForm = true;
  }

  save(): void {
    if (this.editMode && this.formData.id) {
      this.memberService.update(this.formData.id, this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    } else {
      this.memberService.create(this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    }
  }

  delete(id: number): void {
    if (confirm('Confermi?')) {
      this.memberService.delete(id).subscribe(() => this.load());
    }
  }
}
