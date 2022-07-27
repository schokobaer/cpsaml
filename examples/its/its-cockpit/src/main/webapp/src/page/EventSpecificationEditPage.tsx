import React from 'react';
import {
    CriticalMonitor, EventSpecification, LogicalExpression
} from "../dto/MonitorDto";
import LogicTree from "../component/LogicTree";
import {validateLogical} from "../validator/LogicalValidator";
import PredefinedRestClient from "../rest/PredefinedRestClient";
import DisplaySwitch from "../component/widget/DisplaySwitch";
import Dropdown from "../component/widget/Dropdown";
import {CPS} from "../dto/Cps";

export default class EventSpecificationEditPage extends React.Component<Props, State> {

    state: State = {
        name: '',
        before: this.emptyExpression(),
        after: this.emptyExpression(),
        customs: [],
        page: "expression"
    }

    customRest = new PredefinedRestClient()

    componentDidMount(): void {
        this.customRest.getEventSpecifications(this.props.cps)
            .then(customs => this.setState({customs: customs, custom: customs[0]}))
            .catch(err => console.error(err))

        if (this.props.eventSpec) {
            this.setState({
                name: this.props.eventSpec.name,
                before: this.props.eventSpec.before,
                after: this.props.eventSpec.after,
                custom: this.props.eventSpec.custom,
                page: this.props.eventSpec.custom ? "custom" : "expression"
            })
        }
    }

    private savable() {
        if (this.state.name.length < 1) {
            return false
        }
        if (this.state.before && this.state.after && (
            !validateLogical(this.state.before, this.props.cps) ||
            !validateLogical(this.state.after, this.props.cps))) {
            return false
        }

        return true
    }

    private submit() {
        const eventSpec: EventSpecification = {
            name: this.state.name,
            before: this.state.page === "expression" ? this.state.before : undefined,
            after: this.state.page === "expression" ? this.state.after : undefined,
            custom: this.state.page === "custom" ? this.state.custom : undefined
        };
        this.props.onSubmit(eventSpec)
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
        let before = this.state.before
        let after = this.state.after
        if (page === "expression") {
            if (before === undefined) {
                before = this.emptyExpression()
            }
            if (after === undefined) {
                after = this.emptyExpression()
            }
        }
        this.setState({page: page, before: before, after: after})
    }

    render () {
        return <div className="actionedit-page">
            <h3>Event</h3>
            <div>
                Name: <input type="text" value={this.state.name} onChange={e => this.setState({name: e.target.value})} />
            </div>

            {(this.state.customs.length > 0 || this.state.custom) &&
            <DisplaySwitch
                onChange={(p: Page) => this.expressionSwitch(p)}
                items={[{title: 'Predefined', value: 'custom'}, {title: 'Expression', value: 'expression'}]}
                value={this.state.page} />
            }

            {this.state.page === "expression" && <>
                <div>
                    <h4>Before Condition:</h4>
                    <LogicTree
                        cps={this.props.cps}
                        onChange={logical => this.setState({before: logical as LogicalExpression})}
                        logical={this.state.before!}/>
                </div>

                <div>
                    <h4>After Condition:</h4>
                    <LogicTree
                        cps={this.props.cps}
                        onChange={logical => this.setState({after: logical as LogicalExpression})}
                        logical={this.state.after!}/>
                </div>
            </>}

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
    eventSpec?: EventSpecification
    cps: CPS
    onSubmit: (eventSpec: EventSpecification) => void
    onCancel: () => void
}

interface State {
    name: string
    customs: Array<string>
    before?: LogicalExpression
    after?: LogicalExpression
    custom?: string
    page: Page
}

type Page = "expression" | "custom"