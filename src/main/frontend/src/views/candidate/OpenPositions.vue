<template>
    <div>
        <br>
        <h1>Open positions</h1>
        <div v-if="!positions.length">
            There are no open positions
        </div>
        <div v-else class="position" v-for="(item, index) in positions" :key="index">
            <span class="position-edit" @click="() => apply(item)">Apply</span>
            <b>{{item.title}}</b><br>
            Team: {{getTeamTitle(item.teamId)}}<br>
            Requirements: {{item.requirements}}
        </div>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';
  export default {
    name: "Positions",
    data() {
      return {
        positions: [],
        teams: []
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
    },
    mounted() {
      this.fetchPositions();
      this.fetchTeams();
    },
    methods: {
      apply(position) {
        CandidateService.apply({
          candidateId: this.currentUser.id,
          positionId: position.id
        }).then(({data}) => {
          this.$router.push({path: '/my-applications'})
        })
      },
      getTeamTitle(id) {
        const team = this.teams.find(team => team.alias === id);
        return team ? team.title : '';
      },
      fetchPositions() {
        CandidateService.getPositions().then(({data}) => {
          this.positions = data;
        }).catch(() => {
          console.error('Error loading positions')
        })
      },
      fetchTeams() {
        CandidateService.getTeams().then(({data}) => {
          this.teams = data;
        }).catch(() => {
          console.error('Error loading teams')
        })
      },
    }
  }
</script>