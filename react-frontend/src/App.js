import './App.css';
import NewGame from "./pages/NewGame";
import {useEffect, useState} from "react";
import MainGame from "./pages/MainGame";
import UsersAPI from "./API/UsersAPI";
import Results from "./pages/Results";

function App() {
    const [newGame, setNewGame] = useState(true)
    const [mainGame, setMainGame] = useState(false)
    const [results, setResults] = useState(false)

    const [currentUser, setCurrentUser] = useState({id: '', name: '', clan: ''})
    const [actionsCounter, setActionsCounter] = useState(0)

    useEffect(() => {
        fetchCurrentUser()
    }, [mainGame])

    async function fetchCurrentUser() {
        const result = await UsersAPI.getCurrentUser();
        setCurrentUser(result)
    }

    function incrementActionsCounter() {
        setActionsCounter(actionsCounter + 1)
    }

    function startMainGame() {
        setNewGame(false)
        setMainGame(true)
    }

    function seeResults() {
        setMainGame(false)
        setResults(true)
    }

    function startNewGame() {
        setActionsCounter(0)
        setResults(false)
        setNewGame(true)
    }

    function continion() {
        setResults(false)
        setMainGame(true)
    }

    return (
        <div>
            {newGame && <NewGame moveOn={startMainGame}/>}
            {mainGame && <MainGame
                currentUser={currentUser}
                actionsCounter={actionsCounter}
                incrementActionsCounter={incrementActionsCounter}
                seeResults={seeResults}
            />}
            {results && <Results
                currentUser={currentUser}
                newGame={startNewGame}
                continion={continion}
                actionsCounter={actionsCounter}
            />}
        </div>
    );
}

export default App;
