<template>
  <div class="about">
    <div style="width: 50%;">
      <line-chart :chartdata="dataChart" :options="optionsChart"></line-chart>
    </div>
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

      dataChart: {
        labels: [
          'January',
          'February',
          'March',
          'April',
          'May',
          'June',
          'July',
        ],
        datasets: [
          {
            label: 'Data 1',
            data: [2, 10, 5, 9, 0, 6, 20],
            backgroundColor: 'transparent',
            borderColor: 'rgba(1, 116, 188, 0.50)',
            pointBackgroundColor: 'rgba(171, 71, 188, 1)',
          },
        ],
      },
      optionsChart: {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: true,
          text: 'My Fatture',
        },
      },
    };
  },

  mounted() {
    this.loadFatture();
  },

  methods: {
    async loadFatture() {
      const response = await this.$http.get('/api/fatture/all');
      this.fatture = response.data;
      console.log(this.fatture);

      const labels = [];
      const dataSets = [
        {
          label: 'Fattura',
          data: [],
          backgroundColor: 'transparent',
          borderColor: 'rgba(1, 116, 188, 0.50)',
          pointBackgroundColor: 'rgba(171, 71, 188, 1)',
        },

        {
          label: 'Gudagno',
          data: [],
          backgroundColor: 'transparent',
          borderColor: 'rgba(1, 116, 188, 0.50)',
          pointBackgroundColor: 'rgba(171, 71, 188, 1)',
        },

        {
          label: 'Tasse',
          data: [],
          backgroundColor: 'transparent',
          borderColor: 'rgba(1, 116, 188, 0.50)',
          pointBackgroundColor: 'rgba(171, 71, 188, 1)',
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

      console.log(this.dataChart);
    },
  },
};
</script>

<style>
.about {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
}

.form-input {
  margin-bottom: 25px
}
</style>
