import React, {useState} from 'react';
import cl from "./ActionsBoard.module.css"
import Action from "../Action/Action";

const ActionsBoard = (props) => {
    const battle = 'Бой'
    const task = 'Задание'
    const replenishment = 'Пополнение'

    const [battleVisible, setBattleVisible] = useState(false);
    const [taskVisible, setTaskVisible] = useState(false);
    const [replenishmentVisible, setReplenishmentVisible] = useState(false);

    function changeVisible(action) {
        if (action === battle) {
            setBattleVisible(!battleVisible)
            setTaskVisible(false)
            setReplenishmentVisible(false)
        } else if (action === task) {
            setTaskVisible(!taskVisible)
            setBattleVisible(false)
            setReplenishmentVisible(false)
        } else {
            setReplenishmentVisible(!replenishmentVisible)
            setBattleVisible(false)
            setTaskVisible(false)
        }
    }

    return (
        <div className={cl.main}>
            <Action
                action={battle}
                question={'Сколько поставишь на бой?'}
                buttonText={'Начать!'}
                visible={battleVisible}
                changeVisible={changeVisible}
                incrementActionsCounter={props.incrementActionsCounter}
                setInvalidSumModal={props.setInvalidSumModal}
                setBetExceedsModal={props.setBetExceedsModal}
                setZeroBalanceModal={props.setZeroBalanceModal}
            />
            <Action
                action={task}
                question={'Сколько поставишь на выполнение задания?'}
                buttonText={'Начать!'}
                visible={taskVisible}
                changeVisible={changeVisible}
                incrementActionsCounter={props.incrementActionsCounter}
                setInvalidSumModal={props.setInvalidSumModal}
                setBetExceedsModal={props.setBetExceedsModal}
                setZeroBalanceModal={props.setZeroBalanceModal}
            />
            <Action
                action={replenishment}
                question={'Сколько хочешь внести своих?'}
                buttonText={'Внести!'}
                visible={replenishmentVisible}
                changeVisible={changeVisible}
                incrementActionsCounter={props.incrementActionsCounter}
                setInvalidSumModal={props.setInvalidSumModal}
            />
        </div>
    );
};

export default ActionsBoard;