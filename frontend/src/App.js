import React from 'react';
import './App.css';
import { Switch, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Chat from './pages/Chat';
// import Login from './pages/Login';
// import Signup from './pages/Signup';
// import UserPage from './pages/UserPage';

const App = () => {
  // todo, add more pages!
  return (
    <div>
      <nav>
        <Link to="/"> Home </Link>
        <Link to="/chat"> Chat </Link>
      </nav>
      <Switch>
        <Route path="/chat"> 
        <Chat />
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </div>
  );
};

export default App;