  <template>
    <div id="app">
      <img alt="Vue logo" src="./assets/logo.png">
      <TransferenciaForm @transferencia-agendada="atualizarLista" />
      <TransferenciaList ref="lista" />
    </div>
  </template>
  
  <script>
  import TransferenciaForm from './components/TransferenciaForm.vue';
  import TransferenciaList from './components/TransferenciaList.vue';
  
  export default {
    components: {
      TransferenciaForm,
      TransferenciaList
    },
    methods: {
      atualizarLista() {
        this.$refs.lista.carregarTransferencias();
      }
    }
  };
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  </style>