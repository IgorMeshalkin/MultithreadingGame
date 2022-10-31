import React from 'react';
import cl from "./ResultButton.module.css"

const ResultButton = ({message, seeResults}) => {
    return (
        <div>
            <div className={cl.actionsCounter}>{message}</div>
            <div
                className={cl.resultButton}
                onClick={seeResults}
            >
                Показать результаты
            </div>
        </div>
    );
};

export default ResultButton;