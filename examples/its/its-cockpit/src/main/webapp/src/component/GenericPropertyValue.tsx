import React, {CSSProperties} from 'react';
import {CriticalMonitor, PathFieldMapping, PropertyMonitor} from "../dto/MonitorDto";
import {CpsDto, SeverityLevel} from "../dto/CpsDto";
import Tile from "./widget/Tile";
import {ProgressBar} from "react-bootstrap";
import Ampel from "./widget/Ampel";
import {valueByKey} from "../util/KeyValueUtils";
import {CPS} from "../dto/Cps";
import {PrimitiveProperty, Property, resolvePath} from "../metaobject/MetaObjectModel";

class ValueObjective extends React.Component<Props, State> {

    private getValue() {
        let value = valueByKey(this.props.insance, this.props.objective.name)

        if (Array.isArray(value)) {
            // is list
            return (value as Array<any>).map(v => <React.Fragment><span>{v.toString()}</span><br/></React.Fragment>)
        }

        try {
            if (this.props.objective.path) {
                const meta = resolvePath(this.props.objective.path, this.props.cps) as Property
                if ((meta as PrimitiveProperty).bounds?.digits) {
                    value = parseFloat(value).toFixed(2)
                }
            }
            return value.toString()
        } catch (e) {
            return "N/A"
        }
    }

    private getExtra() {
        try {
            return this.props.insance.data
                .find(kv => kv.key === this.props.objective.name)!.extra
        } catch (e) {
            return undefined
        }
    }

    render () {
        let css: CSSProperties = {}
        let severity = undefined
        if (this.props.objective.severity || this.props.objective.customSeverity) {
            severity = this.getExtra()
        }
        if (this.props.objective.representation === "RANGE") {
            let max = undefined
            let min = undefined
            if (this.props.objective.path) {
                const meta = resolvePath(this.props.objective.path, this.props.cps) as PrimitiveProperty
                max = meta.bounds?.max
                min = meta.bounds?.min
            } else if (this.props.objective.custom) {
                max = this.props.objective.custom.bounds!.max
                min = this.props.objective.custom.bounds!.min
            }
            const value = this.getValue()
            css.width = 200
            return <Tile style={css} description={this.props.objective.name}>
                <ProgressBar max={max ?? 100} min={min ?? 0} now={Math.abs(value)}
                             variant={value > 0 ? "success" : "danger"}/>
                {value} {severity && <Ampel color={severity as SeverityLevel} orientation="HORIZONTAL" />}
            </Tile>
        }

        return <Tile style={css} description={this.props.objective.name}>
            {this.getValue()}
            {severity && <Ampel color={severity as SeverityLevel} />}
        </Tile>
    }
}

interface Props {
    objective: PropertyMonitor
    cps: CPS
    insance: CpsDto
}
interface State {
}

export default ValueObjective