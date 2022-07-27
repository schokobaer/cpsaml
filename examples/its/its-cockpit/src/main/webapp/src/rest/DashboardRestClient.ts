import {Dashboard} from "../dto/MonitorDto";
import {CPS} from "../dto/Cps";

const api = '/api/dashboard'
export default class DashboardRestClient {

    getAll(): Promise<{[key: string]: string}> {
        return fetch(api, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json()
            }
            throw 'Could not load available instances'
        })
    }

    get(id: string): Promise<Dashboard> {
        return fetch(api + '/' + id, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as Dashboard
                })
            }
            throw 'Could not load available instances'
        })
    }

    create(dashboard: Dashboard): Promise<string> {
        return fetch(api, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            cache: 'no-cache',
            body: JSON.stringify(dashboard)
        }).then(resp => {
            if (resp.ok) {
                return resp.text()
            }
            throw 'Could not load available instances'
        })
    }

    update(dashboard: Dashboard) {
        return fetch(`${api}/${dashboard.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            cache: 'no-cache',
            body: JSON.stringify(dashboard)
        }).then(resp => {
            if (resp.ok) {
                return resp.text()
            }
            throw 'Could not update dashboard'
        })
    }

    delete(id: string) {
        return fetch(`${api}/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.text()
            }
            throw 'Could not delete dashboard'
        })
    }

}