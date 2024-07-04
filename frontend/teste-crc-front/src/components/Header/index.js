import "./style.css"
import { Link } from "react-router-dom";
export default function Header (){
    return(
        <div className="container-menu-header d-flex justify-content-between align-items-center">
            <Link to="/" className="link-menu"><h3 className="text-light m-0">Teste do CRC</h3></Link>
            <ul className="menu-header m-0">
                <li><Link to='/eventos' className="link-menu">Eventos</Link></li>
                <li><Link to='/participantes' className="link-menu">Participantes</Link></li>
                <li><Link to='/inscricoes' className="link-menu">Inscrições</Link></li>
            </ul>
        </div>
        
    );
}