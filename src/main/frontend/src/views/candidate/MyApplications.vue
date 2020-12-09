<template>
    <div>
        <br>
        <h1>My applications</h1>
        <div v-if="!applications.length">
            There are no applications yet. Apply to position you like
        </div>
        <div v-else class="application" v-for="(item, index) in applications" :key="index" @click="() => show(item.applicationId)">
            <b>{{getPositionTitle(item.positionId)}}</b><br>
            <span class="badge" :class="getStatusClass(item.status)">
                Status: {{item.status}}
            </span>
        </div>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';

  export default {
    name: "MyApplications",
    data() {
      return {
        applications: [],
        positions: []
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
    },
    mounted() {
      this.fetchApplications();
      this.fetchPositions();
    },
    methods: {
      fetchApplications() {
        CandidateService.getMyApplications(this.currentUser.id).then(({data}) => {
          this.applications = data;
        }).catch(() => {
          console.error('Error loading applications')
        })
      },
      fetchPositions() {
        CandidateService.getPositions().then(({data}) => {
          this.positions = data;
        }).catch(() => {
          console.error('Error loading positions')
        })
      },
      getPositionTitle(id) {
        const position = this.positions.find(position => position.id === id);
        return position ? position.title : '';
      },
      show(id) {
        CandidateService.getMyApplication(id).then(({data}) => {
          console.log(data);
        })
      },
      getStatusClass(status) {
        switch (status) {
          case 'PENDING':
            return 'badge-primary';
          case 'REJECT':
            return 'badge-danger';
          case 'OFFER':
            return 'badge-success';
          case 'INTERVIEW_UPCOMING':
            return 'badge-info';
          case 'INTERVIEW_PASSED':
            return 'badge-secondary';
          default:
            return 'badge-light';
        }
      },
    }
  }
</script>

<style scoped>
    .application {
        border: 1px solid;
        padding: 20px;
        border-radius: 4px;
        margin: 20px 0;
        position: relative;
        cursor: pointer;
    }
</style>