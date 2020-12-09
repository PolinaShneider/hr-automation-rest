<template>
    <div>
        <br>
        <h1>My applications</h1>
        <div v-if="!applications.length">
            There are no applications yet. Apply to position you like
        </div>
        <div v-else class="application" v-for="(item, index) in applications" :key="index">
            {{item.title}}
        </div>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';

  export default {
    name: "MyApplications",
    data() {
      return {
        applications: []
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      },
    },
    mounted() {
      CandidateService.getMyApplications(this.currentUser.id).then(({data}) => {
        this.applications = data;
      }).catch(() => {
        console.error('Error loading applications')
      })
    }
  }
</script>

<style scoped>
    .application {
        border: 1px solid;
    }
</style>