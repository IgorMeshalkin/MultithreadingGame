import React from 'react';
import ResultHeader from "../ResultHeader/ResultHeader";
import ResultTable from "../ResultTable/ResultTable";
import cl from './UnitedResult.module.css'

const UnitedResults = ({currentUser, resultList, newGame, continion}) => {
    return (
        <div className={cl.main}>
            <ResultHeader
                userName={currentUser.name}
                clanName={currentUser.clan.name}
                newGame={newGame}
                continion={continion}
            />
            <ResultTable
                resultList={resultList}
            />
        </div>
    );
};

export default UnitedResults;