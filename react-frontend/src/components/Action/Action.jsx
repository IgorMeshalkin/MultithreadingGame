import React, {useEffect, useState} from 'react';
import cl from "./Action.module.css"
import swords from "../../images/swords.png"
import scroll from "../../images/scroll.png"
import coins from "../../images/coins.png"
import GameAPI from "../../API/GameAPI";

const Action = (props) => {
    const [actionObj, setActionObj] = useState({type: '', bet: ''})
    const [currentClanGold, setCurrentClanGold] = useState({currentClanGold: ''})
    const info = 'Казна твоего клана составляет ' + currentClanGold + '.'

    useEffect(() => {
        if (props.action === 'Бой') {
            setActionObj({...actionObj, type: 'BATTLE'})
        } else if (props.action === 'Задание') {
            setActionObj({...actionObj, type: 'TASK'})
        } else {
            setActionObj({...actionObj, type: 'REPLENISHMENT'})
        }
    }, [])

    function Image() {
        if (props.action === 'Бой') {
            return (
                <div className={cl.photo}>
                    <img src={swords} className={cl.img}/>
                </div>);
        } else if (props.action === 'Задание') {
            return (
                <div className={cl.photo}>
                    <img src={scroll} className={cl.img}/>
                </div>);
        } else {
            return (
                <div className={cl.photo}>
                    <img src={coins} className={cl.img}/>
                </div>);
        }

    }

    function isCorrectNumber(number) {
        return (!isNaN(parseFloat(number))
            && isFinite(number)
            && number % 1 == 0)
    }

    function addNewAction() {
        if (!isCorrectNumber(actionObj.bet) || actionObj.bet <= 0) {
            props.setInvalidSumModal(true)
            return;
        }

        if (actionObj.type !== 'REPLENISHMENT' && currentClanGold <= 0) {
            props.setZeroBalanceModal(true)
            return;
        }

        if (actionObj.type !== 'REPLENISHMENT' && actionObj.bet > currentClanGold) {
            props.setBetExceedsModal(true)
            return;
        }

        GameAPI.addNewAction(actionObj)
        props.changeVisible(props.action)
        props.incrementActionsCounter()
    }

    async function openInputBlock() {
        if (!props.visible) {
            const result = await GameAPI.getCurrentClanGold();
            setCurrentClanGold(result.currentClanGold)
        }
        props.changeVisible(props.action)
    }

    return (
        <div className={cl.main}>
            <div className={cl.info} onClick={() => openInputBlock()}>
                <Image/>
                {props.action}
            </div>
            {
                props.visible && <div className={cl.inputBlock}>
                    <div className={cl.question}>{info}</div>
                    <div className={cl.question}>{props.question}</div>
                    <input
                        placeholder='Сумма'
                        className={cl.input}
                        onChange={e => setActionObj({...actionObj, bet: e.target.value})}
                    />
                    <button
                        className={cl.button}
                        onClick={addNewAction}
                    >
                        {props.buttonText}
                    </button>
                </div>
            }
        </div>
    );
};

export default Action;