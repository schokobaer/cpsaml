import React, {useEffect, useRef} from 'react';
import {LogDto} from "../dto/LogDto";
import './LogConsole.css'
import {formatTimestamp} from "../util/KeyValueUtils";

export default class LogConsole extends React.Component<Props, State> {

    state: State = {
        docked: true
    }

    private consoleEndRef = React.createRef<HTMLDivElement>()

    componentDidMount () {
        this.scrollToBottom()
    }
    componentDidUpdate () {
        this.scrollToBottom()
    }
    private scrollToBottom = () => {
        if (this.state.docked) {
            this.consoleEndRef.current?.scrollIntoView({behavior: 'smooth'})
        }
    }

    render () {
        return <div className="logconsole-ct">
            <div className="logconsole-header">
                <div>Event Log</div>
                <div>Dock <input type="checkbox" checked={this.state.docked} onChange={e => this.setState({docked: e.target.checked})} /></div>
            </div>
            <div className="logconsole">
                {this.props.logs.map(log =>
                    <div className={this.props.selected === log ? "log-selected" : ""} onClick={() => this.props.onSelect(log)}>
                        <div title={log.event}>
                            {
                                log.event === "CUSTOM" ? '⚡' :
                                log.event === "ACTION" ? '▶️' :
                                    log.event === "ALARM" ? '🔥' :
                                        log.event === "OFFLINE" ? '🔵' :
                                            log.event === "ONLINE" ? '⚪' : '✔️'
                            }
                        </div>
                        <div>{formatTimestamp(log)}</div>
                        <div>{log.msg}</div>
                    </div>
                )}
                <div ref={this.consoleEndRef} />
            </div>
        </div>
    }
}

interface Props {
    logs: Array<LogDto>
    selected?: LogDto
    onSelect: (log: LogDto) => void
}

interface State {
    docked: boolean
}