import React from 'react';
import './widget/Tile.css'
import {Comparison} from "../dto/MonitorDto";
import PathSelector from "./PathSelector";
import ThresholdEdit from "./ThresholdEdit";
import {CPS} from "../dto/Cps";

export default class ComparisonEdit extends React.Component<Props, Comparison> {

    render () {
        return <div>
            <PathSelector
                path={this.props.expression.path}
                cps={this.props.cps}
                expanded={false}
                allowVectorFunction={true}
                onChange={path => this.props.onChange({
                    path: path,
                    value: this.props.expression.value,
                    comparator: this.props.expression.comparator,
                    __type__: "comp"
                })} />
                <ThresholdEdit
                    threshold={this.props.expression}
                    cps={this.props.cps}
                    path={this.props.expression.path}
                    onChange={threshold => this.props.onChange({
                        path: this.props.expression.path,
                        comparator: threshold.comparator,
                        value: threshold.value,
                        __type__: "comp"
                    })} />
            <span onClick={this.props.onDelete}>➖</span>
        </div>
    }
}

interface Props {
    expression: Comparison
    cps: CPS
    onChange: (expression: Comparison) => void
    onDelete: () => void
}