import React from 'react';
import {Dashboard} from "../dto/MonitorDto";
import DisplaySwitch from "../component/widget/DisplaySwitch";
import DashboardRestClient from "../rest/DashboardRestClient";
import DashboardPage from "./DashboardPage";
import WebSocketClient from "../rest/WebSocketClient";
import {Route, Switch} from "react-router-dom";
import {getUrlPath, navigate} from "../util/UrlPath";
//const AtomSpinner = require('@bit/bondz.react-epic-spinners.atom-spinner')

export default class MainPage extends React.Component<Props, State> {

    state: State = {
        loading: false,
        dashboards: []
    }
    monitorRestClient = new DashboardRestClient()

    componentDidMount(): void {
        //this.setState({loading: !this.props.ws.isReady()})
        this.props.ws.setConnectionChangeHandler(ready => {
            this.setState({loading: !ready})
        })

        this.monitorRestClient.getAll()
            .then(dashboardTuples => {
                const dashboards: Array<DashboardTuple> = Object.keys(dashboardTuples).map(k => ({id: k, name: dashboardTuples[k]}))
                this.setState({dashboards: dashboards})
                if (getUrlPath(window.location).dashboard === undefined && dashboards.length > 0) {
                    navigate(window.location, {
                        main: "DASHBOARD",
                        dashboard: dashboards[0].id
                    })
                }
            })
            .catch(err => console.error('Could not load dashboards from server', err))
    }

    private getDashboard() {
        const path = getUrlPath(window.location)
        return this.state.dashboards.find(m => m.id === path.dashboard)
    }

    render () {
        if (this.state.loading) {
            return <div style={{textAlign: "center", paddingTop: 15}}>
                {/*<AtomSpinner.AtomSpinner
                    color='#000000'
                    size='300'/>*/}
                    <div>Atomspinner</div>
            </div>
        }

        const dashboard = this.getDashboard()

        return <div style={{position: 'absolute', top: 68, left: 0, right: 0, bottom: 0}}>
            <DisplaySwitch
                onChange={m => navigate(window.location, {
                    main: "DASHBOARD",
                    dashboard: m!.id
                })}
                items={this.state.dashboards.map(m => ({title: m.name, value: m}))}
                value={dashboard} />
            {dashboard &&
            <DashboardPage
                key={dashboard.id!}
                ws={this.props.ws}/>
            }
        </div>
    }
}

interface Props {
    ws: WebSocketClient
}

interface State {
    dashboards: Array<DashboardTuple>
    loading: boolean
}

interface DashboardTuple {
    id: string
    name: string
}