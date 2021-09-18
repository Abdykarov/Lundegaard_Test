import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import RequestTypeStore from "./store/RequestTypeStore";

export const Context = createContext(null)
ReactDOM.render(
    <Context.Provider value={{
        requestType: new RequestTypeStore()
    }}>
        <App />
    </Context.Provider>,
    document.getElementById('root')
);