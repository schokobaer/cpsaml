import React, { Fragment } from 'react';
import {Path, PathFieldMapping, ScalarFunction, VectorFunction} from "../dto/MonitorDto";
import {
    metaDataMap
} from "../metaobject/MetaObject";
import Dropdown, {DropdownItem} from "./widget/Dropdown";
import {types} from "util";
import {CPS} from "../dto/Cps";
import {ComplexProperty, CpsComponent, Property, PropertyHolder, resolvePath} from "../metaobject/MetaObjectModel";

const isVectorFunction = (node: string | PathFieldMapping): boolean => {
    if (typeof node === "string") {
        return false
    }
    const pfm = node as PathFieldMapping
    const vectorFuns: Array<VectorFunction> = ['ALL', 'EXISTS', 'NONE']
    return vectorFuns.find(f => pfm.function === f) !== undefined
}

export default class PathSelector extends React.Component<Props, State> {

    private pathChange(field: string, i: number, func?: ScalarFunction | VectorFunction) {
        const path = this.props.path.filter((elem, idx) => idx < i)
        path.push(func ? {field: field, function: func} as PathFieldMapping : field)
        this.props.onChange(path)
    }

    private getSelectOptions(ctx: CpsComponent | Property): Array<string> {
        return (ctx as CpsComponent).properties?.map(p => p.name) ?? []
    }

    /**
     * Gets the field of the path at position idx.
     * @param idx
     */
    private get(idx: number): string | undefined {
        return idx >= this.props.path.length ? undefined : (this.props.path[idx] as PathFieldMapping).field ?? this.props.path[idx]
    }

    private getMetaPath() {
        const metaPath: Array<CpsComponent | Property> = [
            metaDataMap.get(this.props.cps)!
        ]
        let i = 0;
        while (i < this.props.path.length) {
            const id = this.get(i)
            const prev = metaPath[metaPath.length - 1]

            if ((prev as PropertyHolder).properties === undefined) {
                break;
            }

            const prop = (prev as PropertyHolder).properties.find(p => p.name === id)
            if (prop?.type !== "complex") {
                break;
            }

            // if list and ScalarFunction is COUNT|EMPTY is used, break
            if (prop.list === true &&
                ((this.props.path[i] as PathFieldMapping).function === "COUNT"
                    || (this.props.path[i] as PathFieldMapping).function === "EMPTY")) {
                break;
            }

            metaPath.push(prop)
            i++
        }
        return metaPath
    }
    
    private getListSelector(i: number) {
        const path = this.subPath(i + 1)
        const meta = resolvePath(path, this.props.cps) as Property
        if (path.length !== i + 1 || meta.list !== true) {
            return undefined
        }
        const val = typeof path[i] === "string" ? undefined : (path[i] as PathFieldMapping).function
        if (this.props.allowList) {
            return <Dropdown
                value={val}
                items={[
                    {label: 'List', value: undefined},
                    {label: 'Count', value: "COUNT"},
                    {label: 'Empty', value: "EMPTY"},
                    {label: 'Avg', value: "AVG"},
                    {label: 'Min', value: "MIN"},
                    {label: 'Max', value: "MAX"},
                    {label: 'Sum', value: "SUM"},
                    {label: 'First', value: "FIRST"},
                    {label: 'Last', value: "LAST"}
                ] as Array<DropdownItem<ScalarFunction>>}
                onChange={v => this.pathChange(this.get(i)!, i, v)} />
        } else if (this.props.allowVectorFunction) {
            return <Dropdown
                value={val}
                items={[
                    {label: 'Count', value: "COUNT"},
                    {label: 'Empty', value: "EMPTY"},
                    {label: 'Avg', value: "AVG"},
                    {label: 'Min', value: "MIN"},
                    {label: 'Max', value: "MAX"},
                    {label: 'Sum', value: "SUM"},
                    {label: 'First', value: "FIRST"},
                    {label: 'Last', value: "LAST"},
                    {label: 'All', value: "ALL"},
                    {label: 'Exists', value: "EXISTS"},
                    {label: 'None', value: "NONE"},
                ] as Array<DropdownItem<ScalarFunction|VectorFunction>>}
                onChange={v => this.pathChange(this.get(i)!, i, v)} />
        } else {
            return <Dropdown
                value={val}
                items={[
                    {label: 'Count', value: "COUNT"},
                    {label: 'Empty', value: "EMPTY"},
                    {label: 'Avg', value: "AVG"},
                    {label: 'Min', value: "MIN"},
                    {label: 'Max', value: "MAX"},
                    {label: 'Sum', value: "SUM"},
                    {label: 'First', value: "FIRST"},
                    {label: 'Last', value: "LAST"}
                ] as Array<DropdownItem<ScalarFunction|VectorFunction>>}
                onChange={v => this.pathChange(this.get(i)!, i, v)} />
        }
    }

    private subPath(n: number) {
        return this.props.path.filter((elem, idx) => idx < n)
    }

    render() {
        const metaPath = this.getMetaPath()
        return <Fragment>
            {metaPath
                .filter(mp => (mp as PropertyHolder).properties)
                .map((mp, i) =>
                <React.Fragment>
                    <select
                        value={this.get(i)}
                        onChange={e => this.pathChange(e.target.value, i)}
                        style={{overflowY: 'hidden'}}
                        size={this.props.expanded === false ? 1 : this.getSelectOptions(mp).length + 1}>

                        {this.props.expanded === false && this.props.path.length < metaPath.length &&
                        <option style={{display: 'none'}} value={undefined} selected={true} disabled={true}/>
                        }

                        {this.getSelectOptions(mp).map(o =>
                            <option value={o}>{o}</option>
                        )}
                    </select>

                    {this.getListSelector(i)}
                </React.Fragment>
            )}
        </Fragment>
    }
}

interface Props {
    path: Path
    onChange: (path: Path) => void
    cps: CPS
    expanded?: boolean
    allowList?: boolean // allowList XOR allowVectorFunction
    allowVectorFunction?: boolean
}
interface State {
}