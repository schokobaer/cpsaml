
export interface LogDto {
    monitor: string
    id: string
    timestamp: string
    event: LogEvent
    msg: string
}

export type LogEvent = "ONLINE" | "OFFLINE" | "ALARM" | "RESOLVE" | "ACTION" | "CUSTOM"