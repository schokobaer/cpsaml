import React from 'react';
import './Dropdown.css'

export default class Dropdown<T> extends React.Component<Props<T>, State> {

    private change(e: React.ChangeEvent<HTMLSelectElement>) {
        const index = Number(e.target.value)
        if (index >= 0) {
            const item = this.getItem(index)
            this.props.onChange(item.value, item.extra)
        }
    }

    private isGrouped(): boolean {
        return this.props.items.length > 0 && (this.props.items[0] as DropdownGroup<T>).items !== undefined
    }

    private getIndex(value: T): number {
        let i = 0
        if (this.isGrouped()) {
            for (let g of this.props.items.map((i: any) => i as DropdownGroup<T>)) {
                const idx = g.items.findIndex(k => k.value === value)
                if (idx >= 0) {
                    return idx + i
                }
                i += g.items.length
            }
        }
        return this.props.items.map((i: any) => i as DropdownItem<T>).findIndex(i => i.value === value)
    }

    private getItem(index: number): DropdownItem<T> {
        if (this.isGrouped()) {
            const all = this.props.items.map((i: any) => i as DropdownGroup<T>).map(g => g.items).flat()
            return all[index]
        }
        return this.props.items.map((i: any) => i as DropdownItem<T>)[index]
    }

    render () {
        const antiItem = this.isGrouped() ?
            this.props.items.map((i: any) => i as DropdownGroup<T>).find(g => g.items.find(i => i.value === undefined) !== undefined)?.items.find(i => i.value === undefined) :
            this.props.items.map((i: any) => i as DropdownItem<T>).find(i => i.value === undefined)

        return <select className="dropdown"
            value={this.props.value === undefined ? undefined : this.getIndex(this.props.value)}
                       size={this.props.size ?? 1}
            onChange={e => this.change(e)}>
            {antiItem === undefined && this.props.value === undefined &&
                <option style={{display: 'none'}} value={undefined} selected={true} disabled={true} />
            }

            {this.isGrouped() ?
                this.props.items
                    .map((item: any) => item as DropdownGroup<T>)
                    .map((group) => <optgroup label={group.label}>
                        {group.items.map((item) => <option value={this.getIndex(item.value)}>{item.label}</option>)}
                    </optgroup>)
                :
                this.props.items
                        .map((item: any) => item as DropdownItem<T>)
                        .map((item: DropdownItem<T>, i: number) => <option value={i}>{item.label}</option>)
            }
        </select>
    }
}

interface Props<T> {
    items: Array<DropdownItem<T>> | Array<DropdownGroup<T>>
    value?: T
    size?: number
    onChange: (value: T, extra?: any) => void
}
interface State {
}

export interface DropdownItem<T> {
    label: string
    value: T
    extra?: any
}

export interface DropdownGroup<T> {
    label: string
    items: Array<DropdownItem<T>>
}
