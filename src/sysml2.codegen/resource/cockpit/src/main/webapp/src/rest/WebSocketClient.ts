import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import {CpsDto} from "../dto/CpsDto";
import {AlarmDto} from "../dto/AlarmDto";
import {LogDto} from "../dto/LogDto";

export default class WebSocketClient {
    private socket?: WebSocket
    private client?: Stomp.Client
    private establieshed = false
    private connectionCalls: Array<Function> = []
    private subscriptions = new Map<string, Stomp.Subscription>()
    private connectionChangeHandler?: ((ready: boolean) => void)

    private alarmBCC = new BroadcastChannel('alarm')
    private logBCC = new BroadcastChannel('log')
    private cpsBCC = new BroadcastChannel('cps')

    connect(): Promise<Stomp.Frame | undefined> {

        this.socket = new SockJS('/event/websocket');
        this.client = Stomp.over(this.socket);
        const client = this.client
        console.info("Trying to connect to websocket endpoint")

        return new Promise<Stomp.Frame | undefined>((resolve, fail) => {
            client.connect({}, (frame?: Stomp.Frame) => {
                this.establieshed = true
                this.connectionCalls.forEach(f => f())
                console.info("Connect fallback")
                this.initAlarmSubscription()
                resolve(frame)
                if (this.connectionChangeHandler) {
                    this.connectionChangeHandler(this.establieshed)
                }
            }, (err: any) => {
                console.error("Error in WebSocket Connection", err)
                console.info("Trying to reconnect to WebSocketEndpoint")
                this.establieshed = false
                this.connectionCalls = []
                this.connect()
                fail()
                if (this.connectionChangeHandler) {
                    this.connectionChangeHandler(this.establieshed)
                }
            });
        })
    }

    public setConnectionChangeHandler(handler: (ready: boolean) => void) {
        this.connectionChangeHandler = handler
    }

    public isReady(): boolean {
        return this.establieshed
    }

    private initAlarmSubscription() {
        this.subscribe('/event/alarm', (msg) => {
            const alarm = JSON.parse(msg.body) as AlarmDto
            if (alarm.active) {
                console.error("ALARM", alarm)
            } else {
                console.error("RECOVERED", alarm)
            }
            this.alarmBCC.postMessage(alarm)
        })
    }

    subscribeCps(monitorId: string, instanceId?: string) {
        let path = `/event/cps/${monitorId}`
        if (instanceId) {
            path += `/${instanceId}`
        }
        path += '/*'
        if (this.subscriptions.has(path)) return;
        this.subscribe(path, (msg: Stomp.Message) => {
            const destination = (msg.headers as any).destination as string

            if (destination.endsWith("log")) {
                const wsmsg: LogDto = JSON.parse(msg.body)
                this.logBCC.postMessage(wsmsg)
                return;
            }

            const cpsDto: CpsDto = JSON.parse(msg.body)
            const bccMsg: BroadcastMessage = {
                monitorId: monitorId,
                payload: cpsDto,
                type: "UPDATE"
            }
            if (destination.endsWith("update")) {
                bccMsg.type = "UPDATE"
            } else if (destination.endsWith("offline")) {
                bccMsg.type = "OFFLINE"
            } else {
                console.warn("Unknown destination: " + destination)
                return
            }
            this.cpsBCC.postMessage(bccMsg)
        })
    }

    unsubscribeCps(monitorId: string, instanceId?: string) {
        let path = `/event/cps/${monitorId}`
        if (instanceId) {
            path += `/${instanceId}`
        }
        path += '/*'
        const sub = this.subscriptions.get(path)
        if (sub) {
            sub?.unsubscribe()
            console.info("BCC: Unsubscribed")
            this.subscriptions.delete(path)
        }
    }

    private subscribe(path: string, msgHandler: (msg: Stomp.Message) => any) {
        const f = () => {
            const sub = this.client!.subscribe(path, msgHandler);
            console.info("Subscribing for topic " + path)
            this.subscriptions.set(path, sub);
        }
        if (this.establieshed === true) {
            f()
        } else {
            this.connectionCalls.push(f)
        }
    }

}

export interface BroadcastMessage {
    monitorId: string
    type: 'UPDATE' | 'OFFLINE'
    payload: CpsDto
}