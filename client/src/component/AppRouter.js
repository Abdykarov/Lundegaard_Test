// eslint-disable-next-line
import {observer} from "mobx-react-lite";
import {Redirect, Route, Switch} from "react-router-dom";
import {CONTACT_FORM_ROUTE} from "../utils/const";
import {publicRoutes} from "../routes";
import React, {useContext} from 'react';

const AppRouter = observer(() => {
    return (
        <Switch>
            {publicRoutes.map(({path, Component}) =>
                <Route key={path} path={path} component={Component} exact/>
            )}
            <Redirect to={CONTACT_FORM_ROUTE}/>
        </Switch>

    );
});

export default AppRouter;