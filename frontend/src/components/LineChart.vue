<script>
import { Line, mixins } from 'vue-chartjs';

const { reactiveProp } = mixins;

export default {
  name: 'LineChart',
  extends: Line,
  mixins: [reactiveProp],
  props: {
    chartData: {
      type: Object,
      default: () => {},
    },
    options: {
      type: Object,
      default: () => {},
    },
  },
  mounted() {
    this.render();
  },

  methods: {
    render() {
      this.renderChart(this.chartData, this.options);
    },
  },
};
</script>

<style scoped>

</style>
