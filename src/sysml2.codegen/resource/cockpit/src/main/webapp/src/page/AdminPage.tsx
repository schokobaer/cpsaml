import React from 'react';
import "./AdminPage.css"
import {
    Action,
    CriticalMonitor,
    Dashboard, EventSpecification, FixedValueParameterMapping, InputParameterMapping, Monitor, NotificationType,
    PropertyMonitor
} from "../dto/MonitorDto";
import ActionEditPage from "./ActionEditPage";
import DashboardCreateDialog from "../component/DashboardCreateDialog";
import DashboardRestClient from "../rest/DashboardRestClient";
import Dropdown from "../component/widget/Dropdown";
import PropertyMonitorEditPage from "./PropertyMonitorEditPage";
import CriticalMonitorEditPage from "./CriticalMonitorEditPage";
import Tile from "../component/widget/Tile";
import DisplaySwitch from "../component/widget/DisplaySwitch";
import {Route, Switch} from "react-router-dom";
import {getUrlPath, navigate} from "../util/UrlPath";
import EventSpecificationEditPage from "./EventSpecificationEditPage";
import ListItem from "../component/widget/ListItem";
import {CPS} from "../dto/Cps";

export default class AdminPage extends React.Component<Props, State> {

    state: State = {
        dashboards: [],
        edit: {visible: false},
        createDashboard: false,
        page: "prop"
    }

    rest = new DashboardRestClient()

    componentDidMount(): void {
        this.loadDashboards()
        const dashboard = getUrlPath(window.location).dashboard
        if (dashboard) {
            this.rest.get(dashboard)
                .then(dbrd => this.setState({dashboard: dbrd}))
                .catch(err => console.error(err))
        }
    }

    private loadDashboards() {
        this.rest.getAll()
            .then(dashboardTuples => {
                const dashboards: Array<DashboardTuple> = Object.keys(dashboardTuples).map(k => ({id: k, name: dashboardTuples[k]}))
                this.setState({dashboards: dashboards})
                if (getUrlPath(window.location).dashboard === undefined) {
                //if (this.state.dashboard === undefined && dashboards.length > 0) {
                    navigate(window.location, {
                        main: "ADMIN",
                        dashboard: dashboards[0].id
                    })
                    this.rest.get(dashboards[0].id)
                        .then(dbrd => this.setState({dashboard: dbrd}))
                        .catch(err => console.error(err))
                }
            })
            .catch(err => console.error(err))
    }

    private createDashboard(name: string, cps: CPS, instance?: string) {
        const dashboard: Dashboard = {
            name: name,
            cps: cps,
            instance: instance,
            notificationType: "NONE",
            actions: [],
            monitors: [],
            eventSpecifications: []
        }
        this.rest.create(dashboard)
            .then(id => {
                const dashboards = this.state.dashboards
                dashboard.id = id
                dashboards.push({id: id, name: name})
                this.setState({dashboards: dashboards, dashboard: dashboard})
                navigate(window.location, {
                    main: "ADMIN",
                    dashboard: id
                })
            })
        this.setState({createDashboard: false})
    }

    private deleteDashboard() {
        if (!this.state.dashboard) return;
        const dashboard = this.state.dashboard
        if (!window.confirm(`You want to delete dashboard '${dashboard.name}?`)) return;
        const dashboards = this.state.dashboards.filter(m => m.id !== dashboard.id)
        this.setState({dashboards: dashboards})
        this.rest.delete(dashboard.id!)
            .then(() => {
                /*this.rest.getAll()
                    .then(dashboards => this.setState({dashboards}))
                    .catch(err => console.error(err))*/
                navigate(window.location, {
                    main: "ADMIN"
                })
            })
    }

    private actionSubmit(action: Action) {
        console.info("Submitted an action", action)
        const dashboard = this.state.dashboard!
        dashboard.actions = dashboard.actions.filter(a => a.name !== action.name)
        dashboard.actions.push(action)
        this.setState({edit: {visible: false}, dashboard: dashboard})
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => {
                        this.setState({dashboards: monitors})
                    })*/
            })
    }

    private monitorSubmit(objective: Monitor) {
        console.info("Submitted an dashboard", objective)
        const dashboard = this.state.dashboard!
        dashboard.monitors = dashboard.monitors.filter(o => o.name !== objective.name)
        dashboard.monitors.push(objective)
        this.setState({edit: {visible: false}, dashboard: dashboard})
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => {
                        this.setState({dashboards: monitors})
                    })*/
            })
    }

    private eventRuleSubmit(eventRule: EventSpecification) {
        console.info("Submitted an dashboard", eventRule)
        const dashboard = this.state.dashboard!
        dashboard.eventSpecifications = dashboard.eventSpecifications.filter(er => er.name !== eventRule.name)
        dashboard.eventSpecifications.push(eventRule)
        this.setState({edit: {visible: false}, dashboard: dashboard})
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => {
                        this.setState({dashboards: monitors})
                    })*/
            })
    }

    private actionDelete(action: Action) {
        if (!window.confirm(`You want to delete Action '${action.name}'?`)) return;

        const dashboard = this.state.dashboard!
        dashboard.actions = dashboard.actions.filter(a => a !== action)
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => this.setState({dashboards: monitors}))*/
            })
            .catch(err => console.error(err))
        this.setState({dashboard: dashboard})
    }

    private monitorDelete(objective: Monitor) {
        if (!window.confirm(`You want to delete Objective '${objective.name}'?`)) return;

        const dashboard = this.state.dashboard!
        dashboard.monitors = dashboard.monitors.filter(o => o.name !== objective.name)
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => this.setState({dashboards: monitors}))*/
            })
            .catch(err => console.error(err))
        this.setState({dashboard: dashboard})
    }

    private eventRuleDelete(eventRule: EventSpecification) {
        if (!window.confirm(`You want to delete Event '${eventRule.name}'?`)) return;

        const dashboard = this.state.dashboard!
        dashboard.eventSpecifications = dashboard.eventSpecifications.filter(er => er.name !== eventRule.name)
        this.rest.update(dashboard)
            .then(() => {
                /*this.rest.getAll()
                    .then(monitors => this.setState({dashboards: monitors}))*/
            })
            .catch(err => console.error(err))
        this.setState({dashboard: dashboard})
    }

    render () {
        if (this.state.edit.visible) {
            const dashboard = this.state.dashboard!
            if (this.state.page === "action") {
                return <ActionEditPage
                    action={this.state.edit.obj as Action}
                    cps={dashboard.cps}
                    onCancel={() => this.setState({edit: {visible: false}})}
                    onSubmit={action => this.actionSubmit(action)}/>
            } else if (this.state.page === "prop") {
                return <PropertyMonitorEditPage
                    monitor={this.state.edit.obj as PropertyMonitor}
                    cps={dashboard.cps}
                    onSubmit={obj => this.monitorSubmit(obj)}
                    onCancel={() => this.setState({edit: {visible: false}})} />
            } else if (this.state.page === "event") {
                return <EventSpecificationEditPage
                    cps={dashboard.cps}
                    eventSpec={this.state.edit.obj as EventSpecification}
                    onSubmit={er => this.eventRuleSubmit(er)}
                    onCancel={() => this.setState({edit: {visible: false}})} />
            } else if (this.state.page === "critical") {
                return <CriticalMonitorEditPage
                    cps={dashboard.cps}
                    monitor={this.state.edit.obj as CriticalMonitor}
                    events={dashboard.eventSpecifications.map(spec => spec.name)}
                    onSubmit={obj => this.monitorSubmit(obj)}
                    onCancel={() => this.setState({edit: {visible: false}})} />
            }
        }

        if (this.state.createDashboard) {
            return <DashboardCreateDialog
                onCreate={(name, cps, instance) => this.createDashboard(name, cps, instance)}
                onCancel={() => this.setState({createDashboard: false})} />
        }

        const dashboard = this.state.dashboard

        return <div className="admin-page">
            <div className="admin-select-ct">
                <span style={{}}>Dashboard:</span>
                <Dropdown
                    //items={this.state.dashboards.map(moni => ({value: moni.id, label: moni.name + (moni.cps !== undefined ? (" (" + moni.cps + (moni.instance ? "[" + moni.instance + "])" : ")")) : "")}))}
                    items={this.state.dashboards.map(dbrd => ({value: dbrd.id, label: dbrd.name}))}
                    value={dashboard?.id}
                    onChange={d => {
                        navigate(window.location, {
                            main: "ADMIN",
                            dashboard: d
                        })
                        this.rest.get(d)
                            .then(dbrd => this.setState({dashboard: dbrd}))
                            .catch(err => console.error(err))
                    }} />
                <div className="admin-select-button-ct">
                    {/*<span onClick={() => this.setState({createDashboard: true})}>➕</span>
                    <span onClick={() => this.deleteDashboard()}>➖</span>*/}
                    <button className="button-red" onClick={() => this.deleteDashboard()}>Delete</button>
                    <button className="button-green" style={{marginLeft: 10}} onClick={() => this.setState({createDashboard: true})}>New</button>
                </div>
            </div>

            <Switch>
                <Route path="/admin/:id">
                    {dashboard && <div style={{padding: 15}}>

                        <div className="admin-notification-ct">
                            <span>Additional Notification:</span>
                            <Dropdown value={dashboard.notificationType} items={[
                                {label: 'None', value: "NONE"},
                                {label: 'Email', value: "EMAIL"},
                                {label: 'Sms', value: "SMS"}
                            ]} onChange={v => {
                                const dashboard = this.state.dashboard!
                                dashboard.notificationType = v as NotificationType
                                this.setState({dashboard: dashboard})
                                this.rest.update(dashboard)
                                    .catch(err => console.error(err))
                            }} />
                        </div>

                        <DisplaySwitch
                            onChange={(page: Page) => this.setState({page: page})}
                            items={[
                                {title: 'Property', value: "prop"},
                                {title: 'Critical', value: "critical"},
                                {title: 'Event', value: "event"},
                                {title: 'Action', value: "action"}
                            ]}
                            value={this.state.page} />

                            <div style={{display: 'flex', flexDirection: 'row'}}>
                                <div style={{marginRight: 15}}>
                                    <button className="button-green" onClick={() => this.setState({edit: {visible: true}})}>Add</button>
                                </div>

                                <div className="admin-list-ct">
                                {this.state.page === "prop" && dashboard.monitors
                                    .filter(o => o.__type__ === "prop" || o.__type__ === "agg")
                                    .map(o => o as PropertyMonitor)
                                    .map(o =>
                                        /*<Tile
                                            key={o.name}
                                            onDelete={() => this.monitorDelete(o)}
                                            onClick={() => this.setState({edit: {visible: true, obj: o}})}
                                            description={o.name}>
                                            {o.representation}
                                        </Tile>*/
                                        <ListItem
                                            key={o.name}
                                            onClick={() => this.setState({edit: {visible: true, obj: o}})}
                                            onDelete={() => this.monitorDelete(o)}>
                                            {o.name} : {o.representation}
                                        </ListItem>
                                    )}

                                {this.state.page === "critical" && dashboard.monitors
                                    .filter(o => o.__type__ === "critical")
                                    .map(o => o as CriticalMonitor)
                                    .map(o =>
                                        <ListItem
                                            key={o.name}
                                            style={{backgroundColor: 'darkred'}}
                                            onClick={() => this.setState({edit: {visible: true, obj: o}})}
                                            onDelete={() => this.monitorDelete(o)}>
                                            {o.name}
                                        </ListItem>
                                    )}

                                {this.state.page === "event" && dashboard.eventSpecifications
                                    .map(er =>
                                        /*<Tile
                                            style={{backgroundColor: 'green'}}
                                            key={er.name}
                                            description="Event"
                                            onDelete={() => this.eventRuleDelete(er)}
                                            onClick={() => this.setState({edit: {visible: true, obj: er}})}>
                                            {er.name}
                                        </Tile>*/
                                        <ListItem
                                            key={er.name}
                                            style={{backgroundColor: 'darkgreen'}}
                                            onClick={() => this.setState({edit: {visible: true, obj: er}})}
                                            onDelete={() => this.eventRuleDelete(er)}>
                                            {er.name}
                                        </ListItem>
                                    )}

                                {this.state.page === "action" && dashboard.actions.map(a =>
                                    /*<Tile
                                        key={a.name}
                                        style={{backgroundColor: 'orange'}}
                                        onClick={() => this.setState({edit: {visible: true, obj: a}})}
                                        onDelete={() => this.actionDelete(a)}
                                        description="Action">
                                        {a.name}
                                    </Tile>*/
                                    <ListItem
                                        key={a.name}
                                        style={{backgroundColor: 'orange'}}
                                        onClick={() => this.setState({edit: {visible: true, obj: a}})}
                                        onDelete={() => this.actionDelete(a)}>
                                        {a.name}: {a.service}({a.params
                                        .map(p => p.parameter + (p.__type__ === "fixed" ? '=' + (p as FixedValueParameterMapping).value : ':' + (p as InputParameterMapping).type))
                                        .join(', ')})
                                    </ListItem>
                                )}
                            </div>
                        </div>
                    </div>}
                </Route>
            </Switch>

        </div>
    }
}

interface Props {

}
interface State {
    dashboard?: Dashboard
    dashboards: Array<DashboardTuple>
    edit: {
        visible: boolean
        obj?: Monitor | Action | EventSpecification
    }
    createDashboard: boolean
    page: Page
}

type Page = "prop" | "critical" | "action" | "event"

interface DashboardTuple {
    id: string
    name: string
}