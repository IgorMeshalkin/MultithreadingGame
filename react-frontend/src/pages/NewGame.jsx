import '../App.css';
import UserForm from "../components/UserForm/UserForm";

function NewGame({moveOn}) {

    return (
        <div>
            <UserForm
                moveOn={moveOn}
            />
        </div>
    );
}

export default NewGame;