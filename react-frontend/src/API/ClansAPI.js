import axios from 'axios';

const REST_URL = "http://localhost:8080/clans";

export default class ClansAPI {

    static async getAll() {
        const response = await axios.get(REST_URL, {});
        return response.data
    }

    static async getById(id) {
        const response = await axios.get(REST_URL + "/" + id, {});
        return response.data
    }

    static async post() {
        await axios.post(REST_URL, {});
    }
}