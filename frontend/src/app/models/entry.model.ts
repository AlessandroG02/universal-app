export interface Entry {
  id?: number;
  date: string;
  status: 'PENDING' | 'CONFIRMED' | 'CANCELLED';
  note?: string;
  memberId: number;
  memberName?: string;
}
