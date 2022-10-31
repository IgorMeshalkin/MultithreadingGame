import {useEffect, useState} from "react";
import MainHeader from "../components/MainHeader/MainHeader";
import ActionsBoard from "../components/ActionsBoard/ActionsBoard";
import ResultButton from "../components/ResultButton/ResultButton";
import Modal from "../components/Modal/Modal";

function MainGame({currentUser, actionsCounter, incrementActionsCounter, seeResults}) {
    const [actionsCounterMessage, setActionsCounterMessage] = useState('Всего событий: 0')

    const [invalidSumModal, setInvalidSumModal] = useState(false)
    const [betExceedsModal, setBetExceedsModal] = useState(false)
    const [zeroBalanceModal, setZeroBalanceModal] = useState(false)

    useEffect(() => {
        setActionsCounterMessage('Всего событий: ' + actionsCounter)
    }, [actionsCounter])

    return (
        <div className="App">
            <div className="MainGamePage">
                <MainHeader
                    userName={currentUser.name}
                    clanName={currentUser.clan.name}
                />
                <ActionsBoard
                    incrementActionsCounter={incrementActionsCounter}
                    setInvalidSumModal={setInvalidSumModal}
                    setBetExceedsModal={setBetExceedsModal}
                    setZeroBalanceModal={setZeroBalanceModal}
                />
                <ResultButton
                    message={actionsCounterMessage}
                    seeResults={seeResults}
                />
            </div>

            <Modal
                visible={invalidSumModal}
                setVisible={setInvalidSumModal}
                message='Нужно вводить целые числа. Без букв и пробелов. Соберись.'
                buttonText='Хорошо'
            />

            <Modal
                visible={betExceedsModal}
                setVisible={setBetExceedsModal}
                message='Ставка не может превышать остаток казны'
                buttonText='Понятно'
            />

            <Modal
                visible={zeroBalanceModal}
                setVisible={setZeroBalanceModal}
                message='Казна пуста. Попробуй дождаться окончания одного из текущих действий или пополнить казну за свой счёт'
                buttonText='Попробую'
            />
        </div>
    );
}

export default MainGame;
