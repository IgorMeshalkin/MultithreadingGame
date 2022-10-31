import axios from 'axios';

const REST_URL = "http://localhost:8080/users";

export default class UsersAPI {

    static async getAll() {
        const response = await axios.get(REST_URL, {});
        return response.data
    }

    static async getCurrentUser() {
        const response = await axios.get(REST_URL + "/" + "current", {});
        return response.data
    }

    static async save(user, clanId) {
        const response = await axios.post(REST_URL + "/" + clanId, user);
    }
}