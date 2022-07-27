import React from 'react';
import "./ActionEditPage.css"
import {
    Action,
    FixedValueParameterMapping,
    InputParameterMapping, InputType,
    Dashboard,
    ParameterMapping
} from "../dto/MonitorDto";
import Select from "react-select";
import {metaDataMap} from "../metaobject/MetaObject";
import Dropdown from "../component/widget/Dropdown";
import {CPS} from "../dto/Cps";
import {Parameter, Service} from "../metaobject/MetaObjectModel";

export default class ActionEditPage extends React.Component<Props, State> {

    state: State = {
        name: '',
        params: new Map<Parameter, ParameterConfiguration>()
    }

    componentDidMount(): void {
        if (this.props.action) {
            const state: State = {
                name: this.props.action.name,
                service: metaDataMap.get(this.props.cps)!.services.find(s => s.name === this.props.action!.service),
                params: new Map<Parameter, ParameterConfiguration>()
            }
            state.service?.parameters.forEach(p => {
                const pm = this.props.action?.params.find(pm => pm.parameter === p.name)
                if (pm) {
                    state.params.set(p, {
                        enabled: true,
                        type: pm.__type__ === "input" ? "INPUT" : "FIXED",
                        input: pm.__type__ === "input" ? (pm as InputParameterMapping).type : "TEXT",
                        value: pm.__type__ === "fixed" ? (pm as FixedValueParameterMapping).value : ''
                    })
                } else {
                    state.params.set(p, {
                        enabled: false,
                        type: "INPUT",
                        input: this.isNumber(p) ? "SLIDER" : "TEXT",
                        value: ''
                    })
                }
            })
            this.setState(state)
        }
    }

    serviceSelect(svc: Service) {
        const map = this.state.params
        map.clear()
        svc.parameters.forEach(p => {
            map.set(p, {
                enabled: !p.optional,
                type: "INPUT",
                input: this.isNumber(p) ? "SLIDER" : "TEXT",
                value: ''
            })
        })
        const state = this.state
        if (state.service === undefined) {
            state.name = svc.name
        }
        state.service = svc
        state.params = map
        this.setState(state)
    }

    isNumber(param: Parameter) {
        return param.type === "INT" || param.type === "FLOAT"
    }

    enabledChange(param: Parameter, e: React.ChangeEvent<HTMLInputElement>) {
        const map = this.state.params
        map.get(param)!.enabled = e.target.checked
        this.setState({params: map})
    }

    mappingTypeChange(param: Parameter, v: MappingType) {
        const map = this.state.params
        map.get(param)!.type = v
        this.setState({params: map})
    }

    valueChange(param: Parameter, e: React.ChangeEvent<HTMLInputElement>) {
        const map = this.state.params
        map.get(param)!.value = this.isNumber(param) ? Number(e.target.value) : e.target.value
        this.setState({params: map})
    }

    inputChange(param: Parameter, v: InputType) {
        const map = this.state.params
        map.get(param)!.input = v
        this.setState({params: map})
    }

    private savable(): boolean {
        return this.state.name.length > 0 && this.state.service !== undefined
    }

    submit() {
        if (this.state.name.length < 2 || this.state.service === undefined) return

        const action: Action = {
            name: this.state.name,
            service: this.state.service!.name,
            params: Array.from(this.state.params, ([k, v]) => ({param: k, config: v}))
                .filter(kv => kv.config.enabled)
                .map(kv => kv.config.type === "INPUT" ?
                    {
                        parameter: kv.param.name,
                        type: kv.config.input,
                        __type__: "input"
                    } as InputParameterMapping :
                    {
                        parameter: kv.param.name,
                        value: kv.config.value,
                        __type__: "fixed"
                    } as FixedValueParameterMapping)
        }
        this.props.onSubmit(action)
    }

    render () {

        const serviceOptions = metaDataMap.get(this.props.cps)!.services.map(svc => {
            return {value: svc, label: svc.name}
        })

        return <div className="actionedit-page">
            <h3>Action</h3>
            <div>
                <span style={{width: 100, display: 'inline-block'}}>Name: </span>
                <input type="text" value={this.state.name} onChange={e => this.setState({name: e.target.value})} />
            </div>
            <div className="service-select-ct">
                <span style={{width: 100, display: 'inline-block'}}>Service: </span>
                <Dropdown items={serviceOptions} value={this.state.service} onChange={svc => this.serviceSelect(svc)} />
            </div>
            {(this.state.service?.parameters.length ?? 0) > 0 &&
            <div>
                <h4>Parameters:</h4>
                <div className="parammapping-ct">
                    <div>Used</div>
                    <div>Parameter</div>
                    <div>Type</div>
                    <div>Mapping</div>
                </div>
                {Array.from(this.state.params, ([k, v]) => ({param: k, config: v}))
                    .map(kv =>
                        <div className="parammapping-ct">
                            <div>
                                <input type="checkbox" checked={kv.config.enabled} disabled={!kv.param.optional}
                                       onChange={e => this.enabledChange(kv.param, e)}/>
                            </div>
                            <div>
                                {kv.param.name}
                            </div>
                            <div>
                                <Dropdown
                                    value={kv.config.type}
                                    items={[
                                        {label: 'Input', value: "INPUT"},
                                        {label: 'Fixed', value: "FIXED"}
                                    ]}
                                    onChange={(v: MappingType) => this.mappingTypeChange(kv.param, v)} />
                                {/*<input type="radio" radioGroup="mapping" checked={kv.config.type === "INPUT"}
                                       value="INPUT" onChange={e => this.mappingTypeChange(kv.param, e)}/> Input
                                <input type="radio" radioGroup="mapping" checked={kv.config.type === "FIXED"}
                                       value="FIXED" onChange={e => this.mappingTypeChange(kv.param, e)}/> Fixed*/}
                            </div>
                            <div>
                                {
                                    kv.config.type === "FIXED" ?
                                        <input
                                            type={this.isNumber(kv.param) ? "number" : "text"}
                                            value={kv.config.value}
                                            onChange={e => this.valueChange(kv.param, e)}/>
                                        :
                                        <Dropdown
                                            value={kv.config.input}
                                            items={[
                                                {label: 'Text', value: 'TEXT'},
                                                {label: 'Slider', value: 'SLIDER'}
                                            ]}
                                            onChange={(v: InputType) => this.inputChange(kv.param, v)} />
                                }
                            </div>
                        </div>
                    )}
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
    action?: Action
    cps: CPS
    onSubmit: (action: Action) => void
    onCancel: () => void
}
interface State {
    name: string
    service?: Service
    params: Map<Parameter, ParameterConfiguration>
}

interface ParameterConfiguration {
    enabled: boolean
    type: MappingType
    value: any
    input: InputType
}

type MappingType = "INPUT" | "FIXED"