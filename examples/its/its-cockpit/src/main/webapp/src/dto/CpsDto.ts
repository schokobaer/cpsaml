import {CPS} from "./Cps";

export interface CpsDto {
    id: string
    cps: CPS
    data: Array<Pair<any>>
    criticals: {[key: string]: boolean}
}

export interface Pair<T> {
    key: string
    value: T
    extra?: string
}

export type SeverityLevel = "GREEN" | "ORANGE" | "RED"