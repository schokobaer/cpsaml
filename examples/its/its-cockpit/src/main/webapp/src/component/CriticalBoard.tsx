import React from 'react';
import {LogDto} from "../dto/LogDto";
import './CriticalBoard.css'
import {CriticalMonitor} from "../dto/MonitorDto";
import {CpsDto} from "../dto/CpsDto";
import {valueByKey} from "../util/KeyValueUtils";

export default class CriticalBoard extends React.Component<Props, State> {

    render () {
        return <div className="criticalboard-ct">
            <div className="criticalboard-header">Criticals</div>
            <div className="criticalboard">
                {this.props.objectives.map(o =>
                    <div>
                        <div>{this.props.criticals[o.name] === true ? '🟢' : this.props.criticals[o.name] === false ? '🔴' : '⚪'}</div>
                        <div>{o.name}</div>
                    </div>
                )}
            </div>
        </div>
    }
}

interface Props {
    objectives: Array<CriticalMonitor>
    criticals: {[key: string]: boolean}
}

interface State {
}