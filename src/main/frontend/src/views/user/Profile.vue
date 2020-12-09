<template>
    <div class="container">
        <header class="jumbotron">
            <h3>
                User: <strong>{{currentUser.username}}</strong>
            </h3>
        </header>
        <p>
            <strong>Id:</strong>
            {{currentUser.id}}
        </p>
        <p>
            <strong>Name:</strong>
            {{userData.name}}
        </p>
        <p>
            <strong>About:</strong>
            {{userData.bio || "Tell about yourself"}}
        </p>
        <p>
            <strong>Email:</strong>
            {{currentUser.email}}
        </p>
        <p>
            <strong>Authority:</strong>
            {{currentUser.roles[0]}}
        </p>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';
  import HrService from '../../services/hr.service';
  import InterviewerService from '../../services/interviewer.service';

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
      }
    },
    mounted() {
      if (!this.currentUser) {
        this.$router.push('/login');
      }
      this.getProfileInfo();
    },
    methods: {
      getProfileInfo() {
        const roles = this.currentUser.roles;

        if (roles.includes('ROLE_HR')) {
          HrService.getMe(this.currentUser.username).then(({data}) => {
            this.userData = data;
          })
        } else if (roles.includes('ROLE_INTERVIEWER')) {
          InterviewerService.getMe(this.currentUser.username).then(({data}) => {
            this.userData = data;
          })
        } else if (roles.includes('ROLE_CANDIDATE')) {
          CandidateService.getMe(this.currentUser.username).then(({data}) => {
            this.userData = data;
          })
        }
      }
    }
  };
</script>