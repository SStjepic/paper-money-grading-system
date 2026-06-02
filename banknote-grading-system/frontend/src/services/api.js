import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v1/banknote';

export const banknoteApi = {
    evaluateBanknote: async (requestData) => {
        const response = await axios.post(`${API_BASE_URL}/grade`, requestData);
        return response.data;
    },

    checkAchievable: async (gradeCheckPayload) => {
        const response = await axios.post(`${API_BASE_URL}/check-achievable`, gradeCheckPayload);
        return response.data;
    },

    getRequirementsForGrade: async (grade) => {
        const response = await axios.get(`${API_BASE_URL}/requirements/${grade}`);
        return response.data;
    },

    getMissingInputs: async (gradeCheckPayload) => {
        const response = await axios.post(`${API_BASE_URL}/missing-inputs`, gradeCheckPayload);
        return response.data;
    }
};