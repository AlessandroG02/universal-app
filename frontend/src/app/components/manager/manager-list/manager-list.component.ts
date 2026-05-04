import { Component, OnInit } from '@angular/core';
import { Manager } from '../../../models/manager.model';
import { ManagerService } from '../../../services/manager.service';

@Component({
  selector: 'app-manager-list',
  templateUrl: './manager-list.component.html'
})
export class ManagerListComponent implements OnInit {

  managers: Manager[] = [];
  selectedManager: Manager | null = null;
  showForm = false;

  formData: Manager = { name: '', email: '' };
  editMode = false;

  constructor(private managerService: ManagerService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.managerService.getAll().subscribe(data => {
      this.managers = data;
    });
  }

  openCreate(): void {
    this.formData = { name: '', email: '' };
    this.editMode = false;
    this.showForm = true;
  }

  openEdit(m: Manager): void {
    this.formData = { ...m };
    this.editMode = true;
    this.showForm = true;
  }

  save(): void {
    if (this.editMode && this.formData.id) {
      this.managerService.update(this.formData.id, this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    } else {
      this.managerService.create(this.formData)
        .subscribe(() => { this.showForm = false; this.load(); });
    }
  }

  delete(id: number): void {
    if (confirm('Confermi eliminazione?')) {
      this.managerService.delete(id)
        .subscribe(() => this.load());
    }
  }
}
