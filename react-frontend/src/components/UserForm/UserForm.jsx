import React, {useEffect, useState} from 'react';
import ClansAPI from "../../API/ClansAPI";
import UsersAPI from "../../API/UsersAPI";
import cl from "./UserForm.module.css"

const UserForm = ({moveOn}) => {
    const [user, setUser] = useState({name: ''});
    const [clans, setClans] = useState([]);
    const [clanId, setClanId] = useState('');

    useEffect(() => {
        fetchClans()
    }, [])

    async function fetchClans() {
        const result = await ClansAPI.getAll()
        setClans(result)
    }

    async function saveUser() {
        await UsersAPI.save(user, clanId)
        moveOn(true)
    }

    return (
        <div className={cl.main}>
            <div className={cl.window}>
                <div className={cl.title}>Создать игрока</div>
                <input
                    className={cl.input}
                    onChange={e => setUser({...user, name: e.target.value})}
                    placeholder='Введи имя'
                    type='text'
                />
                <select onChange={e => setClanId(e.target.value)} className={cl.select}>
                    <option selected disabled value="">Выбери клан</option>
                    {clans.map(clan =>
                        <option key={clan.id} value={clan.id}>{clan.name}</option>
                    )}
                </select>

                <button onClick={saveUser} className={cl.button}>Далее</button>
            </div>
        </div>
    );
};

export default UserForm;