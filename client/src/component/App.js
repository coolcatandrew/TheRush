import React from "react";
import {HashRouter, Route, Switch} from "react-router-dom";
import Upload from "./Upload";
import Results from "./Results";
import Header from "./Header";

const App = () => {
    return (
        <div>
            <HashRouter>
                <div>
                    <Header/>
                    <Switch>
                        <Route path='/' exact={true} component={Results}/>
                        <Route path='/upload' exact={true} component={Upload}/>
                    </Switch>
                </div>
            </HashRouter>
        </div>
    );


}
export default App;