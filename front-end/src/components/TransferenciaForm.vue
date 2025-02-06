<template>
    <div :style="styles.container">
      <h2>Agendar Transferência</h2>
      <form @submit.prevent="agendarTransferencia" :style="styles.form">
        <div class="mb-3">
          <label for="contaOrigem">Conta Origem:</label>
          <input
            v-model="transferencia.contaOrigem"
            id="contaOrigem"
            :class="{ 'is-invalid': erros.contaOrigem }"
            @input="validarContaOrigem"
            required
          />
          <div v-if="erros.contaOrigem" class="invalid-feedback">
            {{ erros.contaOrigem }}
          </div>
        </div>
        <div class="mb-3">
          <label for="contaDestino">Conta Destino:</label>
          <input
            v-model="transferencia.contaDestino"
            id="contaDestino"
            :class="{ 'is-invalid': erros.contaDestino }"
            @input="validarContaDestino"
            required
          />
          <div v-if="erros.contaDestino" class="invalid-feedback">
            {{ erros.contaDestino }}
          </div>
        </div>
        <div class="mb-3">
          <label for="valor">Valor:</label>
          <input
            v-model.number="transferencia.valor"
            type="number"
            step="0.01"
            id="valor"
            :class="{ 'is-invalid': erros.valor }"
            @input="validarValor"
            required
          />
          <div v-if="erros.valor" class="invalid-feedback">
            {{ erros.valor }}
          </div>
        </div>
        <div class="mb-3">
          <label for="dataTransferencia">Data de Transferência:</label>
          <input
            v-model="transferencia.dataTransferencia"
            type="date"
            id="dataTransferencia"
            :class="{ 'is-invalid': erros.dataTransferencia }"
            @input="validarDataTransferencia"
            required
          />
          <div v-if="erros.dataTransferencia" class="invalid-feedback">
            {{ erros.dataTransferencia }}
          </div>
        </div>
        <button type="submit" :style="styles.button" :disabled="loading || !formularioValido">
          <font-awesome-icon v-if="loading" icon="spinner" spin />
          <span v-else>Agendar</span>
        </button>
      </form>
  
      <!-- Mensagem de Sucesso -->
      <div v-if="sucesso" :style="{ ...styles.alert, ...styles.alertSuccess }">
        <font-awesome-icon icon="check-circle" />
        {{ sucesso }}
      </div>
  
      <!-- Mensagem de Erro -->
      <div v-if="erro" :style="{ ...styles.alert, ...styles.alertError }">
        <font-awesome-icon icon="exclamation-circle" />
        {{ erro }}
      </div>
    </div>
  </template>
  
  <script>
  import { ref, computed } from 'vue';
  import api from '../services/api';
  import { styles } from '../assets/styles';
  
  export default {
    setup(props, { emit }) {
      const transferencia = ref({
        contaOrigem: '',
        contaDestino: '',
        valor: 0,
        dataTransferencia: ''
      });
      const erros = ref({
        contaOrigem: '',
        contaDestino: '',
        valor: '',
        dataTransferencia: ''
      });
      const erro = ref('');
      const sucesso = ref('');
      const loading = ref(false);
  
      const formularioValido = computed(() => {
        return (
          !erros.value.contaOrigem &&
          !erros.value.contaDestino &&
          !erros.value.valor &&
          !erros.value.dataTransferencia
        );
      });
  
      const validarContaOrigem = () => {
        if (!transferencia.value.contaOrigem) {
          erros.value.contaOrigem = 'A conta de origem é obrigatória.';
        } else if (transferencia.value.contaOrigem.length !== 10) {
          erros.value.contaOrigem = 'A conta de origem deve ter 10 dígitos.';
        } else {
          erros.value.contaOrigem = '';
        }
      };
  
      const validarContaDestino = () => {
        if (!transferencia.value.contaDestino) {
          erros.value.contaDestino = 'A conta de destino é obrigatória.';
        } else if (transferencia.value.contaDestino.length !== 10) {
          erros.value.contaDestino = 'A conta de destino deve ter 10 dígitos.';
        } else {
          erros.value.contaDestino = '';
        }
      };
  
      const validarValor = () => {
        if (transferencia.value.valor <= 0) {
          erros.value.valor = 'O valor deve ser maior que zero.';
        } else if (!/^\d+(\.\d{1,2})?$/.test(transferencia.value.valor)) {
          erros.value.valor = 'O valor deve ser um número válido (ex: 45.60).';
        } else {
          erros.value.valor = '';
        }
      };
  
      const validarDataTransferencia = () => {
        const dataAtual = new Date();
        const dataTransferencia = new Date(transferencia.value.dataTransferencia);
        if (!transferencia.value.dataTransferencia) {
          erros.value.dataTransferencia = 'A data de transferência é obrigatória.';
        } else if (dataTransferencia < dataAtual) {
          erros.value.dataTransferencia = 'A data de transferência não pode ser no passado.';
        } else {
          erros.value.dataTransferencia = '';
        }
      };
  
      const agendarTransferencia = async () => {
        loading.value = true;
        erro.value = '';
        sucesso.value = '';
  
        try {
          await api.post('/transferencias', transferencia.value);
          sucesso.value = 'Transferência agendada com sucesso!';
          emit('transferencia-agendada');
        } catch (error) {
          erro.value = error.response?.data?.message || 'Erro ao agendar transferência.';
        } finally {
          loading.value = false;
        }
      };
  
      return {
        transferencia,
        erros,
        erro,
        sucesso,
        loading,
        formularioValido,
        validarContaOrigem,
        validarContaDestino,
        validarValor,
        validarDataTransferencia,
        agendarTransferencia,
        styles
      };
    }
  };
  </script>
  
  <style scoped>
  .mb-3 {
    margin-bottom: 1rem;
  }
  
  label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
  }
  
  input {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button:disabled {
    opacity: 0.7;
    cursor: not-allowed;
  }
  
  .invalid-feedback {
    color: #dc3545;
    font-size: 0.875rem;
    margin-top: 0.25rem;
  }
  
  .is-invalid {
    border-color: #dc3545;
  }
  </style>