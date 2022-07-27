import React from 'react';
import {
    CriticalMonitor, LogicalExpression
} from "../dto/MonitorDto";
import LogicTree from "../component/LogicTree";
import {validateLogical} from "../validator/LogicalValidator";
import PredefinedRestClient from "../rest/PredefinedRestClient";
import DisplaySwitch from "../component/widget/DisplaySwitch";
import Dropdown from "../component/widget/Dropdown";
import {CPS} from "../dto/Cps";

export default class CriticalMonitorEditPage extends React.Component<Props, State> {

    state: State = {
        name: '',
        expression: this.emptyExpression(),
        customs: [],
        page: "expression"
    }

    customRest = new PredefinedRestClient()

    componentDidMount(): void {
        this.customRest.getCriticalMonitors(this.props.cps)
            .then(criticals => this.setState({customs: criticals}))
            .catch(err => console.error(err))

        if (this.props.monitor) {
            this.setState({
                name: this.props.monitor.name,
                expression: this.props.monitor.expression,
                custom: this.props.monitor.custom,
                onEvent: this.props.events.length > 0 ? this.props.monitor.onEvent : undefined,
                page: this.props.monitor.custom ? "custom" : "expression"
            })
        }
    }

    private emptyExpression(): LogicalExpression {
        return {
            expressions: [],
            operator: "AND",
            not: false,
            __type__: "exp"
        }
    }

    private expressionSwitch(page: Page) {
        let expression = this.state.expression
        if (page === "expression") {
            if (expression === undefined) {
                expression = this.emptyExpression()
            }
        }
        this.setState({page: page, expression: expression})
    }

    private savable() {
        if (this.state.name.length < 1 ||
            (this.state.page === "expression" && !validateLogical(this.state.expression!, this.props.cps)) ||
            (this.state.page === "custom" && this.state.custom === undefined)) {
            return false
        }
        return true
    }

    private submit() {
        const objective: CriticalMonitor = {
            name: this.state.name,
            expression: this.state.page === "expression" ? this.state.expression : undefined,
            custom: this.state.page === "custom" ? this.state.custom : undefined,
            onEvent: this.state.onEvent,
            __type__: "critical"
        };
        this.props.onSubmit(objective)
    }

    render () {
        return <div className="actionedit-page">
            <h3>Critical</h3>
            <div>
                <span style={{width: 100, display: 'inline-block'}}>Name: </span>
                <input type="text" value={this.state.name} onChange={e => this.setState({name: e.target.value})} />
            </div>

            {this.props.events.length > 0 && <>
                <div>
                    <span style={{width: 100, display: 'inline-block'}}>
                    <input
                    type="checkbox"
                    checked={this.state.onEvent !== undefined}
                    onChange={e => this.setState({onEvent: e.target.checked ? this.props.events[0] : undefined})} /> On Event:
                        </span>
                    {this.state.onEvent && <>
                        <Dropdown
                            value={this.state.onEvent}
                            items={this.props.events.map(e => ({label: e, value: e}))}
                            onChange={e => this.setState({onEvent: e})} />
                    </>}
                </div>
            </>}

            {(this.state.customs.length > 0 || this.state.custom) &&
                <DisplaySwitch
                    onChange={(p: Page) => this.expressionSwitch(p)}
                    items={[{title: 'Predefined', value: 'custom'}, {title: 'Expression', value: 'expression'}]}
                    value={this.state.page} />
            }

            {this.state.page === "expression" &&
                <div>
                    <LogicTree
                        cps={this.props.cps}
                        onChange={logical => this.setState({expression: logical as LogicalExpression})}
                        logical={this.state.expression!}/>
                </div>
            }

            {this.state.page === "custom" &&
                <div>
                    <Dropdown
                        value={this.state.custom}
                        items={this.state.customs.map(c => ({label: c, value: c}))}
                        onChange={c => this.setState({custom: c})} />
                </div>
            }

            <div style={{marginTop: 15}}>
                {this.savable() && <button className="button-blue" style={{marginRight: 5}} onClick={() => this.submit()}>Save</button>}
                <button className="button-grey" onClick={() => this.props.onCancel()}>Cancel</button>
            </div>
        </div>
    }
}

interface Props {
    monitor?: CriticalMonitor
    events: Array<string>
    cps: CPS
    onSubmit: (objective: CriticalMonitor) => void
    onCancel: () => void
}

interface State {
    name: string
    expression?: LogicalExpression
    customs: Array<string>
    custom?: string
    onEvent?: string
    page: Page
}

type Page = "expression" | "custom"