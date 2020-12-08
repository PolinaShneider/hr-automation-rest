<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
      {{getProfileInfo}}
  </div>
</template>

<script>
import CandidateService from '../services/candidate.service';
import HrService from '../services/hr.service';
import InterviewerService from '../services/interviewer.service';

export default {
  name: 'Profile',
  data: () => {
    return {
      userData: {}
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    getProfileInfo() {
        const roles = this.currentUser.roles;

        console.log(CandidateService.apply({
            candidateId: this.currentUser.id,
            positionId: this.currentUser.id,
        }));

        if (roles.includes('ROLE_HR')) {
            this.userData = HrService.getMe(this.currentUser.username);
        } else if (roles.includes('ROLE_INTERVIEWER')) {
            this.userData = InterviewerService.getMe(this.currentUser.username);
        } else if (roles.includes('ROLE_CANDIDATE')) {
            this.userData = CandidateService.getMe(this.currentUser.username);
        }
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>