import React, {CSSProperties} from 'react';
import './ListItem.css'

export default class ListItem extends React.Component<Props> {

    render () {
        return <div style={this.props.style} className={"listitem" + (this.props.onClick ? " listitem-clickable" : "")}>
            {this.props.onDelete &&
                <div className="listitem-head">
                    <div></div>
                    <div className="listitem-delete" onClick={() => this.props.onDelete!()}>x</div>
                </div>
            }
            <div className="listitem-body" onClick={this.props.onClick}>
                {this.props.children}
            </div>
        </div>
    }
}

interface Props {
    style?: CSSProperties
    onDelete?: () => void
    onClick?: () => void
}