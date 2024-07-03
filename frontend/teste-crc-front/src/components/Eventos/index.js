import './style.css';
import axios from 'axios';
import { useEffect, useState } from 'react';

const cardEvento = ({posts}) =>{
    return(
        
        <div>
            <h1>{posts[0].nome}</h1>
        </div>
    );

}
function Eventos(){
    const [posts, setPosts] = useState([]);
    async function getPosts(){
        try{
            const resposta = await axios.get("http://localhost:8080/api/v1/eventos");
            const dados = resposta.data 
            console.log(dados);
            console.log(dados.length)
            setPosts(dados);

        }catch(e){
            console.log(e);
        }
    }
useEffect(()=>{
    getPosts();
},[])
async function deletarEvento(id){
    try{
        await axios.delete(`http://localhost:8080/api/v1/eventos/${id}`);
        alert("O evento foi deletado com sucesso"); 
        getPosts();
    }
    catch(e){
        console.log(e);
    }
}
async function editarEvento(id){
    try{

        await axios.put(`http://localhost:8080/api/v1/eventos/${id}` );
        alert("O evento foi deletado com sucesso"); 
        getPosts();
    }
    catch(e){
        console.log(e);
    }
}

return(
    <div className='container'>
        <h1>Cadastrar novo evento:</h1>
        <form>
            <div className='inputText'>
                <h3>Nome:</h3>
                <input className='campo' type="text" placeholder='lorem lorem lorem'/>
            </div>
            <div className='inputText'>
                <h3>Descrição:</h3>
                <input className='campo' type="text" placeholder='lorem lorem lorem' />
            </div>


            <div className='inputText'>
                <h3>Descrição:</h3>
                <input className='campo' type="text"  placeholder='lorem lorem lorem'/>
            </div>
            <div>
                <h3>Status do evento:</h3>
                <div className='containerCheckStatus'>
                    <h4>Ativo:</h4>
                    <input type="radio" name="id" id="ativo"  />
                    <h4>Inativo:</h4>
                    <input type="radio" name="id" id="ativo" />
                </div>
            </div>
            <div className='containerBotaoCadastro'>
                <button>Cadastrar</button>
            </div>
            
           
        </form>
        <h1> Eventos </h1>
        {posts.length !== 0 ? 
            posts.map((dados) =>(
                <div className='cardEvento'>
                    <h3>Evento: {dados.nome}</h3>
                    <p>id: {dados.id}</p>
                    <p>Status: {dados.ativo ? "Ativo": "Inativo"}</p>
                    <p>Descrição: {dados.descricao}</p>
                    <div className='prazosEvento'>
                        <h5>prazo de Submissão: {dados.prazoSubmissao}</h5>
                        <h5>prazo de Inscrição: {dados.prazoInscricao} </h5>
                        
                    </div>
                    <div className='edicaoEvento'>
                    <button onClick={() => deletarEvento(dados.id)}>Deletar</button>
                    <button onClick={() => editarEvento(dados.id)}>editar evento</button>
                    </div>

                    <div id="participantes">

                    </div>
                    
                    
                    
                </div>
            ))  
        
        
        : <></>}


    </div>

    
);

}
export default Eventos;