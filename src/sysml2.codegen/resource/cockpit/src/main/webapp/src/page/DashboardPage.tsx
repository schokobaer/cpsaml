import React from 'react';
import WebSocketClient from "../rest/WebSocketClient";
import "./DashboardPage.css"
import {Dashboard } from "../dto/MonitorDto";
import GenericRestClient from "../rest/GenericRestClient";
import Dropdown from "../component/widget/Dropdown";
import DashboardInstancePage from "./DashboardInstancePage";
import {getUrlPath, navigate} from "../util/UrlPath";
import DashboardRestClient from "../rest/DashboardRestClient";
//const AtomSpinner = require('@bit/bondz.react-epic-spinners.atom-spinner')

export default class DashboardPage extends React.Component<Props, State> {

    state: State = {
        instances: [],
        refreshing: false
    }

    rest = new GenericRestClient()
    dashboardRest = new DashboardRestClient()

    componentDidMount(): void {
        const path = getUrlPath(window.location)
        if (path.dashboard) {
            this.dashboardRest.get(path.dashboard)
                .then(dashboard => {
                    this.setState({dashboard: dashboard})
                    if (dashboard.instance === undefined) {
                        this.refreshInstnces()
                    }
                })
                .catch(err => console.error(err))
        }
    }

    private refreshInstnces() {
        this.setState({refreshing: true})
        this.rest.listAvailableInstances(this.state.dashboard!.cps)
            .then(data => {
                this.setState({instances: data})
                getUrlPath(window.location).instance === undefined && data.length > 0 && this.instanceSelected(data[0])
            })
            .catch(err => console.error(err))
            .finally(() => this.setState({refreshing: false}))
    }

    private instanceSelected(id: string): void {
        console.info("Instance selected: ", id)
        navigate(window.location, {
            main: "DASHBOARD",
            dashboard: this.state.dashboard!.id,
            instance: id
        })
    }

    render () {
        const dashboard = this.state.dashboard
        if (dashboard === undefined) {
            return <div style={{textAlign: "center", paddingTop: 15}}>
                {/*<AtomSpinner.AtomSpinner
                    color='#000000'
                    size='300'/>*/}
                <div>AtomSpinner</div>
            </div>
        }

        const instance = getUrlPath(window.location).instance
        return <div className="dashboard-page">
            {dashboard.cps && dashboard.instance === undefined &&
            <React.Fragment>
                <div className="dashboard-select-ct">
                    <span>Instance:</span>
                    <Dropdown
                        value={instance}
                        items={this.state.instances.map(i => ({label: i, value: i}))}
                        onChange={i => this.instanceSelected(i)} />
                    <img className={"" + (this.state.refreshing && "rotating")} src="/img/animation/refresh.png"
                         onClick={() => this.refreshInstnces()}/>
                </div>
                {instance &&
                <DashboardInstancePage key={instance} dashboard={dashboard} ws={this.props.ws} /> }
            </React.Fragment>
            }
            {(dashboard.cps === undefined || dashboard.instance) &&
            <DashboardInstancePage key={dashboard.instance} dashboard={dashboard} ws={this.props.ws} />
            }
        </div>
    }
}

interface Props {
    ws: WebSocketClient
}
interface State {
    dashboard?: Dashboard
    instances: Array<string>
    refreshing: boolean
}