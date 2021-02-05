<template>
  <div class="about">
    <div class="container-chart">
      <line-chart ref="lineChart" :chart-data="dataChart" :options="optionsChart"></line-chart>
    </div>
    <div class="container-table" style="margin-top: 20px">
      <vs-table striped>
        <template #thead>
          <vs-tr>
            <vs-th v-for="(header, index) in tableHeaders" :key="index">
              {{header.header}}
            </vs-th>
            <vs-th>
              Elimina
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
            v-for="(row, i) in fatture"
            :key="i"
            :data="row"
          >
            <vs-td v-for="(header, index) in tableHeaders" :key="index">
              {{row[header.key]}}
            </vs-td>
            <vs-td>
              <vs-button
                icon
                color="danger"
                border
                @click="openDeleteConfirm(row)"
              >
                <i class='bx bxs-trash' ></i>
              </vs-button>
            </vs-td>
          </vs-tr>
        </template>
      </vs-table>
    </div>

    <vs-dialog width="550px" not-center v-model="openConfirm">
      <template #header>
        <h4 class="not-margin">
          Elimina riga
        </h4>
      </template>

      <div class="con-content">
        <p>
          Sei sicuro di voler eliminare la riga?
        </p>
      </div>

      <template #footer>
        <div class="con-footer">
          <vs-button @click="confirmDelete()" transparent>
            Conferma
          </vs-button>
          <vs-button @click="cancelDelete()" dark transparent>
            Cancella
          </vs-button>
        </div>
      </template>
    </vs-dialog>
  </div>
</template>

<script>
import LineChart from '@/components/LineChart.vue';

export default {
  name: 'History',
  components: { LineChart },
  data() {
    return {
      fatture: [],

      tableHeaders: [
        {
          key: 'dataFormatted',
          header: 'Data',
        },
        {
          key: 'fattura',
          header: 'Fattura',
        },
        {
          key: 'guadagno',
          header: 'Guadagno',
        },
        {
          key: 'tassa',
          header: 'Tassa',
        },
      ],

      dataChart: {
        labels: [],
        datasets: [],
      },
      optionsChart: {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: true,
          text: 'My Fatture',
        },
        scales: {
          yAxes: [{
            offset: true,
          }],
        },
      },

      selectedRow: null,
      openConfirm: false,
    };
  },

  mounted() {
    this.loadFatture();
  },

  methods: {
    async loadFatture() {
      const response = await this.$http.get('/api/fatture/all');
      this.fatture = response.data;

      const labels = [];
      const dataSets = [
        {
          label: 'Fattura',
          data: [],
          borderColor: '#8e5ea2',
          pointBackgroundColor: '#8e5ea2',
          pointBorderColor: '#fff',
          fill: false,
        },

        {
          label: 'Gudagno',
          data: [],
          borderColor: '#3cba9f',
          pointBackgroundColor: '#3cba9f',
          pointBorderColor: '#fff',
          fill: false,
        },

        {
          label: 'Tasse',
          data: [],
          borderColor: '#c45850',
          pointBackgroundColor: '#c45850',
          pointBorderColor: '#fff',
          fill: false,
        },
      ];

      this.fatture.forEach((f) => {
        if (f.data) {
          const d = new Date(f.data);
          const ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(d);
          const mo = new Intl.DateTimeFormat('en', { month: 'short' }).format(d);
          const da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(d);
          labels.push(`${da}-${mo}-${ye}`);

          dataSets[0].data.push(f.fattura);
          dataSets[1].data.push(f.guadagno);
          dataSets[2].data.push(f.tassa);
        }
      });

      this.$set(this.dataChart, 'datasets', dataSets);
      this.$set(this.dataChart, 'labels', labels);

      this.$refs.lineChart.render();
    },

    openDeleteConfirm(row) {
      this.openConfirm = true;
      this.selectedRow = row;
    },

    async confirmDelete() {
      await this.$http.post('/api/fatture/delete', this.selectedRow);
      this.selectedRow = null;
      this.openConfirm = false;
      this.fatture = [];
      await this.loadFatture();
    },

    cancelDelete() {
      this.selectedRow = null;
      this.openConfirm = false;
    },
  },
};
</script>

<style>
@media (min-width: 1281px) {
  .container-chart {
    width: 50%;
  }

  .container-table {
    width: 50%;
  }
}

@media (min-width: 1025px) and (max-width: 1280px) {

  .container-chart {
    width: 60%;
  }
  .container-table {
    width: 60%;
  }
}

@media (min-width: 768px) and (max-width: 1024px) {

  .container-chart {
    width: 80%;
  }
  .container-table {
    width: 80%;
  }
}

@media (min-width: 481px) and (max-width: 767px) {

  .container-chart {
    width: 90%;
  }
  .container-table {
    width: 90%;
  }
}

@media (min-width: 320px) and (max-width: 480px) {

  .container-chart {
    width: 90%;
  }

  .container-table {
    width: 90%;
  }
}

.about {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
  flex-direction: column;
}

.form-input {
  margin-bottom: 25px
}

.con-footer {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.con-footer .vs-button {
  margin: 0px;
}
.con-footer .vs-button .vs-button__content {
  padding: 10px 30px;
}
.con-footer .vs-button ~ .vs-button {
  margin-left: 10px;
}
.not-margin {
  margin: 0px;
  font-weight: normal;
  padding: 10px;
  padding-bottom: 0px;
}
.con-content {
  width: 100%;
}
.con-content p {
  font-size: 0.8rem;
  padding: 0px 10px;
}
.con-content .vs-checkbox-label {
  font-size: 0.8rem;
}
.con-content .vs-input-parent {
  width: 100%;
}
.con-content .vs-input-content {
  margin: 10px 0px;
  width: calc(100%);
}
.con-content .vs-input-content .vs-input {
  width: 100%;
}
.footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
}
.footer-dialog .new {
  margin: 0px;
  margin-top: 20px;
  padding: 0px;
  font-size: 0.7rem;
}
.footer-dialog .new a {
  color: rgba(var(--vs-primary), 1) !important;
  margin-left: 6px;
}
.footer-dialog .new a:hover {
  text-decoration: underline;
}
.footer-dialog .vs-button {
  margin: 0px;
}
</style>
