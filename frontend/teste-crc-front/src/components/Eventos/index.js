import './style.css';
import axios from 'axios';
import { useEffect, useState } from 'react';


function Eventos() {
    const [posts, setPosts] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [idEditarEvento, setIdEditarEvento] = useState();
    const [mensagem, setMensagem] = useState("");
    const [filtro, setFiltro] = useState("");

    async function getPosts() {
        try {
            const resposta = await axios.get(`http://localhost:8080/api/v1/eventos${filtro}`);
            const dados = resposta.data
            setPosts(dados);

        } catch (e) {
            alert("Ocorreram erros durante a requisição!");
            console.error(e);
        }
    }

    useEffect(() => {
        getPosts();
    }, [filtro]);

    async function deletarEvento(id) {
        try {
            await axios.delete(`http://localhost:8080/api/v1/eventos/${id}`);
            alert("O evento foi deletado com sucesso");
            getPosts();
        }
        catch (e) {
            setMensagem("Ocorreram erros durante a requisição, tente novamente!");
            console.error(e);
        }
    }
    function ModalEditar() {
        return (
            <div className='foraDoModal'>

                <div className='containerModal'>
                    <div className='d-flex justify-content-end m-3 '>
                        <button onClick={() => setShowModal(false)} className='btn btn-danger'>Fechar</button>
                    </div>
                    <form className="p-3 ">
                        <div>
                            <label className="form-label text-light bold">Nome:</label>
                            <input type="text" id="editarNomeDoEvento" className="form-control"  placeholder="Nome"  />
                        </div>

                        <div className='mt-2'>
                            <label className="form-label text-light bold">Descrição:</label>
                            <input type="text" id="editarDescricaoDoEvento" className="form-control" placeholder="Descrição"  />
                        </div>

                        <div className='mt-2'>
                            <label className="form-label text-light bold" >Prazo Inscrição:</label>
                            <input className='form-control' type="date"  id="editarPrazoDeInscricaoDoEvento" />
                        </div>

                        <div className='mt-2'>
                            <label className="form-label text-light bold">Prazo Submissão:</label>
                            <input className='form-control' type="date"  id="editarPrazoDeSubmissaoDoEvento"  />
                        </div>

                        <div className='d-flex gap-3 align-items-center justify-content-center mt-3'>
                            <label  className="form-label m-0  text-light bold">Ativo:</label>
                            <input type="radio" name="id" id="editarAtivo" />
                            <label className="form-label m-0  text-light bold">Inativo:</label>
                            <input type="radio" name="id" id="editarInativo" />

                        </div>
                        <div className='d-flex justify-content-center mt-3'>
                            <button onClick={editarEventoNoBd} type="submit" className='btn btn-success fs-4'>Enviar</button>
                        </div>
                        <div>
                            <p className={`text-center ${mensagem === "Sucesso!" ? 'text-success' : 'text-danger'} bold`}>{ mensagem }</p>
                        </div>
                        

                    </form>

                </div>
            </div>

        );
    }
    async function editarEventoNoBd(e){
        e.preventDefault();
        
        const nomeEvento = document.getElementById("editarNomeDoEvento").value;
        const descricaoEvento = document.getElementById("editarDescricaoDoEvento").value;
        const prazoInscricaoEvento = document.getElementById("editarPrazoDeInscricaoDoEvento").value;
        const prazoSubmissaoEvento = document.getElementById("editarPrazoDeSubmissaoDoEvento").value;

        const checkAtivo = document.getElementById("editarAtivo").checked;
        const checkInativo = document.getElementById("editarInativo").checked;

        
        if (nomeEvento !== "" && descricaoEvento !== "" && prazoInscricaoEvento !== "" && prazoInscricaoEvento !== "" && prazoSubmissaoEvento !== "" && (checkAtivo !== false || checkInativo !== false)) {
            const objetoEditarEvento = {
                nome: nomeEvento,
                descricao: descricaoEvento,
                ativo: checkAtivo,
                prazoInscricao: prazoInscricaoEvento,
                prazoSubmissao: prazoSubmissaoEvento
            }
            try {
                await axios.put(`http://localhost:8080/api/v1/eventos/${idEditarEvento}`, objetoEditarEvento);
                setMensagem("O evento foi editado com sucesso");
                getPosts();
                setShowModal(false); 
            }
            catch (e) {
                console.log(e);
            }
            
        } else {
            alert("Informe todos os dados para editar!");
            setMensagem("Informe todos os dados para editar");
        }
        
    }
    function editarEvento(id) {
        setIdEditarEvento(id);
        setMensagem("");
        setShowModal(true);
    }

    async function cadastrarEvento(e) {
        e.preventDefault();
        const nomeEvento = document.getElementById("nomeEvento").value;
        const descricaoEvento = document.getElementById("descricaoEvento").value;
        const prazoInscricaoEvento = document.getElementById("dataInscricao").value;
        const prazoSubmissaoEvento = document.getElementById("dataSubimissao").value;

        const checkAtivo = document.getElementById("checkAtivo").checked;
        const checkInativo = document.getElementById("checkInativo").checked;



        if (nomeEvento !== "" && descricaoEvento !== "" && prazoInscricaoEvento !== "" && prazoInscricaoEvento !== "" && prazoSubmissaoEvento !== "" && (checkAtivo !== false || checkInativo !== false)) {
            const cadastrarNovoEvento = {
                nome: nomeEvento,
                descricao: descricaoEvento,
                ativo: checkAtivo,
                prazoInscricao: prazoInscricaoEvento,
                prazoSubmissao: prazoSubmissaoEvento
            }
            await axios.post("http://localhost:8080/api/v1/eventos", cadastrarNovoEvento);
            
            setMensagem("Sucesso!");
            
        } else {
            alert("Informe todos os dados para cadastrar!");
            setMensagem("Informe todos os dados para cadastrar!");
        }

    }
    async function verParticipantes(id){
        try{
            console.log(id)
            const participantes = await axios.get(`http://localhost:8080/api/v1/inscricoes/listar/participantes/evento/${id}`);
            const dados = participantes.data;
            console.log(dados.length);
            if (dados.length === 0){
                alert("não existem participantes cadastrados")
            }else{
                let parthtml = "";
                for(let i = 0; i < dados.length; i++){
                    parthtml += `
                        <div>
                                    <div class='bg-secondary p-3 '>
                                        <h2 class="fs-6">Nome: ${dados[i].participante.nome}</h2>
                                        <p class="fs-6">Id: ${dados[i].participante.id} </p>
                                        <p class="fs-6">Email: ${dados[i].participante.email}</p>
                                        <p class="fs-6">Ativo: ${dados[i].participante.ativo}</p>
                                        <p class="fs-6">Se inscreveu em: ${dados[i].data}</p>
                                    </div>

                        </div>
                    
                    `;
                }
                document.getElementById(`participante${id}`).innerHTML = parthtml;
            }  
        }catch(e){
            console.log(e);
        }

    }

    return (
        <>
           
            {showModal ? <ModalEditar/> : ""}

            <div className='container'>

                <h1>Cadastrar novo evento:</h1>
                <form className='formCadastrar'>
                    <div className='inputText'>
                        <h3>Nome:</h3>
                        <input id="nomeEvento" className='campo' type="text" placeholder='Nome' />
                    </div>
                    <div className='inputText'>
                        <h3>Descrição:</h3>
                        <input id="descricaoEvento" className='campo' type="text" placeholder='Descrição' />
                    </div>


                    <div className='inputText'>
                        <h3>Prazo Inscrição:</h3>
                        <input id="dataInscricao" type="date" name="" />
                    </div>

                    <div className='inputText'>
                        <h3>Prazo Submissão:</h3>
                        <input id="dataSubimissao" type="date" name="" />
                    </div>
                    <div>
                        <h3>Status do evento:</h3>
                        <div className='containerCheckStatus'>
                            <label htmlFor="checkAtivo" className="m-0">Ativo:</label>
                            <input type="radio" name="radioAtivo" id="checkAtivo" />
                            <label htmlFor="checkInativo" className="m-0">Inativo:</label>
                            <input type="radio" name="radioAtivo" id="checkInativo" />

                        </div>
                    </div>
                    <div className='containerBotaoCadastro'>
                        <button className='btn btn-info bold' onClick={cadastrarEvento}>Cadastrar</button>
                    </div>

                    { mensagem.length > 0 ? (
                        <div>
                            <p className={`text-center ${mensagem === "Sucesso!" ? 'text-success' : 'text-danger'} bold`}>{ mensagem }</p>
                        </div>
                    ) : " " }


                </form>

                
                <div className="d-flex justify-content-center gap-2 mt-3 align-items-center">
                    <h1 className="fs-2 m-0">Filtros:</h1>
                    <button className={filtro === "" ? "btn btn-secondary" : "btn btn-primary"} onClick={() => setFiltro("")}>Todos</button>
                    <button className={filtro === "/filtrar/ativos" ? "btn btn-secondary" : "btn btn-primary"} onClick={() => setFiltro("/filtrar/ativos")}>Ativos</button>
                    <button className={filtro === "/filtrar/inativos" ? "btn btn-secondary" : "btn btn-primary"} onClick={() => setFiltro("/filtrar/inativos")}>Inativos</button>
                </div>

                <h1> Eventos </h1>
                {posts.length !== 0 ?
                    posts.map((dados) => (
                        <div className='cardEvento bg-dark text-light m-2 p-4' key={dados.id}>
                            <h3>Evento: {dados.nome}</h3>
                            <p>id: {dados.id}</p>
                            <p>Status: {dados.ativo ? "Ativo" : "Inativo"}</p>
                            <p>Descrição: {dados.descricao}</p>
                            <div className='prazosEvento'>
                                <h5 className="fs-6">Prazo de Inscrição: {dados.prazoInscricao} </h5>
                                <h5 className="fs-6">Prazo de Submissão: {dados.prazoSubmissao}</h5>
                                

                            </div>
                            <div className='d-flex  justify-content-between mt-2'>
                                <button className="btn btn-success bold" onClick={() => verParticipantes(dados.id)}>Ver participantes</button>
                                <div className='d-flex gap-3'>
                                    <button className='btn btn-danger bold' onClick={() => deletarEvento(dados.id)}>Deletar</button>
                                    <button className="btn btn-info bold" onClick={() => editarEvento(dados.id)}>Editar</button>
                                </div>


                            </div>

                            <div className="mt-3 d-flex  flex-column gap-3" id={`participante${dados.id}`}>
                                

                                
                            </div>



                        </div>
                    ))


                    : <></>}


            </div>
        </>



    );


}
export default Eventos;