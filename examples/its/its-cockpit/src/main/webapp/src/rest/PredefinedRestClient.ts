import {Pair} from "../dto/CpsDto";
import {PredefinedPropertyMonitorSpec, DataType} from "../dto/PredefinedPropertyMonitorSpec";
import {CPS} from "../dto/Cps";

const api = '/api/predefined'
export default class PredefinedRestClient {

    getPropertyMonitors(cps: CPS): Promise<Array<PredefinedPropertyMonitorSpec>> {
        let path = api + '/monitor/property?cps=' + cps
        return fetch(path, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
            cache: 'no-cache'
        }).then(resp => {
            if (resp.ok) {
                return resp.json().then(data => {
                    return data as Array<PredefinedPropertyMonitorSpec>
                })
            }
            throw 'Could not load custom property dashboards'
        })
    }

    getCriticalMonitors(cps: CPS): Promise<Array<string>> {
        let path = api + '/monitor/critical?cps=' + cps
        return fetch(path, {
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
            throw 'Could not load custom critical dashboards'
        })
    }

    getEventSpecifications(cps: CPS): Promise<Array<string>> {
        let path = api + '/eventspec?cps=' + cps
        return fetch(path, {
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
            throw 'Could not load custom critical dashboards'
        })
    }

    getSeverities(dataType: DataType, cps: CPS): Promise<Array<string>> {
        let path = api + `/severity?list=${dataType.list}&type=${dataType.type}&cps=${cps}`
        return fetch(path, {
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
            throw 'Could not load custom severities'
        })
    }
}