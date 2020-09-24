# MOCK API  

## Mock de uma API de transações

<h1 align="center">
    <a href="https://www.java.com/pt_BR/"> Java </a>
     <a href="https://gradle.org/">| Gradle |</a>
     <a href="https://www.docker.com/"> Docker </a>
</h1>
<p align="center">Mock de uma API para visualizar transações, geradas aleatoriamente</p>

# Rodando o Back End (servidor)
### Clone este repositório
$ git clone <https://github.com/laylamonteiro/guiabolso>

### Abra o projeto na sua IDE de escolha

### Run > <i>GuiaBolsoApplication</i>

### O servidor iniciará na porta:8080 

## Contrato

### Faça uma requisição GET para a URI: <http://localhost:8080/{id}/transacoes/{ano}/{mes}> onde:
* ID: seja um número inteiro entre 1.000 e 100.000.000;
* ano: seja um número inteiro entre 1900 e 2020;
* mes: seja um número inteiro entre 1 e 12;

### Resposta esperada:

```
[GET] /<id>/transacoes/<ano>/<mes>

Content-type: application/json

[
  {
     "descricao": "string(10, 120)"
     "data": "long(timestamp)"
     "valor": "integer(-9.999.999, 9.999.999)"
     "duplicated": "boolean"
  }  
]
```


# 
