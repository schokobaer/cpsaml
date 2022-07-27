import {PredefinedPropertyMonitorSpec} from "./PredefinedPropertyMonitorSpec";
import {CPS} from "./Cps";

export interface Dashboard {
    id?: string
    name: string
    cps: CPS
    instance?: string
    notificationType: NotificationType
    actions: Array<Action>
    monitors: Array<Monitor>
    eventSpecifications: Array<EventSpecification>
}

export type NotificationType = 'NONE' | 'EMAIL' | 'SMS'
export type InputType = 'TEXT' | 'SLIDER' | 'DROPDOWN'
export type Representation = 'TEXT' | 'RANGE' | 'GRAPH'

export interface Action {
    service: string
    name: string
    params: Array<ParameterMapping>
}

export interface ParameterMapping {
    parameter: string
    __type__: "fixed" | "input"
}

export interface InputParameterMapping extends ParameterMapping {
    type: InputType
    defaultValue?: any
    __type__: "input"
}

export interface FixedValueParameterMapping extends ParameterMapping {
    value: any
    __type__:"fixed"
}

export interface Monitor {
    name: string
    __type__: "prop" | "agg" | "critical"
}

export interface PropertyMonitor extends Monitor {
    path?: Path
    custom?: PredefinedPropertyMonitorSpec
    representation: Representation
    severity?: Severity
    customSeverity?: string
    alarmOnFalseValue?: boolean
    __type__: "prop" | "agg"
}

export type Path = Array<string|PathFieldMapping>

export interface PathFieldMapping {
    field: string
    function: ScalarFunction | VectorFunction
}

export type ScalarFunction = "COUNT" | "FIRST" | "LAST" | "EMPTY" | "AVG" | "SUM" | "MIN" | "MAX"
export type VectorFunction = "ALL" | "EXISTS" | "NONE"

export interface Severity {
    green: Threshold
    orange?: Threshold
}

export interface Threshold {
    comparator: Comparator
    value: any
}

export interface AggregationMonitor extends PropertyMonitor {
    aggregation: Aggregation
    filter: LogicalExpression
    __type__: "agg"
}

export interface CriticalMonitor extends Monitor {
    expression?: LogicalExpression
    custom?: string
    onEvent?: string
    __type__: "critical"
}


export type Aggregation = "COUNT" | "SUM" | "AVG" | "MIN" | "MAX"

export interface Logical {
    __type__: "comp" | "exp"
}

export interface Comparison extends Logical{
    path: Path
    comparator: Comparator
    value: any
    __type__: "comp"
}

export interface LogicalExpression extends Logical{
    expressions: Array<Logical>
    operator: LogicalOperator
    not: boolean
    __type__: "exp"
}

export type LogicalOperator = "AND" | "OR" | "IMPLIES"

export type Comparator = "EQ" | "NEQ" | "SMT" | "SME" | "GRT" | "GRE"

export interface EventSpecification {
    name: string
    before?: LogicalExpression
    after?: LogicalExpression
    custom?: string
}


