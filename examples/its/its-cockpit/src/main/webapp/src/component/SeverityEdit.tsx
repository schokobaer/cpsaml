import React from 'react';
import './widget/Tile.css'
import {Comparator, Comparison, Threshold, Path, PathFieldMapping, Severity} from "../dto/MonitorDto";
import PathSelector from "./PathSelector";
import Dropdown, {DropdownItem} from "./widget/Dropdown";
import ThresholdEdit from "./ThresholdEdit";
import {DataType} from "../dto/PredefinedPropertyMonitorSpec";
import PredefinedRestClient from "../rest/PredefinedRestClient";
import DisplaySwitch from "./widget/DisplaySwitch";
import {CPS} from "../dto/Cps";
import {Property, resolvePath} from "../metaobject/MetaObjectModel";

export default class SeverityEdit extends React.Component<Props, State> {

    state: State = {
        customs: []
    }

    rest = new PredefinedRestClient()

    componentDidMount() {
        if (this.props.dataType) {
            this.rest.getSeverities(this.props.dataType, this.props.cps)
                .then(severities => this.setState({customs: severities}))
                .catch(err => console.error(err))
        }
    }

    private severitySwitch(page: Page) {
        if (page === "threshold") {
            this.props.onChange({
                green: {
                    comparator: "EQ",
                    value: undefined
                },
                orange: {
                    comparator: "EQ",
                    value: undefined
                }
            })
        } else {
            const custom = this.state.customs[0]
            this.props.onChange(custom)
        }
    }

    private enableChange(enabled: boolean) {
        if (enabled) {
            this.severitySwitch("threshold")
        } else {
            this.props.onChange(undefined)
        }
    }

    private orangeToggle(enabled: boolean) {
        const severity = this.props.severity!
        if (enabled) {
            severity.orange = {comparator: "EQ", value: 0}
            this.props.onChange(severity)
        } else {
            severity.orange = undefined
            this.props.onChange(severity)
        }
    }

    render () {
        const page: Page | undefined = this.props.severity ? "threshold" : this.props.custom ? "custom" : undefined
        return <React.Fragment>
            <h4>
                <input type="checkbox"
                       checked={page !== undefined}
                       onChange={e => this.enableChange(e.target.checked)} />
                <span style={{marginLeft: 10}}>Severity:</span>
            </h4>

            {page && (this.state.customs.length > 0 || this.props.custom) && <>
                <DisplaySwitch
                    onChange={(p: Page) => this.severitySwitch(p)}
                    items={[{title: 'Predefined', value: 'custom'}, {title: 'Threshold', value: 'threshold'}]}
                    value={page} />
            </>}

            {page === "threshold" &&
            <div>
                <table className="severity-table">
                    <tr style={{backgroundColor: 'lightgreen'}}>
                        <td></td>
                        <td style={{color: 'darkgreen', fontWeight: 'bold'}}>Green</td>
                        <td>
                            <ThresholdEdit
                                dataType={this.props.dataType}
                                threshold={this.props.severity!.green}
                                path={this.props.path}
                                cps={this.props.cps}
                                onChange={threshold => this.props.onChange({
                                        green: threshold,
                                        orange: this.props.severity!.orange
                                    })} />
                        </td>
                    </tr>
                    <tr style={{backgroundColor: 'lightgoldenrodyellow'}}>
                        <td>
                            <input type="checkbox"
                                   checked={this.props.severity?.orange !== undefined}
                                   onChange={e => this.orangeToggle(e.target.checked)} />
                        </td>
                        <td style={{color: 'darkorange', fontWeight: 'bold'}}>Orange</td>
                        <td>
                            {this.props.severity?.orange &&
                            <ThresholdEdit
                                dataType={this.props.dataType}
                                threshold={this.props.severity.orange}
                                path={this.props.path}
                                cps={this.props.cps}
                                onChange={threshold => this.props.onChange({
                                    green: this.props.severity!.green,
                                    orange: threshold
                                })}/>
                            }
                        </td>
                    </tr>
                    <tr style={{backgroundColor: 'lightpink'}}>
                        <td></td>
                        <td style={{color: 'darkred', fontWeight: 'bold'}}>Red</td>
                        <td>Otherwise</td>
                    </tr>
                </table>
            </div>
            }

            {page === "custom" && <>
                <span style={{marginRight: 10}}>Severity Function:</span>
                <Dropdown
                        value={this.props.custom}
                        items={this.state.customs.map(c => ({label: c, value: c}))}
                        onChange={custom => this.props.onChange(custom)} />
            </>}
        </React.Fragment>
    }
}

interface Props {
    dataType?: DataType
    severity?: Severity
    custom?: string
    path?: Path
    cps: CPS
    onChange: (severity?: Severity | string) => void
}

interface State {
    customs: Array<string>
}

type Page = "threshold" | "custom"