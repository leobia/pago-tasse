<template>
  <div class="home">
    <vs-row justify="center">
      <vs-col w="4">
        <vs-input
          dark
          class="form-input"
          type="date"
          v-model="data"
          label="Data"
        >
          <template #icon>
            <i class='bx bx-calendar'></i>
          </template>
        </vs-input>

        <vs-input primary class="form-input" v-model="fattura" label="Fattura">
          <template #icon>
            <i class='bx bx-euro'></i>
          </template>
        </vs-input>

        <vs-input danger class="form-input" v-model="tasse" label="Tasse" readonly>
          <template #icon>
            <i class='bx bx-euro'></i>
          </template>
        </vs-input>

        <vs-input success class="form-input" v-model="guadagno" label="Guadagno" readonly>
          <template #icon>
            <i class='bx bx-euro'></i>
          </template>
        </vs-input>

        <vs-button
          class="form-input"
          gradient
          :disabled="saveDisabled"
          @click="save"
        >
          Salva
        </vs-button>
      </vs-col>
    </vs-row>

  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {},

  data() {
    return {
      data: '',
      fattura: '',
    };
  },

  computed: {
    tasse() {
      let tax = 0;
      let { fattura } = this;
      if (fattura && !Number.isNaN(fattura)) {
        fattura = parseFloat(fattura.replace(',', '.').replace(' ', ''));
        const originalFattura = (fattura / 100) * 78;
        const quindici = (originalFattura / 100) * 15;
        const ventisette = (originalFattura / 100) * 26;
        tax = quindici + ventisette;
      }
      return Math.round((tax + Number.EPSILON) * 100) / 100;
    },

    guadagno() {
      let guad = 0;
      let { fattura } = this;
      if (fattura && !Number.isNaN(fattura)) {
        fattura = parseFloat(fattura.replace(',', '.').replace(' ', ''));
        guad = fattura - this.tasse;
      }
      return guad;
    },

    saveDisabled() {
      const inputFattura = this.fattura;
      const inputData = this.data;

      if (!inputFattura || !inputFattura.length > 0 || !inputData || !inputData.length > 0) {
        return true;
      }

      const fatturaNum = parseFloat(inputFattura.replace(',', '.').replace(' ', ''));
      return Number.isNaN(fatturaNum);
    },
  },

  methods: {
    async save() {
      const body = {
        fattura: parseFloat(this.fattura.replace(',', '.').replace(' ', '')),
        tassa: this.tasse,
        guadagno: this.guadagno,
        data: this.data,
      };

      await this.$http.post('/api/fatture/insert', body, { message: 'Row inserted correctly' });

      this.clear();
    },

    clear() {
      this.data = '';
      this.fattura = '';
    },
  },
};
</script>

<style>
.home {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
}

.form-input {
  margin-bottom: 25px
}
</style>
