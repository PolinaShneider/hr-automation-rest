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

  getMyApplication(applicationId) {
    return axios.get(API_URL + `applications/${applicationId}`, {headers: authHeader()});
  }

  getMyInterviews(id) {
    return axios.get(API_URL + `${id}/interviews`, {headers: authHeader()});
  }

  getPositions() {
    return axios.get(API_URL + 'open-positions', { headers: authHeader() });
  }

  getTeams() {
    return axios.get(API_URL + 'get-teams', { headers: authHeader() });
  }
}

export default new CandidateService();
