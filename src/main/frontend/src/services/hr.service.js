import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/hr/';

class HrService {
  getMe(id) {
    return axios.get(API_URL + id, { headers: authHeader() });
  }

  createPosition(request) {
    return axios.post(API_URL + 'create-position', request, { headers: authHeader() });
  }

  updatePosition(id, request) {
    return axios.post(API_URL + `${id}/update-position`, request, { headers: authHeader() });
  }

  getPositions() {
    return axios.get(API_URL + 'get-positions', { headers: authHeader() });
  }

  getTeams() {
    return axios.get(API_URL + 'get-teams', { headers: authHeader() });
  }

  getApplications() {
    return axios.get(API_URL + 'get-applications', { headers: authHeader() });
  }

  getInterviewers() {
    return axios.get(API_URL + 'get-interviewers', { headers: authHeader() });
  }

  getInterviewInfo(id) {
    return axios.get(API_URL + `get-interview-info/${id}`, { headers: authHeader() });
  }

  getRotationApplications() {
    return axios.get(API_URL + 'get-rotation-applications', { headers: authHeader() });
  }

  updateApplication(id, request) {
    return axios.post(API_URL + `${id}/update-application`, request,{ headers: authHeader() });
  }
}

export default new HrService();