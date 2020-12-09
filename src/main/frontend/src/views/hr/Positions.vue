<template>
    <div>
        <div v-if="modalIsOpen" class="overlay"></div>
        <br>
        <h1>
            Positions
            <button @click="modalIsOpen = true">Open new</button>
        </h1>
        <div v-if="!positions.length">
            There are no opened positions yet. Open one?
        </div>
        <div v-else class="position" v-for="(item, index) in positions" :key="index">
            <b>{{item.title}}</b><br>
            Team: {{getTeamTitle(item.teamId)}}
        </div>
        <div class="modal-wrapper" v-if="modalIsOpen">
            <span class="close" @click="modalIsOpen = false">Close</span>
            <form>
                <input type="hidden" :value="currentPosition.id">
                <div class="form-group">
                    <label for="title" class="col-form-label">Title</label>
                    <input type="text" v-model="currentPosition.title" class="form-control" id="title">
                </div>
                <div class="form-group">
                    <label for="requirements" class="col-form-label">Requirements</label>
                    <textarea class="form-control" v-model="currentPosition.requirements" id="requirements"></textarea>
                </div>
                <div class="form-group">
                    <label for="is-opened" class="col-form-label">Is opened</label><br>
                    <input id="is-opened" type="checkbox" v-model="currentPosition.isOpened">
                </div>
                <div class="form-group">
                    <label for="team" class="col-form-label">Team</label><br>
                    <select id="team" v-model="currentPosition.team">
                        <option disabled value="">Please select one</option>
                        <option v-for="(item, index) in teams" :key="index" :value="item.alias">
                            {{item.title}}
                        </option>
                    </select>
                </div>
                <button type="button" class="btn btn-primary" @click="savePosition">Save</button>
            </form>
        </div>
    </div>
</template>

<script>
  import HrService from '../../services/hr.service';

  const POSITION_TEMPLATE = {
    title: '',
    requirements: '',
    team: '',
    id: '',
    isOpened: true
  };

  export default {
    name: "Positions",
    data() {
      return {
        positions: [],
        teams: [],
        modalIsOpen: false,
        currentPosition: POSITION_TEMPLATE
      };
    },
    mounted() {
      this.fetchPositions();
      this.fetchTeams();
    },
    methods: {
      fetchPositions() {
        HrService.getPositions().then(({data}) => {
          this.positions = data;
        }).catch(() => {
          console.error('Error loading positions')
        })
      },
      fetchTeams() {
        HrService.getTeams().then(({data}) => {
          this.teams = data;
        }).catch(() => {
          console.error('Error loading teams')
        })
      },
      getTeamTitle(id) {
        const team = this.teams.find(team => team.alias === id);
        return team ? team.title : '';
      },
      savePosition() {
        if (!this.currentPosition.id) {
          HrService.createPosition({
            title: this.currentPosition.title,
            requirements: this.currentPosition.requirements,
            isOpened: this.currentPosition.isOpened,
            teamId: this.currentPosition.team,
          }).then((data) => {
            // console.log(data);
          })
        } else {
          HrService.updatePosition(this.currentPosition.id, {
            title: this.currentPosition.title,
            requirements: this.currentPosition.requirements,
            isOpened: this.currentPosition.isOpened,
            teamId: this.currentPosition.team,
          }).then((data) => {
            // console.log(data)
          })
        }

        this.currentPosition = POSITION_TEMPLATE;
        this.modalIsOpen = false;
        this.fetchPositions();
      }
    }
  }
</script>

<style scoped>
    .position {
        border: 1px solid;
        padding: 20px;
        border-radius: 4px;
        margin: 20px 0;
    }

    .overlay {
        background: rgba(0,0,0,.25);
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        overflow: hidden;
    }

    .modal-wrapper {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border: 1px solid beige;
        border-radius: 3px;
        background: white;
        padding: 20px;
        min-width: 600px;
    }

    .close {
        position: absolute;
        top: 20px;
        right: 20px;
        font-size: 20px;
    }
</style>