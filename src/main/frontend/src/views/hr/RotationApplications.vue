<template>
    <div>
        <br>
        <h1>Rotation applications</h1>
        <div v-if="!applications.length">
            There are no rotation applications yet. Probably, they will come soon.
        </div>
        <div v-else class="application" v-for="(item, index) in applications" :key="index">
            <b>{{getPositionTitle(item.positionId)}}</b><br>
            <span class="badge" :class="getStatusClass(item.status)">
                Status: {{item.status}}
            </span>
        </div>
    </div>
</template>

<script>
  import HrService from '../../services/hr.service';
  export default {
    name: "RotationApplications",
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
        HrService.getRotationApplications().then(({data}) => {
          this.applications = data;
        }).catch(() => {
          console.error('Error loading applications')
        })
      },
      fetchPositions() {
        HrService.getPositions().then(({data}) => {
          this.positions = data;
        }).catch(() => {
          console.error('Error loading positions')
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
    .application {
        border: 1px solid;
        padding: 20px;
        border-radius: 4px;
        margin: 20px 0;
        position: relative;
        cursor: pointer;
    }
</style>