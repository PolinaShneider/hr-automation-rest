<template>
    <div>
        <br>
        <h1>My interviews</h1>
        <div v-if="!interviews.length">
            You have no upcoming interviews for now
        </div>
        <div v-else class="interview" v-for="(item, index) in interviews" :key="index">
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
    name: "MyInterviews",
    data() {
      return {
        interviews: [],
        positions: []
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      },
    },
    mounted() {
      this.fetchInterviews();
      this.fetchPositions();
    },
    methods: {
      fetchInterviews() {
        CandidateService.getMyInterviews(this.currentUser.id).then(({data}) => {
          this.interviews = data;
        }).catch(() => {
          console.error('Error loading interviews')
        })
      },
      fetchPositions() {
        CandidateService.getPositions().then(({data}) => {
          this.positions = data;
        }).catch(() => {
          console.error('Error loading interviews')
        })
      },
      getPositionTitle(id) {
        const position = this.positions.find(position => position.id === id);
        return position ? position.title : '';
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
    .interview {
        border: 1px solid;
        padding: 20px;
        border-radius: 4px;
        margin: 20px 0;
        position: relative;
    }
</style>