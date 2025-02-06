<template>
    <div>
      <h2>Extrato de Transferências</h2>
      <ul>
        <li v-for="transferencia in transferencias" :key="transferencia.id">
          {{ transferencia.contaOrigem }} -> {{ transferencia.contaDestino }}: R$ {{ transferencia.valor }} (Taxa: R$ {{ transferencia.taxa }})
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import api from '../services/api';
  
  export default {
    data() {
      return {
        transferencias: []
      };
    },
    async created() {
      await this.carregarTransferencias();
    },
    methods: {
      async carregarTransferencias() {
        const response = await api.get('/transferencias');
        this.transferencias = response.data;
      }
    }
  };
  </script>
  
  <style scoped>
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    margin: 10px 0;
  }
  </style>