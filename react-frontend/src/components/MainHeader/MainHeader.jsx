import React from 'react';
import style from "./MainHeader.module.css"

const MainHeader = ({userName, clanName}) => {
    return (
        <div className={style.main}>
            <div className={style.text}>
                Привет тебе, {userName} из клана "{clanName}".
            </div>
            <div className={style.text}>
                Выбери действие:
            </div>
        </div>
    );
};

export default MainHeader;