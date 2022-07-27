export interface AlarmDto {
    monitor: string
    context: string
    instance?: string
    active: boolean
    type: AlarmType
}

export type AlarmType = "CRITICAL" | "OUTOFBOUNDS"