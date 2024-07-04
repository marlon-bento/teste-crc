import { useState, useEffect } from 'react';
import axios from 'axios';

const API_BASE = "http://localhost:8080/api/v1/participantes"

export default function Participantes(){
    const [mensagem, setMensagem] = useState("");
    const [participantes, setParticipantes] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [editado, setEditado] = useState({});

    // Sub-componente Form de Update
    function ModalEditar(){

        return(
            <div className='foraDoModal'>
                <div className='containerModal'>
                    <div className='d-flex justify-content-end m-3 '>
                        <button onClick={() => setShowModal(false)} className='btn btn-danger'>fechar</button>
                    </div>
                    <form className='p-3'>
                        <div>
                            <label htmlFor="nomeParticipante" className="form-label text-light bold">Nome</label>
                            <input type="text" defaultValue={editado.nome} className="form-control" id="nomeParticipanteEd" aria-describedby="nomeHelp" placeholder="Seu Nome" required/>
                        </div>
                        <div className="mt-2">
                            <label htmlFor="emailParticipante" className="form-label text-light bold">Email</label>
                            <input type="email" defaultValue={editado.email} className="form-control" id="emailParticipanteEd" aria-describedby="emailHelp" placeholder="email@dominio.com" required/>
                        </div>
                        <div className="d-flex gap-3 align-items-center justify-content-center mt-3">
                        <label className="form-check-label text-light bold" htmlFor="checkAtivo">
                                Ativo
                            </label>
                            <input className="form-check-input" type="radio" name="statusRadioParticipante" id="checkAtivoEd" value="ativo"/>
                            <label className="form-check-label text-light bold" htmlFor="checkInativo">
                                Inativo
                            </label>
                            <input className="form-check-input" type="radio" name="statusRadioParticipante" id="checkInativoEd" value="invativo"/>
                        </div>
                        <div className="d-flex justify-content-center mt-3">
                            <button type="submit" className="btn btn-success" onClick={(ev) => {editarParticipante(ev, editado.id);}}>Editar</button>
                        </div>
                        <div className="text-center text-danger p-1">
                            <p className={`text-center ${mensagem === "Sucesso!" ? 'text-success' : 'text-danger'} bold`}>{ mensagem }</p>
                        </div>
                    </form>
                </div>
            </div>
    
        );
    }

    /**
     * Obtem todos participantes por GET request
     */
    async function getParticipantes() {
        try{
            const resposta = await axios.get(API_BASE);
            if (resposta.status >= 200 && resposta.status < 300) {
                const dados = resposta.data;
                
                setParticipantes(dados);
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
     * Cria novo Participante atraves de POST request
     * @param {Event} ev 
     * @returns 
     */
    async function criarParticipante (ev) {
        // Previne o envio do form
        ev.preventDefault();
        // Obter items do form
        const nome = document.getElementById("nomeParticipante").value;
        const email = document.getElementById("emailParticipante").value;
        const ativo = document.getElementById("checkAtivo").checked;
        const inativo = document.getElementById("checkInativo").checked;

        // Validar email
        if(nome.length <= 0 || email.length <= 0){
            setMensagem("Informe todos os dados para cadastrar!");
            return null;
        }

        if((!ativo && !inativo) || (ativo && inativo)){
            setMensagem("O participante deve ter status ativo ou inativo!");
            return null;
        }



        // Enviar
        try {
            const resposta = await axios.post(API_BASE, {nome: nome, email: email, ativo: ativo});
            if (resposta.status >= 200 && resposta.status < 300) {
                setMensagem("Sucesso!");
                await getParticipantes();
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
        } catch (error) {
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    async function editarParticipante (ev, id) {
        // Previne envio do form
        ev.preventDefault();

        // Obter items do form
        const nome = document.getElementById("nomeParticipanteEd").value;
        const email = document.getElementById("emailParticipanteEd").value;
        const ativo = document.getElementById("checkAtivoEd").checked;
        const inativo = document.getElementById("checkInativoEd").checked;

        // Validar
        if(nome.length <= 0 || email.length <= 0){
            setMensagem("Informe todos os dados para cadastrar!");
            return null;
        }

        if((!ativo && !inativo) || (ativo && inativo)){
            setMensagem("O participante deve ter status ativo ou inativo!");
            return null;
        }

        
        const participante = {nome: nome, email: email, ativo: ativo};
        try{
            const resposta = await axios.put(API_BASE + `/${id}`, participante);
            if (resposta.status >= 200 && resposta.status < 300) {
                alert("Editado com Sucesso!");
                setShowModal(false);
                setEditado({});
                await getParticipantes();
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
        }catch(error){
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    async function deletarParticipante (id) {
        try{
            const resposta = await axios.delete(API_BASE + `/${id}`);
            if (resposta.status >= 200 && resposta.status < 300) {
                alert("Sucesso!");
                await getParticipantes();
            }
            else {
                throw new Error(`Erro de Status ${resposta.status}`);
            }
        }catch(error){
            console.error(error);
            alert("Houve um erro na requisição!");
        }
    }

    useEffect(() => {getParticipantes();}, []);

    return (
    <>
        {showModal ? <ModalEditar /> : ""}
        <div className='container'>
            
            <h1>Novo Participante</h1>
            <form className='formCadastrar'>
                <div className="inputText">
                    <label htmlFor="nomeParticipante">Nome</label>
                    <input type="text" className="campo" id="nomeParticipante" aria-describedby="nomeHelp" placeholder="Seu Nome" required/>
                </div>
                <div className="inputText">
                    <label htmlFor="emailParticipante">Email</label>
                    <input type="email" className="campo" id="emailParticipante" aria-describedby="emailHelp" placeholder="email@dominio.com" required/>
                </div>
                <div className="containerCheckStatus">
                    <label className="" htmlFor="checkAtivo">
                        Ativo
                    </label>
                    <input className="" type="radio" name="statusRadioParticipante" id="checkAtivo" value="ativo" />
                    <label className="" htmlFor="checkInativo">
                        Inativo
                    </label>
                    <input className="" type="radio" name="statusRadioParticipante" id="checkInativo" value="inativo" />
                </div>
                <div className="containerBotaoCadastro">
                    <button type="submit" className="btn btn-info bold" onClick={criarParticipante}>Cadastrar</button>
                </div>
                
                <div className="text-center text-danger p-1">
                    <p className={`text-center ${mensagem === "Sucesso!" ? 'text-success' : 'text-danger'} bold`}>{ mensagem }</p>
                </div>
            
            </form>

            <h1> Participantes </h1>
            {participantes.length !== 0 ? 
                participantes.map((dados) =>(
                    <div className='cardEvento bg-dark text-light m-2 p-4' key={dados.id}>
                        <h3>Nome: {dados.nome}</h3>
                        <p>id: {dados.id}</p>
                        <p>Status: {dados.ativo ? "Ativo": "Inativo"}</p>
                        <p>Email: {dados.email}</p>
                        <div className='edicaoEvento'>
                        <button className="btn btn-danger" onClick={() => {deletarParticipante(dados.id);}}>Deletar</button>
                        <button className="btn btn-info" onClick={() => {setShowModal(true); setEditado({...dados}); setMensagem("");}}>Editar</button>
                        </div>

                        <div id="participantes">

                        </div>
                        
                        
                        
                    </div>
                ))  
            
            
            : <></>}


        </div>
    </>
    );
}