import React from 'react';
import cl from './Loader.module.css'

const Loader = () => {
    return (
        <div className={cl.background}>
            <div className={cl.header}>Подожди пока закончатся текущие действия</div>
            <div className={cl.main}>
                <div className={cl.first}></div>
                <div className={cl.second}></div>
                <div className={cl.third}></div>
                <div className={cl.fourth}></div>
            </div>
        </div>
    );
};

export default Loader;