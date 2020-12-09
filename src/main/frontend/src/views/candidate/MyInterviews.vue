<template>
    <div>
        <br>
        <h1>My interviews</h1>
        <div v-if="!interviews.length">
            You have no upcoming interviews for now
        </div>
        <div v-else class="interview" v-for="(item, index) in interviews" :key="index">
            {{item.title}}
        </div>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';

  export default {
    name: "MyInterviews",
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
      CandidateService.getMyInterviews(this.currentUser.id).then(({data}) => {
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