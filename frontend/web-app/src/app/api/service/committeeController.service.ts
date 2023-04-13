/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';

import { Observable }                                        from 'rxjs';

import { BasicResponse } from '../model/basicResponse';
import { CommitteeDTO } from '../model/committeeDTO';
import { CommitteeVO } from '../model/committeeVO';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class CommitteeControllerService {

    protected basePath = 'https://localhost:8080';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (let consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * This service delete a Committee
     * Delete a Committee, if it doesn&#39;t find it throw an exception
     * @param authorization 
     * @param committeeId committeeId
     * @param semesterDegreeSubjectId semesterDegreeSubjectId
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteById(authorization: string, committeeId: number, semesterDegreeSubjectId: number, observe?: 'body', reportProgress?: boolean): Observable<BasicResponse>;
    public deleteById(authorization: string, committeeId: number, semesterDegreeSubjectId: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<BasicResponse>>;
    public deleteById(authorization: string, committeeId: number, semesterDegreeSubjectId: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<BasicResponse>>;
    public deleteById(authorization: string, committeeId: number, semesterDegreeSubjectId: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling deleteById.');
        }
        if (committeeId === null || committeeId === undefined) {
            throw new Error('Required parameter committeeId was null or undefined when calling deleteById.');
        }
        if (semesterDegreeSubjectId === null || semesterDegreeSubjectId === undefined) {
            throw new Error('Required parameter semesterDegreeSubjectId was null or undefined when calling deleteById.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.delete<BasicResponse>(`${this.basePath}/committee/delete/${encodeURIComponent(String(semesterDegreeSubjectId))}/${encodeURIComponent(String(committeeId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Service that return a Committee
     * This service return a Committee by the ID
     * @param authorization 
     * @param id id
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findById(authorization: string, id: number, observe?: 'body', reportProgress?: boolean): Observable<CommitteeDTO>;
    public findById(authorization: string, id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CommitteeDTO>>;
    public findById(authorization: string, id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CommitteeDTO>>;
    public findById(authorization: string, id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling findById.');
        }
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling findById.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.get<CommitteeDTO>(`${this.basePath}/committee/find-committee-by-id/${encodeURIComponent(String(id))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * This service save a Committee
     * Service that return CommitteeDTO with saved object Committee
     * @param authorization 
     * @param committee committee
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public save(authorization: string, committee: CommitteeVO, observe?: 'body', reportProgress?: boolean): Observable<CommitteeDTO>;
    public save(authorization: string, committee: CommitteeVO, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CommitteeDTO>>;
    public save(authorization: string, committee: CommitteeVO, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CommitteeDTO>>;
    public save(authorization: string, committee: CommitteeVO, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling save.');
        }
        if (committee === null || committee === undefined) {
            throw new Error('Required parameter committee was null or undefined when calling save.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];
        let httpContentTypeSelected:string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set("Content-Type", httpContentTypeSelected);
        }

        return this.httpClient.post<CommitteeDTO>(`${this.basePath}/committee/save`,
            committee,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * This service update a Committee
     * Update a Committee, if it doesn&#39;t find it throw an exception
     * @param authorization 
     * @param committee committee
     * @param committeeId committeeId
     * @param semesterDegreeSubjectId semesterDegreeSubjectId
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public update(authorization: string, committee: CommitteeDTO, committeeId: number, semesterDegreeSubjectId: number, observe?: 'body', reportProgress?: boolean): Observable<CommitteeDTO>;
    public update(authorization: string, committee: CommitteeDTO, committeeId: number, semesterDegreeSubjectId: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CommitteeDTO>>;
    public update(authorization: string, committee: CommitteeDTO, committeeId: number, semesterDegreeSubjectId: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CommitteeDTO>>;
    public update(authorization: string, committee: CommitteeDTO, committeeId: number, semesterDegreeSubjectId: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling update.');
        }
        if (committee === null || committee === undefined) {
            throw new Error('Required parameter committee was null or undefined when calling update.');
        }
        if (committeeId === null || committeeId === undefined) {
            throw new Error('Required parameter committeeId was null or undefined when calling update.');
        }
        if (semesterDegreeSubjectId === null || semesterDegreeSubjectId === undefined) {
            throw new Error('Required parameter semesterDegreeSubjectId was null or undefined when calling update.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String(authorization));
        }

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];
        let httpContentTypeSelected:string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set("Content-Type", httpContentTypeSelected);
        }

        return this.httpClient.put<CommitteeDTO>(`${this.basePath}/committee/update/${encodeURIComponent(String(semesterDegreeSubjectId))}/${encodeURIComponent(String(committeeId))}`,
            committee,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
