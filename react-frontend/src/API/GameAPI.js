import axios from 'axios';

const REST_URL = "http://localhost:8080/game";

export default class GameAPI {

    static async getCurrentClanGold() {
        const response = await axios.get(REST_URL);
        return response.data
    }

    static async addNewAction(action) {
        await axios.post(REST_URL, action);
    }

    static async stopGame() {
        const response = await axios.get(REST_URL + "/" + "stop");
        return response.data
    }
}