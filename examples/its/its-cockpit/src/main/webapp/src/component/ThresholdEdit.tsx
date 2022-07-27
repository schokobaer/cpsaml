import React from 'react';
import './widget/Tile.css'
import {Comparator, Comparison, Threshold, Path, PathFieldMapping} from "../dto/MonitorDto";
import Dropdown, {DropdownItem} from "./widget/Dropdown";
import {DataType} from "../dto/PredefinedPropertyMonitorSpec";
import {CPS} from "../dto/Cps";
import {dataTypeFromPath, PrimitiveProperty, Property, resolvePath, toDataType} from "../metaobject/MetaObjectModel";

export default class ThresholdEdit extends React.Component<Props, Comparison> {

    private getInput() {

        const changeHandler = (v: any) => {
            this.props.onChange({
                comparator: this.props.threshold.comparator,
                value: v,
            })
        }

        if (this.props.path) {
            const prop = resolvePath(this.props.path, this.props.cps) as Property
            const dataType = dataTypeFromPath(this.props.path, this.props.cps)
            if (dataType === undefined) {
                return undefined
            }
            if (dataType.type === "INT" || dataType.type === "FLOAT") {
                return <input type="number" value={this.props.threshold.value}
                              onChange={e => changeHandler(Number(e.target.value))}/>
            }
            if (prop.type === "enum") {
                return <select value={this.props.threshold.value} onChange={e => changeHandler(e.target.value)}>
                    {prop.literals.map(l =>
                        <option value={l}>{l}</option>
                    )}
                </select>
            } else if (dataType.type === "STRING") {
                return <input type="text" value={this.props.threshold.value}
                              onChange={e => changeHandler(e.target.value)}/>
            } else if (dataType.type === "BOOL") {
                return <select value={this.props.threshold.value}
                               onChange={e => changeHandler(e.target.value === "true")}>
                    <option style={{display: 'none'}} selected={this.props.threshold.value === undefined}></option>
                    <option value={"true"}>True</option>
                    <option value={"false"}>False</option>
                </select>
            } else {
                return <input type="number" value={this.props.threshold.value}
                              onChange={e => changeHandler(Number(e.target.value))}/>
            }
        }

        if (this.props.dataType && this.props.dataType.list !== true) {
            if (this.props.dataType.type === "STRING") {
                return <input type="text" value={this.props.threshold.value}
                              onChange={e => changeHandler(e.target.value)}/>
            } else if (this.props.dataType.type === "BOOL") {
                return <select value={this.props.threshold.value}
                               onChange={e => changeHandler(e.target.value === "true")}>
                    <option style={{display: 'none'}} selected={this.props.threshold.value === undefined}></option>
                    <option value={"true"}>True</option>
                    <option value={"false"}>False</option>
                </select>
            }  else {
                return <input type="number" value={this.props.threshold.value}
                              onChange={e => changeHandler(Number(e.target.value))}/>
            }
        }
    }

    private getComparators(): Array<DropdownItem<Comparator>> {

        let dataType = this.props.dataType
        if (dataType === undefined) {
            if (this.props.path === undefined) {
                return []
            }
            dataType = dataTypeFromPath(this.props.path, this.props.cps)
            if (dataType === undefined) {
                return []
            }
        }

        if (dataType.type === "STRING") {
            return [
                {label: '=', value: "EQ"},
                {label: '≠', value: "NEQ"}
            ]
        } else if (dataType.type === "BOOL") {
            return [
                {label: '=', value: "EQ"}
            ]
        } else {
            return [
                {label: '=', value: "EQ"},
                {label: '≠', value: "NEQ"},
                {label: '<', value: "SMT"},
                {label: '≤', value: "SME"},
                {label: '>', value: "GRT"},
                {label: '≥', value: "GRE"}
            ]
        }
    }

    render () {
        return <React.Fragment>
            <Dropdown
                items={this.getComparators()}
                value={this.props.threshold.comparator}
                onChange={c => this.props.onChange({
                    value: this.props.threshold.value,
                    comparator: c
                })} />
            {this.getInput()}
        </React.Fragment>
    }
}

interface Props {
    threshold: Threshold
    path?: Path
    dataType?: DataType
    cps: CPS
    onChange: (threshold: Threshold) => void
}