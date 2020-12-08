import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/interviewer/';

class InterviewerService {
  getMe(id) {
    return axios.get(API_URL + id, { headers: authHeader() });
  }

  getMyInterviews(id) {
    return axios.get(API_URL + `${id}/upcoming-interviews`, { headers: authHeader() });
  }

  conductInterview(id, feedback) {
    return axios.post(API_URL + `/conduct-interview/${id}`, {feedback}, { headers: authHeader() });
  }
}

export default new InterviewerService();
