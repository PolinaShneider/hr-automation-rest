<template>
    <div>
        <br>
        <h1>Upcoming interviews</h1>
        <div v-if="!interviews.length">
            You have no interviews to conduct for now
        </div>
        <div v-else class="interview" v-for="(item, index) in interviews" :key="index" @click="() => takeAction(item)">
            <b>{{item.positionId}}</b><br>
            <span class="badge" :class="getStatusClass(item.status)">
                Status: {{item.status}}
            </span>
        </div>
        <div v-if="modalIsOpen" class="modal-wrapper">
            <span class="close" @click="closeModal">Close</span>
            <label for="feedback">
                <b>Interview feedback</b>
            </label><br>
            <select id="feedback" v-model="feeback">
                <option>ACCEPTED</option>
                <option>FAIL</option>
            </select><br><br>
            <button type="button" class="btn btn-primary mt-3" @click="sendFeedback">Submit</button>
        </div>
    </div>
</template>

<script>
  import InterviewerService from '../../services/interviewer.service';
  export default {
    name: "UpcomingInterviews",
    data() {
      return {
        interviews: [],
        feeback: 'NONE',
        modalIsOpen: false,
        selectedInterview: null
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      },
    },
    mounted() {
      InterviewerService.getMyInterviews(this.currentUser.username).then(({data}) => {
        this.interviews = data;
      }).catch(() => {
        console.error('Error loading interviews')
      })
    },
    methods: {
      closeModal() {
        this.modalIsOpen = false;
        this.selectedInterview = null;
        this.status = '';
        this.interviewer = '';
      },
      sendFeedback() {
        InterviewerService.conductInterview(this.selectedInterview.id, this.feeback).then(({data}) => {
          this.selectedInterview = null;
        });

        this.feeback = 'NONE';
        this.modalIsOpen = false;
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
      takeAction(interview) {
        this.selectedInterview = interview;
        this.modalIsOpen = true;
      }
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
        cursor: pointer;
    }
</style>