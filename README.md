# 💱 Conversor de Moedas - Challenge ONE Java

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## 📋 Descrição
Conversor de moedas que utiliza a API ExchangeRate-API para conversões em tempo real entre diversas moedas, desenvolvido como parte do Challenge ONE - Java da Oracle Next Education.

## ✨ Funcionalidades
- ✅ Conversão entre 6 moedas (USD, BRL, ARS, BOB, CLP, COP)
- 🖥️ Interface CLI simples e intuitiva
- 🔄 Taxas atualizadas via API
- ⚠️ Validação de entradas do usuário

## 🚀 Como Executar
Compile e execute:
```bash
javac Principal.java
java Principal
```

## 🛠️ Estrutura do Código
```java
public class Principal {
    public static void main(String[] args) {
        // Implementação principal
        HttpClient client = HttpClient.newHttpClient();
        String apiKey = "sua-chave-api";
        // ... resto do código
    }
}
```

## 📌 Pré-requisitos
- Java JDK 17+
- Conexão com internet
- Conta na [ExchangeRate-API](https://www.exchangerate-api.com/)

## 🔗 Dependências
- Gson (para manipulação de JSON)
- Java HTTP Client

## 📊 Moedas Suportadas
| Código | Moeda               |
|--------|---------------------|
| USD    | Dólar Americano     |
| BRL    | Real Brasileiro     |
| ARS    | Peso Argentino      |
| BOB    | Boliviano           |
| CLP    | Peso Chileno        |
| COP    | Peso Colombiano     |

---


