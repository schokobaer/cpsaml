import React, {CSSProperties} from 'react';
import './Tile.css'

export default class Tile extends React.Component<Props> {

    render () {
        return <div style={this.props.style} className={"tile" + (this.props.onClick ? " tile-clickable" : "")}>
            <div className="tile-head">
                <div className="tile-title">{this.props.description}</div>
                <div>
                    {this.props.onDelete &&
                    <div className="tile-delete" onClick={() => this.props.onDelete!()}>x</div>
                    }
                </div>
            </div>
            <div className="tile-body" onClick={this.props.onClick}>
                {this.props.children}
            </div>
        </div>
    }
}

interface Props {
    style?: CSSProperties
    description: string
    onDelete?: () => void
    onClick?: () => void
}