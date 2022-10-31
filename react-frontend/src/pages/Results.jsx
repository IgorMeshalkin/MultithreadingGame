import '../App.css';
import React, {useEffect, useState} from 'react';
import GameAPI from "../API/GameAPI";
import Loader from "../components/Loader/Loader";
import UnitedResults from "../components/UnitedResults/UnitedResults";

const Results = ({currentUser, newGame, continion, actionsCounter}) => {
    const [resultList, setResultList] = useState([]);
    const [actionsContinues, setActionsContinues] = useState(true)

    useEffect(() => {
        fetchResults()
    }, [])

    async function fetchResults() {
        const result = await GameAPI.stopGame()
        setResultList(result.slice(result.length - (actionsCounter), result.length))
        setActionsContinues(false)
    }

    function test() {
        console.log(resultList)
    }

    return (
        <div className='resultMain'>
            {actionsContinues ?
                <Loader/>
                :
                <UnitedResults
                    currentUser={currentUser}
                    resultList={resultList}
                    newGame={newGame}
                    continion={continion}
                />
            }
        </div>
    );
};

export default Results;