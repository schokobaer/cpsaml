import {CpsDto} from "../dto/CpsDto";
import {LogDto} from "../dto/LogDto";


export function valueByKey(cpsDto: CpsDto | undefined, key: string) {
    return cpsDto?.data.filter(kv => kv !== null).find(kv => kv.key === key)?.value
}

export function formatTimestamp(log: LogDto) {
    const timestamp = new Date(log.timestamp)
    return timestamp.getHours() + ':' + timestamp.getMinutes() + ':' + timestamp.getSeconds() + '.' + timestamp.getMilliseconds()
}