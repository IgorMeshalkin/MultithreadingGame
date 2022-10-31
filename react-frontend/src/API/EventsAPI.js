import axios from 'axios';

const REST_URL = "http://localhost:8080/events";

export default class EventsAPI {

    static async getAll() {
        const response = await axios.get(REST_URL, {});
        return response.data
    }
}