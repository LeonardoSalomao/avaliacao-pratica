import { createApp } from 'vue';
import App from './App.vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCheckCircle, faExclamationCircle, faSpinner } from '@fortawesome/free-solid-svg-icons';
import 'bootstrap/dist/css/bootstrap.css';

// Adiciona os ícones à biblioteca
library.add(faCheckCircle, faExclamationCircle, faSpinner);

const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon); // Registra o componente de ícone
app.mount('#app');