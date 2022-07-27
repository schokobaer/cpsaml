import {CpsDto} from "../dto/CpsDto";
import {LogDto} from "../dto/LogDto";
import {CPS} from "../dto/Cps";

const api = '/api/cps'
export default class GenericRestClient {

    listAvailableInstances(cps: CPS): Promise<Array<string>> {
        return fetch(`${api}/${cps.toLowerCase()}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as Array<string>
                })
            }
            throw 'Could not load available instances'
        })
    }


    getInstance(dashboard: string, id: string): Promise<CpsDto> {
        let path = `${api}/${dashboard}/${id}`
        return fetch(path, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as CpsDto
                })
            }
            throw 'Could not load its data'
        })
    }

    invokeAction(cps: CPS, service: string, args: object, id: string, dashboardId: string) {
        const path = `${api}/${cps.toLowerCase()}/${id}/${service}`
        return fetch(path, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'dashboard': dashboardId
            },
            cache: 'no-cache',
            body: JSON.stringify(args)
        }).then(resp => {
            if (resp.ok) {
                return ""
            }
            throw 'Could not invoke action'
        })
    }

    getLogs(monitorId: string, instanceId: string): Promise<Array<LogDto>> {
        const path = `/api/log/${monitorId}/${instanceId}`
        return fetch(path, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as Array<LogDto>
                })
            }
            throw 'Could not get logs'
        })
    }

    getHistoryData(dashboardId: string, instanceId: string, timestamp: string): Promise<CpsDto> {
        const path = `/api/log/${dashboardId}/${instanceId}/${timestamp}`
        return fetch(path, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as CpsDto
                })
            }
            throw 'Could not get history entry'
        })
    }

}