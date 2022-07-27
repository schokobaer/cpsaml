import {Representation} from "./MonitorDto";
import {PrimitiveType} from "../metaobject/MetaObjectModel";

export interface PredefinedPropertyMonitorSpec {
    name: string
    representation: Representation
    returnType: DataType
    bounds?: Bounds
}

export interface Bounds {
    min: number
    max: number
}

export interface DataType {
    list: boolean
    type: PrimitiveType
}