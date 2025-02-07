<template>
  <div id="app">
    <TransferenciaForm @transferencia-agendada="atualizarLista" />
    <TransferenciaList ref="lista" />
  </div>
</template>

<script>
import { ref } from "vue";
import TransferenciaForm from "./components/TransferenciaForm.vue";
import TransferenciaList from "./components/TransferenciaList.vue";

export default {
  components: {
    TransferenciaForm,
    TransferenciaList,
  },
  setup() {
    const lista = ref(null);

    const atualizarLista = () => {
      if (lista.value) {
        lista.value.carregarTransferencias();
      }
    };

    return {
      lista,
      atualizarLista,
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
