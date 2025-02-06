<template>
    <div :style="styles.container">
      <h2>Extrato de Transferências</h2>
      <div class="table-responsive">
        <table :style="styles.table">
          <thead :style="styles.tableHeader">
            <tr>
              <th>ID</th>
              <th>Conta Origem</th>
              <th>Conta Destino</th>
              <th>Valor</th>
              <th>Taxa</th>
              <th>Valor Total</th>
              <th>Data de Transferência</th>
              <th>Data de Agendamento</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="transferencia in transferencias" :key="transferencia.id" :style="styles.tableRow">
              <td>{{ transferencia.id }}</td>
              <td>{{ transferencia.contaOrigem }}</td>
              <td>{{ transferencia.contaDestino }}</td>
              <td>R$ {{ transferencia.valor.toFixed(2) }}</td>
              <td>R$ {{ transferencia.taxa.toFixed(2) }}</td>
              <td>R$ {{ (transferencia.valor + transferencia.taxa).toFixed(2) }}</td>
              <td>{{ formatarData(transferencia.dataTransferencia) }}</td>
              <td>{{ formatarData(transferencia.dataAgendamento) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import api from '../services/api';
  import { styles } from '../assets/styles';
  
  export default {
    setup() {
      const transferencias = ref([]);
  
      const carregarTransferencias = async () => {
        const response = await api.get('/transferencias');
        transferencias.value = response.data;
      };
  
      const formatarData = (data) => {
        const dataLocal = new Date(data);
        dataLocal.setMinutes(dataLocal.getMinutes() + dataLocal.getTimezoneOffset());
        return dataLocal.toLocaleDateString('pt-BR');
      };
  
      onMounted(() => {
        carregarTransferencias();
      });
  
      return {
        transferencias,
        formatarData,
        carregarTransferencias,
        styles
      };
    }
  };
  </script>
  
  <style scoped>
  .table-responsive {
    overflow-x: auto;
  }
  
  th, td {
    padding: 0.75rem;
    text-align: left;
  }
  
  @media (max-width: 768px) {
    th, td {
      font-size: 0.875rem;
    }
  }
  </style>