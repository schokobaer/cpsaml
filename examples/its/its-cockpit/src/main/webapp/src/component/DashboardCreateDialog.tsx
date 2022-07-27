import React, { Fragment } from 'react';
import Dropdown, {DropdownItem} from "./widget/Dropdown";
import GenericRestClient from "../rest/GenericRestClient";
import './DashboardCreateDialog.css'
import {CPS, CPS_VALUES} from "../dto/Cps";

const toFirstUpper = (str: string) => str[0].toUpperCase() + str.substr(1).toLowerCase()

export default class DashboardCreateDialog extends React.Component<Props, State> {

    state: State = {
        name: '',
        instances: [],
        cps: CPS_VALUES[0]
    }
    rest = new GenericRestClient()

    cpsChange(cps: CPS) {
        this.setState({cps: cps})
        if (cps) {
            this.rest.listAvailableInstances(cps)
                .then(instances => this.setState({instances: instances}))
                .catch(err => console.error(err))
        } else {
            this.setState({instances: [], instance: undefined})
        }
    }

    render () {

        return <div className="dashboard-create-ct">
            <h2>New Dashboard</h2>
            <div>
                <span>Name:</span>
                <input type="text" value={this.state.name} onChange={e => this.setState({name: e.target.value}) } />
            </div>
            <div>
                <span>CPS:</span>
                <Dropdown
                    items={CPS_VALUES.map(cps => ({value: cps, label: toFirstUpper(cps)}))}
                    value={this.state.cps}
                    onChange={cps => this.cpsChange(cps)} />
            </div>
            {this.state.cps !== CPS_VALUES[0] &&
            <div className="dashboard-create-instance-ct">
                <span>Instance:</span>
                <input type="text" value={this.state.instance} onChange={e => this.setState({instance: e.target.value})}/>
                <Dropdown
                    items={this.state.instances.map(i => ({label: i, value: i}))}
                    onChange={i => this.setState({instance: i})} />
            </div>
            }
            <div className="dashboard-create-button-ct">
                <button className="button-blue" onClick={() => this.props.onCreate(
                    this.state.name,
                    this.state.cps,
                    this.state.cps === CPS_VALUES[0] ? CPS_VALUES[0] : this.state.instance
                    )}>Create</button>
                <button className="button-grey" onClick={() => this.props.onCancel()}>Cancel</button>
            </div>
        </div>
    }
}

interface Props {
    onCreate: (name: string, cps: CPS, instance?: string) => void
    onCancel: () => void
}
interface State {
    name: string
    cps: CPS
    instance?: string
    instances: Array<string>
}