<template>
    <div>
        <br>
        <h1>Open positions</h1>
        <div v-if="!positions.length">
            There are no open positions
        </div>
        <div v-else class="position" v-for="(item, index) in positions" :key="index">
            {{item.title}}
        </div>
    </div>
</template>

<script>
  import CandidateService from '../../services/candidate.service';
  export default {
    name: "Positions",
    data() {
      return {
        positions: []
      };
    },
    mounted() {
      CandidateService.getPositions().then(({data}) => {
        this.positions = data;
      }).catch(() => {
        console.error('Error loading positions')
      })
    }
  }
</script>

<style scoped>
    .position {
        border: 1px solid;
    }
</style>