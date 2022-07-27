import React, {CSSProperties} from 'react';
import WebSocketClient, {BroadcastMessage} from "../rest/WebSocketClient";
import "./DashboardPage.css"
import {
    Action, AggregationMonitor, CriticalMonitor,
    FixedValueParameterMapping,
    Dashboard,
    PropertyMonitor
} from "../dto/MonitorDto";
import GenericAction from "../component/GenericAction";
import GenericRestClient from "../rest/GenericRestClient";
import GenericPropertyValue from "../component/GenericPropertyValue";
import {CpsDto} from "../dto/CpsDto";
import GraphTile from "../component/GraphTile";
import Tile from "../component/widget/Tile";
import {getUrlPath} from "../util/UrlPath";
import {LogDto} from "../dto/LogDto";
import LogConsole from "../component/LogConsole";
import {formatTimestamp, valueByKey} from "../util/KeyValueUtils";
import CriticalBoard from "../component/CriticalBoard";
import "./DashboardInstancePage.css"


//const bcc = new BroadcastChannel('cps')

export default class DashboardInstancePage extends React.Component<Props, State> {

    state: State = {
        offline: false,
        logs: []
    }

    rest = new GenericRestClient()
    bcc: BroadcastChannel = new BroadcastChannel('cps')
    logBcc = new BroadcastChannel('log')

    componentDidMount(): void {
        const instanceid = this.getInstanceId()
        this.rest.getInstance(this.props.dashboard.id!, instanceid!)
            .then(response => this.setState({instance: response}))
            .catch(err => console.error(err))
        this.rest.getLogs(this.props.dashboard.id!, instanceid!)
            .then(response => this.setState({logs: response}))
            .catch(err => console.error(err))
        this.props.ws.subscribeCps(this.props.dashboard.id!, instanceid)

        //bcc.onmessage = (e: MessageEvent) => {
        this.bcc.onmessage = (e: MessageEvent) => {
            const path = getUrlPath(window.location)
            const msg = e.data as BroadcastMessage
            if (msg.monitorId === this.props.dashboard.id && msg.payload.id === instanceid && this.state.selectedLog === undefined) {
                if (msg.type === "UPDATE") {
                    this.setState({instance: msg.payload, offline: false})
                } else if (msg.type === "OFFLINE") {
                    this.setState({offline: true})
                }
            }
        }

        this.logBcc.onmessage = (e: MessageEvent) => {
            const path = getUrlPath(window.location)
            const instanceId = path.instance ?? this.props.dashboard.instance
            const log = e.data as LogDto
            if (path.dashboard === log.monitor && instanceId === log.id) {
                const logs = this.state.logs
                logs.push(log)
                this.setState({logs: logs})
            }
        }
    }

    private getInstanceId(): string {
        const urlpath = getUrlPath(window.location)
        return urlpath.instance ?? this.props.dashboard.instance!
    }

    componentWillUnmount(): void {
        this.props.ws.unsubscribeCps(this.props.dashboard.id!, getUrlPath(window.location).instance)
        this.bcc.close()
        this.logBcc.close()
    }

    invokeAction(action: Action, args: object) {
        const fixedArgs = Object.fromEntries(action.params
            .filter(p => (p as FixedValueParameterMapping).value !== undefined)
            .map(p => [p.parameter, (p as FixedValueParameterMapping).value]))
        const req: object = {
            ...args, ...fixedArgs
        }
        console.info(`Should invoke service ${action.service} with args: `, req)
        this.rest.invokeAction(this.props.dashboard.cps, action.service, req, this.state.instance!.id, this.props.dashboard.id!)
    }

    private logSelected(log?: LogDto) {
        this.setState({selectedLog: log})

        if (log) {
            this.rest.getHistoryData(log.monitor, log.id, log.timestamp)
                .then(data => this.setState({instance: data}))
                .catch(err => console.error(err))
        } else {
            this.rest.getInstance(this.props.dashboard.id!, this.getInstanceId())
                .then(response => this.setState({instance: response}))
                .catch(err => console.error(err))
        }
    }

    render () {
        return <div>
            <div className="instance-header">
                <h4>{this.state.selectedLog ? "History: " + formatTimestamp(this.state.selectedLog) : this.state.offline ? "Offline" : "Live"}</h4>
                {this.state.selectedLog &&
                    <button className="button-grey" onClick={() => this.logSelected()}>Reset</button>
                }
            </div>

            <div className={"dashboard-instance-ct" + (this.state.offline ? " instance-offline" : "")}>
                <div>
                    <h4>Data</h4>
                    <div>
                        {this.props.dashboard.monitors
                            .filter(o => o.__type__ === "prop" || o.__type__ === "agg")
                            .map(o =>
                                o.__type__ === "prop" && (o as PropertyMonitor).representation === "GRAPH" ?
                                    <GraphTile description={o.name} data={valueByKey(this.state.instance, o.name) ?? []} />

                                    :
                                    <GenericPropertyValue
                                        objective={o as PropertyMonitor}
                                        cps={this.props.dashboard.cps}
                                        insance={this.state.instance!} />
                            )}
                    </div>
                </div>

                <div className="action-ct">
                    <h4>Actions</h4>
                    <div>
                        {this.props.dashboard.actions.map(a =>
                            <GenericAction
                                action={a}
                                cps={this.props.dashboard.cps}
                                onInvoke={(args: object) => this.invokeAction(a, args)} />
                        )}
                    </div>
                </div>

                <div style={{display: 'flex', flexDirection: 'column'}}>
                    {this.props.dashboard.monitors.filter(m => m.__type__ === "critical").length > 0 &&
                        <CriticalBoard
                            objectives={this.props.dashboard.monitors.filter(o => o.__type__ === "critical").map(o => o as CriticalMonitor)}
                            criticals={this.state.instance?.criticals ?? {}}/>
                    }
                    <LogConsole
                        selected={this.state.selectedLog}
                        onSelect={log => this.logSelected(log)}
                        logs={this.state.logs} />
                </div>
            </div>
        </div>
    }
}

interface Props {
    dashboard: Dashboard
    ws: WebSocketClient
}
interface State {
    instance?: CpsDto
    selectedLog?: LogDto
    logs: Array<LogDto>
    offline: boolean
}