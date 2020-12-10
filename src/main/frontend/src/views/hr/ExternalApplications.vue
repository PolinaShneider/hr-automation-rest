<template>
    <div>
        <div v-if="modalIsOpen" class="overlay"></div>
        <br>
        <h1>External applications</h1>
        <div v-if="!applications.length">
            There are no external applications yet. Probably, they will come soon.
        </div>
        <div v-else class="application" v-for="(item, index) in applications" :key="index" @click="() => takeAction(item)">
            <b>{{getPositionTitle(item.positionId)}}</b><br>
            <span class="badge" :class="getStatusClass(item.status)">
                Status: {{item.status}}
            </span><br>
            <span
                    @click="(e) => interviewRedirect(e, item.interviewId)" class="interview-link">
                Interview: <span class="badge badge-dark">{{item.interviewId || 'NONE'}}</span>
            </span>
        </div>
        <div v-if="modalIsOpen" class="modal-wrapper">
            <span class="close" @click="closeModal">Close</span>
            <label for="status">
                <b>Interview status</b>
            </label><br>
            <select id="status" v-model="status">
                <option>INTERVIEW_UPCOMING</option>
                <option>OFFER</option>
                <option>REJECT</option>
            </select><br><br>
            <label v-if="status === 'INTERVIEW_UPCOMING'" for="interviewer">
                <b>Interviewer</b>
            </label><br>
            <select v-model="interviewer" v-if="status === 'INTERVIEW_UPCOMING'" id="interviewer">
                <option v-for="(item, index) in interviewers" :key="index">{{item.username}}</option>
            </select>
            <br>
            <button type="button" class="btn btn-primary mt-3" @click="changeStatus">Submit</button>
        </div>
    </div>
</template>

<script>
  import HrService from '../../services/hr.service';
  export default {
    name: "ExternalApplications",
    data() {
      return {
        status: '',
        interviewer: '',
        applications: [],
        positions: [],
        interviewers: [],
        modalIsOpen: false,
        selectedApplication: null
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
      this.fetchInterviewers();
    },
    methods: {
      fetchApplications() {
        HrService.getApplications().then(({data}) => {
          this.applications = data;
        }).catch(() => {
          console.error('Error loading applications')
        })
      },
      fetchInterviewers() {
        HrService.getInterviewers().then(({data}) => {
          this.interviewers = data;
        }).catch(() => {
          console.error('Error loading interviewers')
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
      takeAction(item) {
        this.status = item.status;
        this.modalIsOpen = true;
        this.selectedApplication = item;
      },
      changeStatus() {
        HrService.updateApplication(this.selectedApplication.applicationId, {
          status: this.status, interviewerId: this.interviewer
        }).then(({data}) => {
          this.selectedApplication.status = data.status;
          this.selectedApplication = null;
        });

        this.modalIsOpen = false;
        this.interviewer = '';
        this.status = '';
      },
      closeModal() {
        this.modalIsOpen = false;
        this.selectedApplication = null;
        this.status = '';
        this.interviewer = '';
      },
      interviewRedirect(e, link) {
        e.stopImmediatePropagation();
        if (link) {
          HrService.getInterviewInfo(link).then(({data}) => {
            const feedback = data.feedback;

            if (feedback === 'ACCEPTED') {
              alert('Фидбэк положительный')
            } else if (feedback === 'FAIL') {
              alert('Фидбэк отрицательный')
            } else {
              alert('Фидбэка пока нет')
            }
          })
        }
      }
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

    .close {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 15px;
    }

    .modal-wrapper {
        position: fixed;
        transform: translate(-50%, -50%);
        top: 50%;
        left: 50%;
        padding: 15px;
        min-width: 300px;
    }

    .interview-link {
        position: relative;
        z-index: 10;
    }
</style>