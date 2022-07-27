import React from 'react';
import './GenericAction.css'
import {Action, InputParameterMapping} from "../dto/MonitorDto";
import Tile from "./widget/Tile";
import {metaDataMap} from "../metaobject/MetaObject";
import {CPS} from "../dto/Cps";
import {resolvePath} from "../metaobject/MetaObjectModel";

export default class GenericAction extends React.Component<Props, State> {

    state: State = {
        args: new Map<InputParameterMapping, any>()
    }

    componentDidMount() {
        const params = new Map<InputParameterMapping, any>()
        this.props.action.params.map(p => p as InputParameterMapping).filter(p => p.type).forEach(pm => {
            const meta = metaDataMap.get(this.props.cps)?.services
                .find(s => s.name === this.props.action.service)?.parameters
                .find(p => p.name === pm.parameter)
            params.set(pm, meta?.bounds?.defaultValue ?? "")
            //params.set(p, p.defaultValue ? p.defaultValue : "")
        })
        this.setState({args: params})
    }

    argChange(param: InputParameterMapping, value: any) {
        const params = this.state.args
        params.set(param, value)
        this.setState({args: params})
    }

    invoke() {
        const copy = new Map<string, any>(Array.from(this.state.args, ([k,v]) => [k.parameter, v]))
        const args = Object.fromEntries(copy)
        this.props.onInvoke(args)
    }

    getInput(param: InputParameterMapping, value: any) {
        const meta = metaDataMap.get(this.props.cps)?.services
            .find(s => s.name === this.props.action.service)?.parameters
            .find(p => p.name === param.parameter)
        if (param.type === "TEXT") {
            return <input
                type="text"
                value={value}
                onKeyDown={e => e.key === 'Enter' && this.invoke()}
                onChange={e => this.argChange(param, e.target.value)} />
        } else if (param.type === "SLIDER") {
            return <React.Fragment>
                <input
                    type="range"
                    min={meta?.bounds?.min}
                    max={meta?.bounds?.max}
                    step={meta?.bounds?.step}
                    value={value}
                    onChange={e => this.argChange(param, Number(e.target.value))} />
                <input
                    type="number"
                    min={meta?.bounds?.min}
                    max={meta?.bounds?.max}
                    step={meta?.bounds?.step}
                    value={value}
                    onKeyDown={e => e.key === 'Enter' && this.invoke()}
                    onChange={e => this.argChange(param, Number(e.target.value))} />
            </React.Fragment>
        } else if (param.type === "DROPDOWN") {
            return <div>TODO: Dropdown</div>
        }
    }

    render () {
        return <Tile style={{width: 300, height: 'auto', backgroundColor: 'darkorange'}} description={this.props.action.name}>
            {Array.from(this.state.args, ([k, v]) => ({param: k, value: v})).map(kv =>
                <div className="action-arg">
                    <div>{kv.param.parameter}:</div>
                    {this.getInput(kv.param, kv.value)}
                </div>
            )}
            <button className="button-blue" onClick={() => this.invoke()}>{this.props.action.name}</button>
        </Tile>
    }
}

interface Props {
    action: Action
    cps: CPS
    onInvoke: (args: object) => void
}
interface State {
    args: Map<InputParameterMapping, any>
}
