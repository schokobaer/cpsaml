import React from 'react';
import {LineChart, Line, XAxis, YAxis, CartesianGrid} from 'recharts';
import './GraphTile.css'

export default class GraphTile extends React.Component<Props> {

    render () {
        const data = this.props.data.map(d => ({time: `${d.first}s`, value: d.second}))
        return <div className="graph-tile">
            <span>{this.props.description}</span>
            <LineChart width={400} height={200} data={data}>
                <XAxis dataKey="time" />
                <YAxis max={100} min={0} />
                <CartesianGrid stroke="#c00" strokeDasharray="5 5" />
                <Line type="monotone" dataKey="value" />
            </LineChart>
        </div>
    }
}

interface Props {
    description: string
    data: Array<any>
}