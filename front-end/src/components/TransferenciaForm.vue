<template>
    <div>
      <h2>Agendar Transferência</h2>
      <form @submit.prevent="agendarTransferencia">
        <div>
          <label for="contaOrigem">Conta Origem:</label>
          <input v-model="transferencia.contaOrigem" id="contaOrigem" required />
        </div>
        <div>
          <label for="contaDestino">Conta Destino:</label>
          <input v-model="transferencia.contaDestino" id="contaDestino" required />
        </div>
        <div>
          <label for="valor">Valor:</label>
          <input v-model.number="transferencia.valor" type="number" id="valor" required />
        </div>
        <div>
          <label for="dataTransferencia">Data de Transferência:</label>
          <input v-model="transferencia.dataTransferencia" type="date" id="dataTransferencia" required />
        </div>
        <button type="submit">Agendar</button>
      </form>
      <p v-if="erro" class="error">{{ erro }}</p>
    </div>
  </template>
  
  <script>
  import api from '../services/api';
  
  export default {
    data() {
      return {
        transferencia: {
          contaOrigem: '',
          contaDestino: '',
          valor: 0,
          dataTransferencia: ''
        },
        erro: ''
      };
    },
    methods: {
      async agendarTransferencia() {
        try {
          const response = await api.post('/transferencias', this.transferencia);
          alert(`Transferência agendada com sucesso! ID: ${response.data.id}`);
          this.$emit('transferencia-agendada');
          this.erro = '';
        } catch (error) {
          this.erro = error.response?.data?.message || 'Erro ao agendar transferência.';
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .error {
    color: red;
  }
  </style>