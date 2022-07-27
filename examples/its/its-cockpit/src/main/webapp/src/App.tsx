import React from 'react';
import './App.css';
import WebSocketClient from './rest/WebSocketClient'
import AdminPage from "./page/AdminPage";
import MainPage from "./page/MainPage";
import {Switch, Route, Link, useLocation} from "react-router-dom";
import AlarmHandler from "./component/AlarmHandler";

const ws = new WebSocketClient()
ws.connect()

export default function App() {
    const location = useLocation()
    const admin = location.pathname.startsWith("/admin")
    return <div className="page">
        <div className="header">
            <div>
                <img src="/logo2.jpg" />
                <span>{admin ? "Configuration" : "ITS - Cockpit"}</span>
            </div>
            <div>
                {admin ? <Link to="/dashboard">🖥️</Link> :
                    <Link to="/admin">⚙️</Link>}
            </div>
        </div>
        <Switch>
            <Route path="/dashboard" render={() => <MainPage key="mainpage" ws={ws} />} />
            <Route path="/admin" component={AdminPage} />
        </Switch>
        <AlarmHandler />
    </div>
}