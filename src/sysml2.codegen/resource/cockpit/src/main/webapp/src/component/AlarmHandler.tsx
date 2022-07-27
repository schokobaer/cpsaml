import React from 'react';
import {AlarmDto} from "../dto/AlarmDto";
import {Link} from "react-router-dom"

export default class AlarmHandler extends React.Component<Props, State> {

    state: State = {
        alarms: [],
        open: new Map<string, boolean>()
    }
    private alarmBCC = new BroadcastChannel('alarm')

    componentDidMount() {
        this.alarmBCC.onmessage = msg => {
            const alarm = msg.data as AlarmDto
            if (alarm.active && this.state.alarms.find(a => a.monitor === alarm.monitor && a.context === alarm.context && a.instance === alarm.instance) === undefined) {
                const alarms = this.state.alarms
                alarms.push(alarm)
                const open = this.state.open
                open.set(this.getKey(alarm), true)
                this.setState({alarms: alarms, open: open})
            } else if (alarm.active === false) {
                const alarms = this.state.alarms.filter(a => !(a.monitor === alarm.monitor && a.context === alarm.context && a.instance === alarm.instance))
                const open = this.state.open
                open.delete(this.getKey(alarm))
                this.setState({alarms: alarms, open: open})
            }
        }
    }

    getKey(alarm: AlarmDto) {
        let key = `${alarm.monitor}_${alarm.context}`
        if (alarm.instance) {
            key += '_' + alarm.instance
        }
        return key
    }

    getMsg(alarm: AlarmDto) {
        let msg = ""
        if (alarm.type === "CRITICAL") {
            msg = `Critical ${alarm.context} failed`
        } else {
            msg = `Value is out of bounds in ${alarm.context}`
        }
        if (alarm.instance) {
            msg = `${alarm.instance}: ` + msg
        }
        return <Link to={`/dashboard/${alarm.monitor}/${alarm.instance}`}>{msg}</Link>
    }

    handleClose(alarm: AlarmDto) {
        const open = this.state.open
        open.delete(this.getKey(alarm))
        this.setState({open: open})
    }

    render () {
        return <React.Fragment>
            {this.state.alarms.filter(a => this.state.open.get(this.getKey(a))).map(a =>
                /* <Snackbar
                    key={this.getKey(a)}
                    open={true}
                    message={this.getMsg(a)} action={[
                    <button onClick={() => this.handleClose(a)}>x</button>
                ]} /> */
                <div>Snackbar</div>
            )}
        </React.Fragment>
    }
}

interface Props {
}

interface State {
    alarms: Array<AlarmDto>
    open: Map<string, boolean>
}