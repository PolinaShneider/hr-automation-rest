import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/candidate/';

class CandidateService {
  getMe(id) {
    return axios.get(API_URL + 'id?=' + id, { headers: authHeader() });
  }

  apply(positionId, userId) {
      return axios.post(API_URL + 'apply', {
          positionId,
          userId
      }, { headers: authHeader() });
  }
}

export default new CandidateService();
