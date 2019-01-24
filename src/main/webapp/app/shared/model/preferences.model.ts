import { IUser } from 'app/core/user/user.model';

export const enum WeightUnits {
    KG = 'KG',
    LB = 'LB'
}

export interface IPreferences {
    id?: number;
    weeklyGoal?: number;
    weightUnits?: WeightUnits;
    user?: IUser;
}

export class Preferences implements IPreferences {
    constructor(public id?: number, public weeklyGoal?: number, public weightUnits?: WeightUnits, public user?: IUser) {}
}
