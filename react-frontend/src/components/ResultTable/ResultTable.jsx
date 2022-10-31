import React from 'react';
import cl from './ResultTable.module.css'

const ResultTable = ({resultList}) => {
    function getTableAction(result) {
        if (result.eventType === 'BATTLE') {
            return 'Бой'
        } else if (result.eventType === 'TASK') {
            return 'Задание'
        } else {
            return 'Пополнение'
        }
    }

    function getTableSum(result) {
        let sum = result.clanGoldAfter - result.clanGoldBefore
        if (sum < 0) {
            sum = Math.abs(sum)
        }
        return sum
    }

    function getTableResult(result) {
        if (result.eventType === 'BATTLE') {
            return result.success ? 'Победа' : 'Поражение'
        } else if (result.eventType === 'TASK') {
            return result.success ? 'Успех' : 'Провал'
        } else {
            return 'Успех'
        }
    }

    function getTableDateTime(result) {
        return result.dateTime.hour + ':'
            + (result.dateTime.minute < 10
                ? '0' + result.dateTime.minute
                : result.dateTime.minute) + '   -   '
            + result.dateTime.dayOfMonth + '.'
            + result.dateTime.monthValue + '.'
            + result.dateTime.year;
    }

    let tableCounter = 0
    let result = resultList.map((res) => {
        tableCounter += 1
        return (<tr key={res.id}>
            <td>{tableCounter}</td>
            <td>{getTableAction(res)}</td>
            <td>{res.enemyClanName}</td>
            <td>{getTableSum(res)}</td>
            <td>{getTableResult(res)}</td>
            <td>{res.clanGoldBefore}</td>
            <td>{res.clanGoldAfter}</td>
            <td>{getTableDateTime(res)}</td>
        </tr>);
    });
    return (
        <div>
            <table>
                <tr>
                    <th className={cl.firstColumn}>№</th>
                    <th>Тип события</th>
                    <th>Клан врага</th>
                    <th>Сумма</th>
                    <th>Результат</th>
                    <th>Казна до</th>
                    <th>Казна после</th>
                    <th>Время и дата</th>
                </tr>
                <tbody>
                {result}
                </tbody>
            </table>
        </div>
    );
};

export default ResultTable;