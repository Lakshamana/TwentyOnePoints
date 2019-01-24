import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBloodPressure } from 'app/shared/model/blood-pressure.model';
import { AccountService } from 'app/core';
import { BloodPressureService } from './blood-pressure.service';

@Component({
    selector: 'jhi-blood-pressure',
    templateUrl: './blood-pressure.component.html'
})
export class BloodPressureComponent implements OnInit, OnDestroy {
    bloodPressures: IBloodPressure[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected bloodPressureService: BloodPressureService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.bloodPressureService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IBloodPressure[]>) => (this.bloodPressures = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.bloodPressureService.query().subscribe(
            (res: HttpResponse<IBloodPressure[]>) => {
                this.bloodPressures = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBloodPressures();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBloodPressure) {
        return item.id;
    }

    registerChangeInBloodPressures() {
        this.eventSubscriber = this.eventManager.subscribe('bloodPressureListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
