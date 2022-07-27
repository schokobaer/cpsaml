import React, {CSSProperties} from 'react';
import './Ampel.css'
import {SeverityLevel} from "../../dto/CpsDto";

export default class Ampel extends React.Component<Props> {

    render () {
        return <div className={"ampel " + (this.props.orientation === "HORIZONTAL" ? "ampel-horizontal" : "ampel-vertical")}>
                <div className={this.props.color === "RED" ? "ampel-red" : ""}></div>
                <div className={this.props.color === "ORANGE" ? "ampel-orange" : ""}></div>
                <div className={this.props.color === "GREEN" ? "ampel-green" : ""}></div>
            </div>
    }
}

interface Props {
    orientation?: "HORIZONTAL" | "VERTICAL"
    color: SeverityLevel
}