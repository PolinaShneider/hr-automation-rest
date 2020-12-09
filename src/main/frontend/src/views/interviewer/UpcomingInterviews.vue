<template>
    <div>
        <br>
        <h1>Upcoming interviews</h1>
        <div v-if="!interviews.length">
            You have no interviews to conduct for now
        </div>
        <div v-else class="interview" v-for="(item, index) in interviews" :key="index">
            {{item.title}}
        </div>
    </div>
</template>

<script>
  import InterviewerService from '../../services/interviewer.service';
  export default {
    name: "UpcomingInterviews",
    data() {
      return {
        interviews: []
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      },
    },
    mounted() {
      InterviewerService.getMyInterviews(this.currentUser.id).then((data) => {
        this.interviews = data;
      }).catch(() => {
        console.error('Error loading interviews')
      })
    }
  }
</script>

<style scoped>
    .interview {
        border: 1px solid;
    }
</style>