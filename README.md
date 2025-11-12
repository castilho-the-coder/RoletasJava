# 🎪 Simulação de Controle de Acesso em Estádio - RoletasJava

Aplicação em Java que demonstra o uso de **threads, sincronização e concorrência** através da simulação de múltiplas roletas (catracas) de um estádio processando entrada de pessoas de forma segura.

## 📋 Descrição do Projeto

Este projeto implementa um sistema de simulação onde:
- 🎟️ Múltiplas roletas (catracas) funcionam em paralelo como threads independentes
- 👥 Cada roleta processa uma quantidade de pessoas
- 🔒 Um contador compartilhado registra o total de acessos de forma thread-safe
- ✅ O programa valida se todos os acessos foram contabilizados corretamente

## 🏗️ Arquitetura

### Estrutura de Classes

```
RoletasJava/
├── ContadorAcesso.java      # Recurso compartilhado (thread-safe)
├── Roleta.java              # Thread que simula uma roleta
├── SimulacaoEstadio.java    # Classe principal (orchestrator)
└── README.md                # Este arquivo
```

### Fluxo de Execução

```
┌─────────────────────────────────────────┐
│  SimulacaoEstadio (main)                │
│  - Cria ContadorAcesso compartilhado    │
│  - Cria 8 threads (Roletas)             │
└──────────────┬──────────────────────────┘
               │
        ┌──────┴──────┬──────────┬──────────┐
        │             │          │          │
    ┌───▼─┐      ┌───▼─┐   ┌───▼─┐   ┌───▼─┐
    │R 0  │      │R 1  │   │R 2  │   │R 3  │  ...
    └───┬─┘      └───┬─┘   └───┬─┘   └───┬─┘
        │             │          │          │
        └──────────┬──┴──────┬───┴──────┬───┘
                   │ Acesso sincronizado
                   ▼
            ┌─────────────────┐
            │ ContadorAcesso  │ (AtomicInteger)
            │  totalPessoas   │
            └─────────────────┘
```

## 🚀 Como Executar

### Pré-requisitos
- **Java 8+** instalado
- **Git** para clonar o repositório

### Clonar o Repositório
```bash
git clone https://github.com/castilho-the-coder/RoletasJava.git
cd RoletasJava
```

### Compilar
```bash
javac *.java
```

### Executar
```bash
java SimulacaoEstadio
```

### Saída Esperada
```
Iniciando simulação com 8 roletas.
Cada roleta processará 1000 pessoas.
Total esperado: 8000
-------------------------------------------------
Roleta 0 foi ativada.
Roleta 1 foi ativada.
Roleta 2 foi ativada.
...
Total agora: 100
Total agora: 200
...
>>> Roleta 0 terminou de processar 1000 pessoas em 5234ms
>>> Roleta 1 terminou de processar 1000 pessoas em 5198ms
...
-------------------------------------------------
Todas as roletas terminaram.
Total Esperado: 8000
Total Registrado: 8000
SUCESSO! Nenhum acesso foi perdido.
```

## 📚 Componentes Principais

### 1. **ContadorAcesso.java** 🔐
Representa o recurso compartilhado que todas as threads acessam simultaneamente.

**Características:**
- ✅ Thread-safe usando `AtomicInteger`
- ✅ Operações atômicas garantidas
- ✅ Logs reduzidos para melhor performance (a cada 100 pessoas)

**Características:**
- 🔄 Cada roleta é uma thread independente
- ⏱️ Mede tempo de execução com cronômetro
- 🎲 Simula tempo aleatório (0-10ms) para cada pessoa passar
- 🛡️ Trata exceções de interrupção

**Parâmetros Configuráveis:**
```java
private static final int NUM_ROLETAS = 8;           // Número de roletas
private static final int PESSOAS_POR_ROLETA = 1000; // Pessoas por roleta
```

## 🔑 Conceitos-Chave Demonstrados

### 1. **Threads em Java**
- Criação de threads com `Thread` e `Runnable`
- Gerenciamento de ciclo de vida

### 2. **Sincronização**
- `AtomicInteger` para operações thread-safe
- Evita race conditions no acesso compartilhado

### 3. **Concorrência**
- Múltiplas threads executando em paralelo
- Coordenação entre threads com `join()`

### 4. **Tratamento de Exceções**
- `InterruptedException` em operações de thread

A simulação valida a **integridade dos dados** em ambiente concorrente:

| Aspecto | Esperado | Validação |
|---------|----------|-----------|
| Total de roletas | 8 | ✅ Criadas |
| Pessoas por roleta | 1.000 | ✅ Processadas |
| **Total de pessoas** | **8.000** | ✅ **Sem perdas** |

Se todas as operações forem thread-safe, o resultado será sempre `SUCESSO!`