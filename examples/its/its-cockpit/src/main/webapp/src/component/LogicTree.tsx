import React from 'react';
import {Comparison, LogicalExpression, LogicalOperator, Logical} from "../dto/MonitorDto";
import Dropdown, {DropdownItem} from "./widget/Dropdown";
import ComparisonEdit from "./ComparisonEdit";
import './LogicalTree.css'
import {CPS} from "../dto/Cps";

const colorScheme = [
    '#CBE4F9',
    '#CDF5F6',
    '#EFF9DA',
    '#F9EBDF',
    '#F9D8D6',
    '#D6CDEA'
]

export default class LogicTree extends React.Component<Props, State> {

    state: State = {
        showAdd: false
    }

    componentDidMount() {
        const composed = this.props.logical as LogicalExpression
        if (composed.expressions && composed.expressions.length === 0) {
            this.setState({showAdd: true})
        }
    }

    private color() {
        //return colorScheme[(this.props.depth || 0) % colorScheme.length]
        const composed = this.props.logical as LogicalExpression
        return colorScheme[composed.operator === "IMPLIES" ? 0 :composed.operator === "OR" ? 2 : 4];
    }

    private getSubTree(index: number) {
        const composed = this.props.logical as LogicalExpression
        return <LogicTree
            depth={this.props.depth ? this.props.depth + 1 : 1}
            logical={composed.expressions[index]}
            cps={this.props.cps}
            onDelete={() => this.removeLogical(index)}
            onChange={logical => {
                const logicals = composed.expressions
                logicals[index] = logical
                this.props.onChange({
                    operator: composed.operator,
                    expressions: logicals,
                    not: composed.not,
                    __type__: "exp"
                } as LogicalExpression)
            }}/>
    }

    private getHeader() {
        const composed = this.props.logical as LogicalExpression
        return <div className="logicaltree-header">
            <div className="logicaltree-not">
                <input
                    type="checkbox"
                    checked={composed.not}
                    onChange={e => this.props.onChange({
                        expressions: composed.expressions,
                        operator: composed.operator,
                        not: e.target.checked,
                        __type__: "exp"
                    } as LogicalExpression)} /> NOT</div>
            {this.props.onDelete && <div className="logicaltree-delete" onClick={() => this.props.onDelete && this.props.onDelete()} >x</div>}
        </div>
    }

    private getSeparator(text: string) {
        return <div className="logicaltree-separator">
            <hr />
            <span style={{backgroundColor: this.color()}}>{text}</span>
        </div>
    }

    private getAddSelect() {
        return <div className="logicaltree-logical">
            <Dropdown items={this.getOptions()}
                      onChange={e => this.addLogical(e)}/>
        </div>
    }

    addLogical(type: LogicalOperator | "RULE") {
        this.setState({showAdd: false})
        const composed = this.props.logical as LogicalExpression
        const expressions = composed.expressions
        if (type === "RULE") {
            expressions.push({
                path: [],
                comparator: "EQ",
                value: undefined,
                __type__: "comp"
            } as Comparison)
        } else {
            expressions.push({
                expressions: [],
                operator: type,
                not: false,
                __type__: "exp"
            } as LogicalExpression)
        }
        this.props.onChange({
            operator: composed.operator,
            expressions: expressions,
            not: composed.not,
            __type__: "exp"
        } as LogicalExpression)
    }

    removeLogical(index: number) {
        const composed = this.props.logical as LogicalExpression
        composed.expressions.splice(index, 1)
        if (composed.expressions.length === 0) {
            this.setState({showAdd: true})
        }
        this.props.onChange(composed)
    }

    private getOptions() {
        const composed = this.props.logical as LogicalExpression
        const options: Array<DropdownItem<LogicalOperator | "RULE">> = [
            {label: "Rule", value: "RULE"},
            {label: "And", value: "AND"},
            {label: "Or", value: "OR"},
            {label: "If/Then", value: "IMPLIES"}
        ]
        return options.filter(o => o.value !== composed.operator)
    }

    render () {

        const comparison = this.props.logical as Comparison
        const composition = this.props.logical as LogicalExpression

        return <div>
            {this.props.logical.__type__ === "comp" ?
                <div className="logicaltree-logical">
                    <ComparisonEdit
                        expression={comparison}
                        cps={this.props.cps}
                        onChange={this.props.onChange}
                        onDelete={() => this.props.onDelete && this.props.onDelete()} />
                </div>
                :
                composition.operator === "IMPLIES" ?
                    <div className="logicaltree" style={{backgroundColor: this.color()}}>
                        {this.getHeader()}
                        <div className="logicaltree-if">IF</div>
                        {composition.expressions.length === 0 ? this.getAddSelect() : this.getSubTree(0)}
                        {this.getSeparator('THEN')}
                        {composition.expressions.length > 0 && (composition.expressions.length === 1 ? this.getAddSelect() :
                            this.getSubTree(1)
                        )}
                    </div>
                :
                <div className="logicaltree" style={{backgroundColor: this.color()}}>
                    {this.getHeader()}
                    {composition.expressions.map((logical, i) =>
                        <React.Fragment>
                            {this.getSubTree(i)}
                            {(i < composition.expressions.length - 1 || this.state.showAdd) && this.getSeparator(composition.operator)}
                        </React.Fragment>
                    )}

                    {this.state.showAdd ? this.getAddSelect() :
                        <div className="logicaltree-add" onClick={() => this.setState({showAdd: true})}>+</div>
                    }
                </div>
            }
        </div>
    }
}

interface Props {
    logical: Logical
    depth?: number
    cps: CPS
    onDelete?: () => void
    onChange: (logical: Logical) => void
}

interface State {
    showAdd: boolean
}