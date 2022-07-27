import React from 'react';
import {
    Aggregation, AggregationMonitor,
    LogicalExpression, Path, PropertyMonitor, Representation, Severity
} from "../dto/MonitorDto";
import Dropdown, {DropdownItem} from "../component/widget/Dropdown";
import {
    metaDataMap
} from "../metaobject/MetaObject";
import ThresholdEdit from "../component/ThresholdEdit";
import {validateLogical, validateThreshold} from "../validator/LogicalValidator";
import PathSelector from "../component/PathSelector";
import PredefinedRestClient from "../rest/PredefinedRestClient";
import {PredefinedPropertyMonitorSpec, DataType} from "../dto/PredefinedPropertyMonitorSpec";
import DisplaySwitch from "../component/widget/DisplaySwitch";
import SeverityEdit from "../component/SeverityEdit";
import LogicTree from "../component/LogicTree";
import './PropertyMonitorEditPage.css'
import {CPS} from "../dto/Cps";
import {CpsComponent, dataTypeFromPath, PrimitiveProperty, Property, resolvePath} from "../metaobject/MetaObjectModel";

export default class PropertyMonitorEditPage extends React.Component<Props, State> {

    state: State = {
        name: '',
        path: [],
        representation: "TEXT",
        sourcePage: "path",
        customSources: []
    }

    customRest = new PredefinedRestClient()

    componentDidMount(): void {
        this.customRest.getPropertyMonitors(this.props.cps)
            .then(customs => this.setState({customSources: customs}))
            .catch(err => console.error(err))

        if (this.props.monitor) {
            this.setState({
                name: this.props.monitor.name,
                representation: this.props.monitor.representation,
                path: this.props.monitor.path,
                customSource: this.props.monitor.custom,
                sourcePage: this.props.monitor.custom ? "custom" : "path",
                severity: this.props.monitor.severity ? Object.assign({}, this.props.monitor.severity) : undefined,
                customSeverity: this.props.monitor.customSeverity,
                alarmOnFalseValue: this.props.monitor.alarmOnFalseValue,
                aggregation: (this.props.monitor as AggregationMonitor).aggregation,
                filter: (this.props.monitor as AggregationMonitor).filter
            })
        }
    }

    private savable() {
        if (this.state.name.length < 1) {
            return false
        }
        if (this.state.filter && !validateLogical(this.state.filter, this.props.cps)) {
            return false
        }
        if (this.state.aggregation !== "COUNT") {
            if (this.state.sourcePage === "path") {
                const dt = dataTypeFromPath(this.state.path!, this.props.cps)
                if (dt === undefined) {
                    return false
                }
            } else {
                if (this.state.customSource === undefined) {
                    return false
                }
            }
        }
        if (this.state.severity) {
            return validateThreshold(this.state.severity.green) && (this.state.severity.orange === undefined || validateThreshold(this.state.severity.orange!))
        }
        return true
    }

    private submit() {
        // TODO: Aggregations not allow alarmOnFalseValue
        let obj: PropertyMonitor = {
            name: this.state.name,
            path: this.state.sourcePage === "path" ? this.state.path : undefined,
            custom: this.state.sourcePage === "custom" ? this.state.customSource : undefined,
            representation: this.state.representation,
            severity: this.state.severity,
            customSeverity: this.state.customSeverity,
            alarmOnFalseValue: this.state.alarmOnFalseValue,
            __type__: "prop"
        }
        if (this.state.aggregation) {
            let agg = obj as AggregationMonitor
            agg.aggregation = this.state.aggregation
            agg.filter = this.state.filter!
            if (agg.aggregation === "COUNT") {
                agg.path = undefined
                agg.custom = undefined
            }
            agg.__type__ = "agg"
            obj = agg
        }
        this.props.onSubmit(obj)
    }

    private sourceChanged(source: Path | PredefinedPropertyMonitorSpec) {
        if ((source as PredefinedPropertyMonitorSpec).name) {
            const custom = source as PredefinedPropertyMonitorSpec
            const alarmOnFalseValue = custom.bounds === undefined ? undefined : this.state.alarmOnFalseValue ?? false
            this.setState({customSource: custom, path: undefined, alarmOnFalseValue: alarmOnFalseValue})
        } else {
            const path = source as Path
            const meta = resolvePath(path, this.props.cps) as Property
            const alarmOnFalseValue = (meta as PrimitiveProperty).bounds === undefined ? undefined : this.state.alarmOnFalseValue ?? false
            this.setState({customSource: undefined, path: path, alarmOnFalseValue: alarmOnFalseValue})
        }
    }

    private sourceSwitch(page: SourcePage) {
        let path = this.state.path
        if (page === "path") {
            if (path === undefined) {
                path = []
            }
        }
        this.setState({sourcePage: page, path: path})
    }

    private getRepresentations(): Array<DropdownItem<Representation>> {
        const representations: Array<DropdownItem<Representation>> = [{label: 'Text', value: "TEXT"}]
        if (this.state.sourcePage === "path") {
            const mp = resolvePath(this.state.path!, this.props.cps) as PrimitiveProperty
            if (mp.list === undefined && (mp.dataType === "FLOAT" || mp.dataType === "INT")) {
                if (mp.bounds?.max !== undefined) {
                    representations.push({label: 'Range', value: "RANGE"})
                }
                representations.push({label: 'Graph', value: "GRAPH"})
            }
        } else {
            if (this.state.customSource) {
                if (this.state.customSource.representation === "RANGE") {
                    representations.push({label: 'Range', value: "RANGE"})
                    representations.push({label: 'Graph', value: "GRAPH"})
                } else if (this.state.customSource.representation === "GRAPH") {
                    representations.push({label: 'Graph', value: "GRAPH"})
                }
            }
        }
        return representations
    }

    private getDataType(): DataType | undefined {
        if (this.state.customSource) {
            return this.state.customSource.returnType
        } else if (this.state.path) {
            const meta = resolvePath(this.state.path, this.props.cps) as PrimitiveProperty
            if (meta.type === "primitive") {
                return {
                    type: meta.dataType!,
                    list: meta.list === true
                }
            } else if (meta.type === "enum") {
                return {
                    type: "STRING",
                    list: meta.list === true
                }
            }
        }
        return undefined
    }

    private severityChange(severity: Severity | string | undefined) {
        if (severity === undefined) {
            this.setState({
                severity: undefined,
                customSeverity: undefined
            })
        } else if ((severity as Severity).green !== undefined) {
            this.setState({
                severity: severity as Severity,
                customSeverity: undefined
            })
        } else {
            this.setState({
                severity: undefined,
                customSeverity: severity as string
            })
        }
    }

    private aggregationCheckboxChanged(active: boolean) {
        if (active) {
            this.setState({
                aggregation: "COUNT",
                filter: {
                    operator: "AND",
                    expressions: [],
                    not: false,
                    __type__: "exp"
                }
            })
        } else {
            this.setState({
                aggregation: undefined,
                filter: undefined
            })
        }
    }

    render () {
        return <div className="actionedit-page">
            <h3>Property Objective</h3>
            <div>
                <span style={{marginRight: 10}}>Name:</span>
                <input type="text" value={this.state.name} onChange={e => this.setState({name: e.target.value})} />
            </div>

            <div>
                <span style={{marginRight: 10}}>Aggregation</span>
                <input type="checkbox"
                       checked={this.state.aggregation !== undefined}
                       onChange={e => this.aggregationCheckboxChanged(e.target.checked)} />
            </div>

            {this.state.aggregation &&
                <div>
                    <span style={{marginRight: 10}}>Aggregation Function:</span>
                    <Dropdown
                    value={this.state.aggregation}
                    items={[
                        {label: 'Avg', value: "AVG"},
                        {label: 'Count', value: "COUNT"},
                        {label: 'Min', value: "MIN"},
                        {label: 'Max', value: "MAX"},
                        {label: 'Sum', value: "SUM"}
                    ] as Array<DropdownItem<Aggregation>>}
                    onChange={v => this.setState({aggregation: v})} />
                </div>
            }

            {this.state.filter &&
                <div style={{}}>
                    <h4>Filter:</h4>
                    <LogicTree
                        logical={this.state.filter}
                        cps={this.props.cps}
                        onChange={exp => this.setState({filter: exp as LogicalExpression})} />
                </div>
            }

            {this.state.aggregation !== "COUNT" &&
                <div className="property-source-ct">
                    {(this.state.customSources.length > 0 || this.state.customSource) &&
                    <DisplaySwitch
                        onChange={(p: SourcePage) => this.sourceSwitch(p)}
                        items={[{title: 'Predefined', value: 'custom'}, {title: 'Path', value: 'path'}]}
                        value={this.state.sourcePage}/>
                    }

                    <div>
                    {this.state.sourcePage === "path" ?
                        <PathSelector
                        cps={this.props.cps}
                        path={this.state.path!}
                        expanded allowList
                        /*onChange={path => this.setState({path: path})} */
                        onChange={path => this.sourceChanged(path)} />
                        :
                        <>
                            <span style={{marginRight: 10}}>Function:</span>
                            <Dropdown
                                value={this.state.customSource?.name}
                                items={this.state.customSources.map(cs => ({label: cs.name, value: cs.name, extra: cs}))}
                                /*onChange={(name: string, cs: PredefinedPropertyMonitorSpec)  => this.setState({customSource: cs})}*/
                                onChange={(name: string, cs: PredefinedPropertyMonitorSpec)  => this.sourceChanged(cs)} />
                        </>
                    }
                    </div>
                </div>
            }

            <div className="service-select-ct">
                <span style={{marginRight: 10}}>Representation:</span>
                <Dropdown
                    items={this.getRepresentations()}
                    value={this.state.representation}
                    onChange={r => this.setState({representation: r})} />
            </div>

            {this.state.alarmOnFalseValue !== undefined &&
            <div>
                <span style={{marginRight: 10}}>Alarm on false value:</span>
                <input
                    type="checkbox"
                    checked={this.state.alarmOnFalseValue}
                    onChange={e => this.setState({alarmOnFalseValue: e.target.checked})} />
            </div>
            }

            <div>
                <SeverityEdit
                    key={JSON.stringify(this.getDataType())}
                    dataType={this.getDataType()}
                    path={this.state.path}
                    cps={this.props.cps}
                    severity={this.state.severity}
                    custom={this.state.customSeverity}
                    onChange={severity => this.severityChange(severity)} />
            </div>

            <div style={{marginTop: 15}}>
                {this.savable() && <button className="button-blue" style={{marginRight: 5}} onClick={() => this.submit()}>Save</button>}
                <button className="button-grey" onClick={() => this.props.onCancel()}>Cancel</button>
            </div>
        </div>
    }
}

interface Props {
    monitor?: PropertyMonitor
    cps: CPS
    onSubmit: (objective: PropertyMonitor) => void
    onCancel: () => void
}

interface State {
    name: string
    aggregation?: Aggregation
    filter?: LogicalExpression
    path?: Path
    representation: Representation
    severity?: Severity
    customSeverity?: string
    sourcePage: SourcePage
    customSources: Array<PredefinedPropertyMonitorSpec>
    customSource?: PredefinedPropertyMonitorSpec
    alarmOnFalseValue?: boolean
}

type SourcePage = "path" | "custom"