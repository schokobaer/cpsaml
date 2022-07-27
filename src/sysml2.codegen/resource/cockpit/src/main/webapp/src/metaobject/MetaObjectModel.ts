import {Path, PathFieldMapping} from "../dto/MonitorDto";
import {CPS} from "../dto/Cps";
import {DataType} from "../dto/PredefinedPropertyMonitorSpec";
import {metaDataMap} from "./MetaObject";

export interface PropertyHolder {
    properties: Array<Property>
}

export interface CpsComponent extends PropertyHolder {
    services: Array<Service>
}

export interface Service {
    name: string
    parameters: Array<Parameter>
}

export interface Parameter {
    optional: boolean
    name: string
    type: PrimitiveType
    bounds?: Bounds
}

interface AbstractProperty {
    name: string
    list?: true
    type: "primitive" | "complex" | "enum"
}

export interface PrimitiveProperty extends AbstractProperty {
    type: "primitive"
    dataType: PrimitiveType
    bounds?: Bounds
}

export interface ComplexProperty extends AbstractProperty, PropertyHolder {
    type: "complex"
}

export interface EnumProperty extends AbstractProperty {
    type: "enum"
    literals: Array<string>
}

export type Property = PrimitiveProperty | ComplexProperty | EnumProperty

export interface Bounds {
    min?: number
    max?: number
    step?: number
    digits?: number
    defaultValue?: number
}

export type PrimitiveType = "INT" | "BOOL" | "STRING" | "FLOAT"

/**
 * Resolves the path in the given cps and returns this meta object.
 * @param path
 * @param cps
 */
export function resolvePath(path: Path, cps: CPS) {
    if (path.length === 0) {
        return metaDataMap.get(cps)!
    }

    const id = (path[0] as PathFieldMapping).field ?? path[0]
    let result: Property = metaDataMap.get(cps)!.properties.find(p => p.name === id)!

    let i = 1;
    while (i < path.length) {
        const id = (path[i] as PathFieldMapping).field ?? path[i]
        result = (result as ComplexProperty).properties.find(p => p.name === id)!
        i++
    }
    return result
}

export function dataTypeFromPath(path: Path, cps: CPS): DataType | undefined {
    let scalarFun
    for (let sp of path) {
        scalarFun = (sp as PathFieldMapping)?.function
        if (scalarFun === "COUNT") {
            return {
                type: "INT",
                list: false
            }
        } else if (scalarFun === "EMPTY") {
            return {
                type: "BOOL",
                list: false
            }
        }

        if (scalarFun === "AVG" || scalarFun === "MIN" || scalarFun === "MAX" || scalarFun === "SUM") {
            const property = resolvePath(path, cps) as Property
            const primitive = (property as PrimitiveProperty).dataType
            if (property.type !== "primitive" || !(primitive === "INT" || primitive === "FLOAT")) {
                return undefined
            }
            if (scalarFun === "AVG") {
                return {
                    type: "FLOAT",
                    list: false
                }
            } else if (scalarFun === "MIN" || scalarFun === "MAX" || scalarFun === "SUM") {
                return {
                    type: "INT",
                    list: false
                }
            }
        }
    }
    return toDataType(resolvePath(path, cps) as Property)
}

export function toDataType(property: Property): DataType | undefined {
    if (property.type === undefined || property.type === "complex") {
        return undefined
    }
    if (property.type === "enum" || property.dataType === "STRING") {
        return {
            type: "STRING",
            list: property.list === true
        }
    }
    return {
        type: property.dataType!,
        list: property.list === true
    }
}