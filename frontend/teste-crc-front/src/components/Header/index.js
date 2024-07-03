import "./style.css"
import { Link } from "react-router-dom";
export default function Header (){
    return(
        <div className="container-menu-header">
            <ul className="menu-header">
                <li><Link to='/' className="link-menu">Home</Link></li>
                <li><Link to='/eventos' className="link-menu">Eventos</Link></li>
                <li><Link to='/participantes' className="link-menu">Participantes</Link></li>
            </ul>
        </div>
        
    );
}