import React from 'react';
import style from "./ResultHeader.module.css"

const ResultHeader = ({userName, clanName, newGame, continion}) => {
    return (
        <div className={style.main}>
            <div className={style.button} onClick={newGame}>Новая игра</div>
            <div className={style.center}>
                <div className={style.text}>
                    {userName} из клана "{clanName}"
                </div>
                <div className={style.text}>
                    Вот твои результаты
                </div>
            </div>
            <div className={style.button} onClick={continion}>Продолжить</div>
        </div>
    );
};

export default ResultHeader;