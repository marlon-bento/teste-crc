# Instruções para iniciar o Back end
## Na pasta `teste-crc\backend\puc-crc` rodar no terminal:
### `docker-compose up --build`
---
# Instruções para iniciar o front end
## Na pasta `teste-crc\frontend\teste-crc-front` rodar no terminal:  
### `npm instal`
## Depois para startar a aplicação:
### `npm start`

--- 

## Agora sobre as requisições que o back pode fazer:

# Requisições de Evento

**URL:** `api/v1/eventos`

**Método:** `POST`

**Parâmetros do Corpo (JSON):**


```json
{
        "nome": "Jogo de futebol",
        "descricao": "Evento para quem gosta de futebol jogar com outros que também gostam",
        "ativo": true,
        "prazoInscricao": "2024-08-01",
        "prazoSubmissao": "2024-07-25"
}
```

**Possíveis Respostas:**

- **201 CREATED:** com o json do Evento criado sendo retornado.
---
**URL:** `api/v1/eventos/{id}`

**Método:** `GET`

**Descrição:** `A requisição serve para pesquisar um evento pelo seu id, passando o id pela url`


**Possíveis Respostas:**

- **200 OK:** com o json do Evento criado sendo retornado.
- **404 NOT FOUND:** com mensagem "id não foi encontrado".

---
**URL:** `api/v1/eventos`

**Método:** `GET`

**Descrição:** `A requisição serve para pesquisar todos os eventos`


**Possíveis Respostas:**

- **200 OK:** com uma lista de eventos.

---
**URL:** `api/v1/eventos/{id}`

**Método:** `PUT`

**Descrição:** `A requisição serve para alterar os dados do evento que tem o id que foi passado pela url`

**Parâmetros do Corpo (JSON):**


```json
{
        "nome": "Novo nome)",
        "descricao": "Nova descrição.",
        "ativo": true,
        "prazoInscricao": "2024-07-01",
        "prazoSubmissao": "2024-07-01"
}
```

**Possíveis Respostas:**

- **200 OK:** com o json do Evento editado sendo retornado.

---

**URL:** `api/v1/eventos/{id}`

**Método:** `DELETE`

**Descrição:** `A requisição deleta o evento que tem o id passado pela url`


**Possíveis Respostas:**

- **204 NO CONTENT :** body vazio.
- **404 NOT FOUND:** com mensagem "Evento não existe".

---

**URL:** `api/v1/eventos/filtrar/ativos`

**Método:** `GET`

**Descrição:** `A requisição filtra todos os eventos que estão ativos`


**Possíveis Respostas:**

- **200 OK :** Com a lista de Eventos ativos.


---

**URL:** `api/v1/eventos/filtrar/inativos`

**Método:** `GET`

**Descrição:** `A requisição filtra todos os eventos que estão inativos`


**Possíveis Respostas:**

- **200 OK :** Com a lista de Eventos inativos.

---

**URL:** `api/v1/eventos/filtrar/prazo-submicao`

**Método:** `GET`

**Descrição:** `A requisição filtra todos os eventos que possuem prazo de submissão igual a informada`

**Parâmetros do Corpo (JSON):**


```json
{
        "prazoSubmissao": 10,
}
```

**Possíveis Respostas:**

- **200 OK :** Com a lista de Eventos filtrados.

---

**URL:** `api/v1/eventos/filtrar/prazo-inscricao`

**Método:** `GET`

**Descrição:** `A requisição filtra todos os eventos que possuem prazo de inscricao igual a informada`

**Parâmetros do Corpo (JSON):**


```json
{
        "prazoInscricao": 10,
}
```

**Possíveis Respostas:**

- **200 OK :** Com a lista de Eventos filtrados.

---

# Requisições de Participantes

**URL:** `api/v1/participantes`

**Método:** `POST`

**Descrição:** `Criar novo participante`

**Parâmetros do Corpo (JSON):**


```json
{
        	"nome": "Nome participante",
          "email": "Email do participante",
          "ativo": true
}
```

**Possíveis Respostas:**

- **201 Created :** Com retorno do participante criado.
  
---

**URL:** `api/v1/participantes/{id}`

**Método:** `GET`

**Descrição:** `Devolve o participante com o id passado pela requisição`


**Possíveis Respostas:**

- **404 NOT FOUND :** Com mensagem "id não foi encontrado".
- **200 OK :** Com objeto encontrado.

---

**URL:** `api/v1/participantes`

**Método:** `GET`

**Descrição:** `Devolve uma lista de todos os participantes`


**Possíveis Respostas:**

- **200 OK :** Com lista de todos os participantes.

---

**URL:** `api/v1/participantes/{id}`

**Método:** `PUT`

**Descrição:** `Editar participante por id informado pela url`

**Parâmetros do Corpo (JSON):**


```json
{
        	"nome": "Nome participante",
          "email": "Email do participante",
          "ativo": true
}
```

**Possíveis Respostas:**

- **200 OK :** Com retorno do participante editado.

---

**URL:** `api/v1/participantes/{id}`

**Método:** `DELETE`

**Descrição:** `Deleta o participante que contem o id informado pela url`


**Possíveis Respostas:**

- **204 OK :** Com body vazio.
- **404 OK :** Com mensagem "Participante não existe" .


---

# Requisições de Inscrição

**URL:** `api/v1/inscricoes`

**Método:** `POST`

**Descrição:** `Fazer inscrição, recebe id do participante e o id do evento que ele quer se registrar`

**Parâmetros do Corpo (JSON):**


```json
{
    "participanteId": 3,
    "eventoId": 3
}
```

**Possíveis Respostas:**

- **404 NOT FOUND:** Com mensagem "O id de evento ou participante não existe".
- **201 CREATED:** Com retorno da Inscrição realizada.

---

**URL:** `api/v1/inscricoes`

**Método:** `GET`

**Descrição:** `Devolve uma lista de inscrições`


**Possíveis Respostas:**

- **200 OK :** Com lista de todos as inscrições.

---

**URL:** `api/v1/inscricoes/buscar/participantes/{id}`

**Método:** `GET`

**Descrição:** `Devolve todos as incrições que o participante de id informado possui`


**Possíveis Respostas:**

- **200 OK :** Com lista de todos as inscrições do participante.

---

**URL:** `api/v1/inscricoes/listar/participantes/evento/{id}`

**Método:** `GET`

**Descrição:** `Esse ao contrario do lista participantes do evento, esse recebe o id do evento e devolve uma lista de todos os participantes que estão inscritos neste evento em especifico`


**Possíveis Respostas:**

- **200 OK :** Lista de participantes de um evento.

---

**URL:** `api/v1/inscricoes/deletar/participantes/{id}`

**Método:** `DELETE`

**Descrição:** `Deleta todas as inscrições que o participante de id informado pela url está`


**Possíveis Respostas:**

- **200 OK :** body vazio.

---

**URL:** `/cancelar/evento/{idEvento}/participante/{idPart}}`

**Método:** `DELETE`

**Descrição:** `Cancela a inscrição de um participante em um evento especifico, com base no id do participante e no id do evento que deseja cancelar a inscrição`


**Possíveis Respostas:**

- **200 OK :** body vazio.
