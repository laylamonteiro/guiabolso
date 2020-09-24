# MOCK API  

## Mock de uma API de transações

<h1 align="center">
    <a href="https://www.java.com/pt_BR/">🔗 Java</a>
</h1>
<p align="center">Mock de uma API para visualizar transações, geradas aleatoriamente</p>

# 🎲 Rodando o Back End (servidor)
### Clone este repositório
$ git clone <https://github.com/laylamonteiro/guiabolso>

### Abra o projeto na sua IDE de escolha

### Run > <i>GuiaBolsoApplication</i>

### O servidor inciará na porta:8080 - acesse <http://localhost:8080/{id}/transacoes/{ano}/{mes}> 

## Contrato

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
