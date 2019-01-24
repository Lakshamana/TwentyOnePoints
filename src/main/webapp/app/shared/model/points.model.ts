import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IPoints {
    id?: number;
    date?: Moment;
    exercise?: boolean;
    meals?: boolean;
    alcohol?: boolean;
    notes?: string;
    user?: IUser;
}

export class Points implements IPoints {
    constructor(
        public id?: number,
        public date?: Moment,
        public exercise?: boolean,
        public meals?: boolean,
        public alcohol?: boolean,
        public notes?: string,
        public user?: IUser
    ) {
        this.exercise = this.exercise || false;
        this.meals = this.meals || false;
        this.alcohol = this.alcohol || false;
    }
}
