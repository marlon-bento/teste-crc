import { useState, useEffect } from 'react';
import axios from 'axios';

const API_BASE = "http://localhost:8080/api/v1/inscricoes"

export default function Inscricoes(){
    const [mensagem, setMensagem] = useState("");
    const [inscricoes, setInscricoes] = useState([]);



    /**
     * Obtem inscricoes por GET request
     */
    async function getInscricoes() {
        try{
            const resposta = await axios.get(API_BASE);
            if (resposta.status >= 200 && resposta.status < 300) {
                const dados = resposta.data;
                
                setInscricoes(dados);
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
            
        }catch(error){
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    /**
     * Cria nova Inscricao atraves de POST request
     * @param {Event} ev 
     * @returns 
     */
    async function criarInscricao (ev) {
        // Previne o envio do form
        ev.preventDefault();
        // Obter items do form
        const idPessoa = document.getElementById("idPessoa").value;
        const idEvento = document.getElementById("idEvento").value;

        // Validar
        if(!idPessoa || !idEvento){
            setMensagem("Informe todos os dados para cadastrar!");
            return null;
        }

        if(idPessoa < 0 || idEvento < 0){
            setMensagem("O ID deve ser maior do que 0!");
            return null;
        }

        // Enviar
        try {
            const resposta = await axios.post(API_BASE, {eventoId: idEvento, participanteId: idPessoa});
            if (resposta.status >= 200 && resposta.status < 300) {
                setMensagem("Sucesso!");
                await getInscricoes();
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
        } catch (error) {
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    async function cancelarInscricao (idPart, idEvento) {
        try{

            //Parametros foram definidos no objeto de configuração do axios
            const resposta = await axios.delete( `http://localhost:8080/api/v1/inscricoes/cancelar/evento/${idEvento}/participante/${idPart}` );
            if (resposta.status >= 200 && resposta.status < 300) {
                alert("Sucesso!");
                await getInscricoes();
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
        }catch(error){
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    useEffect(() => {getInscricoes();}, []);

    return (
        <div className='container'>
            
            <h1>Inscrever Pessoa</h1>
            <form className='formCadastrar'>
                <div className="inputText">
                    <label htmlFor="idPessoa">ID do Participante</label>
                    <input type="number" className="campo" id="idPessoa" placeholder="ID Participante" required/>
                </div>
                <div className="inputText">
                    <label htmlFor="idEvento">ID do Evento</label>
                    <input type="number" className="campo" id="idEvento" placeholder="ID Evento" required/>
                </div>
                <div className="containerBotaoCadastro">
                    <button type="submit" className="btn btn-info bold" onClick={criarInscricao}>Cadastrar</button>
                </div>
                
                <div className="text-center text-danger p-1">
                    <p className={`text-center ${mensagem === "Sucesso!" ? 'text-success' : 'text-danger'} bold`}>{ mensagem }</p>
                </div>
            
            </form>

            <h1> Inscricoes </h1>
            {inscricoes.length !== 0 ? 
                inscricoes.map((dados) =>(
                    <div className='cardEvento bg-dark text-light m-2 p-4' key={dados.id}>
                        <p>Evento: {dados.evento.nome}</p>
                        <p>Participante: {dados.participante.nome}</p>
                        <p>Data: {dados.data}</p>
                        <div className='edicaoEvento'>
                        <button className="btn btn-danger" onClick={() => {cancelarInscricao(dados.participante.id, dados.evento.id);}}>Cancelar</button>
                        </div>
                    </div>
                ))  
            
            
            : <></>}


        </div>
    );
}