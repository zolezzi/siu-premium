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
import { DegreeFilterDTO } from '../model/degreeFilterDTO';
import { SubjectDTO } from '../model/subjectDTO';
import { SubjectVO } from '../model/subjectVO';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class SubjectControllerService {

    protected basePath = 'http://localhost:8080';
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
     * This service delete a Subject
     * Delete a Subject, if it doesn&#39;t find it throw an exception
     * @param authorization 
     * @param degreeId degreeId
     * @param subjectId subjectId
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteById(authorization: string, degreeId: number, subjectId: number, observe?: 'body', reportProgress?: boolean): Observable<BasicResponse>;
    public deleteById(authorization: string, degreeId: number, subjectId: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<BasicResponse>>;
    public deleteById(authorization: string, degreeId: number, subjectId: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<BasicResponse>>;
    public deleteById(authorization: string, degreeId: number, subjectId: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling deleteById1.');
        }
        if (degreeId === null || degreeId === undefined) {
            throw new Error('Required parameter degreeId was null or undefined when calling deleteById1.');
        }
        if (subjectId === null || subjectId === undefined) {
            throw new Error('Required parameter subjectId was null or undefined when calling deleteById1.');
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

        return this.httpClient.delete<BasicResponse>(`${this.basePath}/subject/delete/${encodeURIComponent(String(degreeId))}/${encodeURIComponent(String(subjectId))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Service that return a Subject
     * This service return a Subject by the ID
     * @param authorization 
     * @param id id
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findById(authorization: string, id: number, observe?: 'body', reportProgress?: boolean): Observable<SubjectDTO>;
    public findById(authorization: string, id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<SubjectDTO>>;
    public findById(authorization: string, id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<SubjectDTO>>;
    public findById(authorization: string, id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling findById1.');
        }
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling findById1.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String('Bearer ' + authorization));
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

        return this.httpClient.get<SubjectDTO>(`${this.basePath}/subject/find-subject-by-id/${encodeURIComponent(String(id))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * This service save a Subject
     * Service that return SubjectDTO with saved object Subject
     * @param authorization 
     * @param subject subject
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public save(authorization: string, subject: SubjectVO, observe?: 'body', reportProgress?: boolean): Observable<SubjectDTO>;
    public save(authorization: string, subject: SubjectVO, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<SubjectDTO>>;
    public save(authorization: string, subject: SubjectVO, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<SubjectDTO>>;
    public save(authorization: string, subject: SubjectVO, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling save1.');
        }
        if (subject === null || subject === undefined) {
            throw new Error('Required parameter subject was null or undefined when calling save1.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String('Bearer ' + authorization));
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

        return this.httpClient.post<SubjectDTO>(`${this.basePath}/subject/save`,
            subject,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Service that returns search by filter subject by filter
     * This service returns search by filter  subject load by filter
     * @param authorization 
     * @param filter filter
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public searchByFilter(authorization: string, filter: DegreeFilterDTO, observe?: 'body', reportProgress?: boolean): Observable<Array<SubjectDTO>>;
    public searchByFilter(authorization: string, filter: DegreeFilterDTO, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<SubjectDTO>>>;
    public searchByFilter(authorization: string, filter: DegreeFilterDTO, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<SubjectDTO>>>;
    public searchByFilter(authorization: string, filter: DegreeFilterDTO, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling searchByFilter2.');
        }
        if (filter === null || filter === undefined) {
            throw new Error('Required parameter filter was null or undefined when calling searchByFilter2.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String('Bearer ' + authorization));
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

        return this.httpClient.post<Array<SubjectDTO>>(`${this.basePath}/subject/search-by-filter`,
            filter,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * This service update a Subject
     * Update a Subject, if it doesn&#39;t find it throw an exception
     * @param authorization 
     * @param degreeId degreeId
     * @param subject subject
     * @param subjectId subjectId
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public update(authorization: string, degreeId: number, subject: SubjectDTO, subjectId: number, observe?: 'body', reportProgress?: boolean): Observable<SubjectDTO>;
    public update(authorization: string, degreeId: number, subject: SubjectDTO, subjectId: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<SubjectDTO>>;
    public update(authorization: string, degreeId: number, subject: SubjectDTO, subjectId: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<SubjectDTO>>;
    public update(authorization: string, degreeId: number, subject: SubjectDTO, subjectId: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (authorization === null || authorization === undefined) {
            throw new Error('Required parameter authorization was null or undefined when calling update1.');
        }
        if (degreeId === null || degreeId === undefined) {
            throw new Error('Required parameter degreeId was null or undefined when calling update1.');
        }
        if (subject === null || subject === undefined) {
            throw new Error('Required parameter subject was null or undefined when calling update1.');
        }
        if (subjectId === null || subjectId === undefined) {
            throw new Error('Required parameter subjectId was null or undefined when calling update1.');
        }

        let headers = this.defaultHeaders;
        if (authorization !== undefined && authorization !== null) {
            headers = headers.set('Authorization', String('Bearer ' + authorization));
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

        return this.httpClient.put<SubjectDTO>(`${this.basePath}/subject/update/${encodeURIComponent(String(degreeId))}/${encodeURIComponent(String(subjectId))}`,
            subject,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
