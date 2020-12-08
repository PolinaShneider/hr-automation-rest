import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/candidate/';

class CandidateService {
  getMe(username) {
    return axios.get(API_URL + username, {headers: authHeader()});
  }

  apply(request) {
    return axios.post(API_URL + 'apply', request, {headers: authHeader()});
  }

  getMyApplications(id) {
    return axios.get(API_URL + `${id}/applications`, {headers: authHeader()});
  }

  getMyInterviews(id) {
    return axios.get(API_URL + `${id}/interviews`, {headers: authHeader()});
  }
}

export default new CandidateService();
