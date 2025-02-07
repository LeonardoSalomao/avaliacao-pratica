# Sistema de Agendamento de Transferências Financeiras

Este projeto foi desenvolvido como parte de um desafio técnico para a empresa **Tokio Marine**. O sistema permite agendar transferências financeiras, calcular taxas com base em regras específicas e visualizar um extrato de todas as transferências agendadas.

---

## **Tecnologias Utilizadas**

### **Back-end**

- **Java 11**
- **Spring Boot**
- **H2 Database** (banco de dados em memória)
- **Spring Data JPA**
- **Spring Web**
- **SpringDoc OpenAPI** (Swagger)

### **Front-end**

- **Vue.js 3**
- **Axios** (para chamadas à API)
- **Bootstrap** (para estilização)
- **Font Awesome** (para ícones)

---

## **Instalação e Execução**

### **Pré-requisitos**

1. **Java 11** instalado.
2. **Node.js** (versão 14.x ou superior) instalado.
3. **Maven** (para o back-end).
4. **Vue CLI** (para o front-end).

---

### **1. Back-end (Spring Boot)**

1. **Clone o repositório:**
   git clone https://github.com/seu-usuario/sistema-transferencias.git
   cd sistema-transferencias/backend

2. **Compile e execute o projeto:**
   mvn spring-boot:run

3. **Acesse a API:**
   A aplicação estará disponível em http://localhost:8080.
   Acesse o Swagger UI em http://localhost:8080/swagger-ui.html.

---

### **2. Front-end (Vue.js)**

1. **Navegue até a pasta do front-end:**
   cd ../frontend

2. **Instale as dependências:**
   npm install

3. **Execute o servidor de desenvolvimento:**
   npm run serve

4. **Acesse o front-end:**
   A aplicação estará disponível em http://localhost:8081.

---

## **Funcionalidades**

1. **Agendar Transferência**
   O usuário pode agendar uma transferência informando:

   - Conta de origem (10 dígitos).
   - Conta de destino (10 dígitos).
   - Valor da transferência (com até duas casas decimais).
   - Data de transferência (não pode ser no passado).

2. **Listar Transferências**
   Todas as transferências agendadas são exibidas em uma tabela, com os seguintes dados:

   - ID.
   - Conta de origem.
   - Conta de destino.
   - Valor.
   - Taxa.
   - Valor total (valor + taxa).
   - Data de transferência.
   - Data de agendamento.

3. **Cálculo de Taxas**
   A taxa é calculada com base na data de transferência:
   - 0 a 3 dias: 2,5% + R$ 3,00.
   - 4 a 10 dias: R$ 12,00.
   - 11 a 20 dias: 8,2%.
   - 21 a 30 dias: 6,9%.
   - 31 a 40 dias: 4,7%.
   - 41 a 50 dias: 1,7%.
